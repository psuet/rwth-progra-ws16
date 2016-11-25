/*
 * HINWEIS:
 *
 * Sie brauchen den Java-Code, der in dieser Datei steht, nicht zu lesen oder zu 
 * verstehen. Alle Hinweise, die zur Verwendung der Datei noetig sind, koennen Sie 
 * aus den jeweiligen Aufgabentexten entnehmen.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;

/**
 * User interface for a Logo-like language. Allows to see the painting process step-by-step.
 */
public class Canvas {

    /**
     * The color brown.
     */
    public static final Color BROWN = new Color(160, 82, 45);

    /**
     * The color green.
     */
    public static final Color GREEN = Color.GREEN;

    /**
     * Adds the current position to the specified bounds.
     * @param g The graphics object to draw on.
     * @param boundsParam The bounds of the canvas.
     */
    private static void addCurrentPos(Graphics2D g, Rectangle boundsParam) {
        if (boundsParam != null) {
            Canvas.addToBounds(new Point2D.Float(0f, 0f), boundsParam, g);
        }
    }

    /**
     * Adds a point to the bounds, transformed as in the current graphics object.
     * @param point The point to add to the bounds.
     * @param boundsParam The bounds.
     * @param g The graphics object storing affine transformations.
     */
    private static void addToBounds(Point2D point, Rectangle boundsParam, Graphics2D g) {
        boundsParam.add(g.getTransform().transform(point, null));
    }

    /**
     * Adds a rectangle to the bounds, transformed as in the current graphics object.
     * @param r The rectangle to add to the bounds.
     * @param boundsParam The bounds.
     * @param g The graphics object storing affine transformations.
     */
    private static void addToBounds(Rectangle2D r, Rectangle boundsParam, Graphics2D g) {
        double maxX = r.getMaxX();
        double maxY = r.getMaxY();
        double minX = r.getMinX();
        double minY = r.getMinY();
        Canvas.addToBounds(new Point2D.Double(maxX, maxY), boundsParam, g);
        Canvas.addToBounds(new Point2D.Double(maxX, minY), boundsParam, g);
        Canvas.addToBounds(new Point2D.Double(minX, maxY), boundsParam, g);
        Canvas.addToBounds(new Point2D.Double(minX, minY), boundsParam, g);
    }

    /**
     * The bounds of the canvas.
     */
    protected Rectangle bounds;

    /**
     * The actions to be executed on the canvas.
     */
    private ArrayList<Canvas.GraphicAction> actions = new ArrayList<>();

    /**
     * The actual drawing on the canvas.
     */
    private final JComponent drawing = new JPanel() {

        /**
         * For serialization.
         */
        private static final long serialVersionUID = -1665573331455268961L;

        @Override
        public Dimension getPreferredSize() {
            synchronized (Canvas.this) {
                return Canvas.this.bounds == null ? new Dimension(100, 100) : Canvas.this.bounds.getSize();
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            synchronized (Canvas.this) {
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Rectangle clipBounds = g.getClipBounds();
                g2.setColor(Color.WHITE);
                g2.fill(clipBounds);
                g2.setColor(Color.BLACK);
                AffineTransform t = g2.getTransform();
                int drawCounter;
                // do we have current bounds?
                if (Canvas.this.bounds == null) {
                    Rectangle newBounds = new Rectangle();
                    Canvas.this.transformations.clear();
                    drawCounter = 0;
                    for (GraphicAction a : Canvas.this.actions) {
                        a.replay(g2, false, newBounds);
                        if (a.drawsSomething()) {
                            drawCounter++;
                        }
                    }
                    Canvas.this.bounds = new Rectangle(newBounds);
                }
                // move the drawing inside the screen
                GraphicAction init = new Move(20 - Canvas.this.bounds.x, 20 - Canvas.this.bounds.y);
                // draw all lines
                g2.setTransform(t);
                Canvas.this.transformations.clear();
                drawCounter = 0;
                init.replay(g2, true, null);
                for (GraphicAction a : Canvas.this.actions) {
                    boolean draw = !(a instanceof DrawTextLabel);
                    a.replay(g2, draw, null);
                    if (a.drawsSomething()) {
                        drawCounter++;
                    }
                    if (drawCounter >= Canvas.this.renderMaxDraws) {
                        break;
                    }
                }
                // draw all labels
                g2.setTransform(t);
                Canvas.this.transformations.clear();
                drawCounter = 0;
                init.replay(g2, true, null);
                for (GraphicAction a : Canvas.this.actions) {
                    boolean draw = (a instanceof DrawTextLabel);
                    a.replay(g2, draw, null);
                    if (a.drawsSomething()) {
                        drawCounter++;
                    }
                    if (drawCounter >= Canvas.this.renderMaxDraws) {
                        break;
                    }
                }
            }
        }

    };

    /**
     * The number of actions actually drawing something. Used to step forward and backward through the painting process.
     */
    private int draws;

    /**
     * Listener to execute the paint actions.
     */
    private final ActionListener handler = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "start":
                    Canvas.this.renderMaxDraws = 0;
                    break;
                case "next":
                    Canvas.this.renderMaxDraws++;
                    break;
                case "back":
                    Canvas.this.renderMaxDraws--;
                    break;
                case "end":
                    Canvas.this.renderMaxDraws = Canvas.this.draws;
                    break;
            }
            Canvas.this.step.setText("Schritt: " + Canvas.this.renderMaxDraws);
            Canvas.this.drawing.repaint();
        }

    };

    /**
     * Current step in the stepwise view of the painting process.
     */
    private int renderMaxDraws = 0;

    /**
     * Label for showing the current step.
     */
    private final JLabel step = new JLabel("Schritt: " + this.renderMaxDraws);

    /**
     * Used to (re-)store positions and orientations on the canvas.
     */
    private final Stack<AffineTransform> transformations = new Stack<>();

    /**
     * The frame to display the canvas.
     */
    private final JFrame ui = new JFrame() {

        /**
         * For serialization.
         */
        private static final long serialVersionUID = 8620900696432559397L;

        {
            // set up components
            final JScrollPane scrollPane = new JScrollPane(Canvas.this.drawing);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            final JPanel panel = new JPanel();
            final JButton startButton = new JButton("Anfang");
            startButton.setActionCommand("start");
            startButton.addActionListener(Canvas.this.handler);
            final JButton backButton = new JButton("Zurueck");
            backButton.setActionCommand("back");
            backButton.addActionListener(Canvas.this.handler);
            final JButton forwardButton = new JButton("Vor");
            forwardButton.setActionCommand("next");
            forwardButton.addActionListener(Canvas.this.handler);
            final JButton endButton = new JButton("Ende");
            endButton.setActionCommand("end");
            endButton.addActionListener(Canvas.this.handler);
            // configure frame
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            // set up layout
            GroupLayout jPanel1Layout = new GroupLayout(panel);
            panel.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING
                ).addGap(0, 158, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING
                ).addGap(0, 36, Short.MAX_VALUE)
            );
            GroupLayout layout = new GroupLayout(this.getContentPane());
            this.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING
                ).addGroup(
                    GroupLayout.Alignment.TRAILING,
                    layout.createSequentialGroup().addContainerGap().addGroup(
                        layout.createParallelGroup(
                            GroupLayout.Alignment.TRAILING
                        ).addComponent(
                            scrollPane,
                            GroupLayout.Alignment.LEADING,
                            GroupLayout.DEFAULT_SIZE,
                            458,
                            Short.MAX_VALUE
                        ).addGroup(
                            GroupLayout.Alignment.LEADING,
                            layout.createSequentialGroup().addComponent(
                                startButton
                            ).addGap(
                                6,
                                6,
                                6
                            ).addComponent(
                                backButton
                            ).addGap(
                                6,
                                6,
                                6
                            ).addComponent(
                                forwardButton
                            ).addGap(
                                6,
                                6,
                                6
                            ).addComponent(
                                endButton
                            ).addGap(
                                18,
                                18,
                                18
                            ).addComponent(
                                Canvas.this.step
                            ).addPreferredGap(
                                LayoutStyle.ComponentPlacement.RELATED
                            ).addComponent(
                                panel,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE
                            )
                        )
                    ).addContainerGap()
                )
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING
                ).addGroup(
                    GroupLayout.Alignment.TRAILING,
                    layout.createSequentialGroup().addContainerGap().addComponent(
                        scrollPane,
                        GroupLayout.DEFAULT_SIZE,
                        400,
                        Short.MAX_VALUE
                    ).addPreferredGap(
                        LayoutStyle.ComponentPlacement.RELATED
                    ).addGroup(
                        layout.createParallelGroup(
                            GroupLayout.Alignment.TRAILING
                        ).addComponent(
                            panel,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE
                        ).addGroup(
                            layout.createParallelGroup(
                                GroupLayout.Alignment.BASELINE
                            ).addComponent(
                                startButton
                            ).addComponent(
                                backButton
                            ).addComponent(
                                forwardButton
                            ).addComponent(
                                endButton
                            ).addComponent(
                                Canvas.this.step
                            )
                        )
                    ).addContainerGap()
                )
            );
            // make frame visible
            this.pack();
            this.setVisible(true);
        }

    };

    /**
     * Choose the specified color for further painting.
     * @param c The color to choose.
     */
    public void chooseColor(Color c) {
        this.addAction(new ChooseColor(c));
    }

    /**
     * Draws a line of length {@code length} in the current direction. The current position is moved to the end of the 
     * line.
     * @param length The length of the line to draw.
     */
    public void drawForward(int length) {
        this.addAction(new DrawForward(length));
    }

    /**
     * Draws a box with the text {@code text} at the current position.
     * @param text The text to draw.
     */
    public void drawTextLabel(String text) {
        this.addAction(new DrawTextLabel(text));
    }

    /**
     * Moves the current position by {@code x} units to the right and {@code y} units down according to the current 
     * orientation (so if the orientation is rotated by 180 degrees, the move goes left and up instead of right and 
     * down). This does not draw anything.
     * @param x The measure to go right.
     * @param y The measure to go down.
     */
    public void move(double x, double y) {
        this.addAction(new Move(x, y));
    }

    /**
     * Moves the current position by {@code length} units in the current direction without drawing anything.
     * @param length The measure to move the current position.
     */
    public void moveForward(int length) {
        this.addAction(new MoveForward(length));
    }

    /**
     * Sets the current position and orientation to the top-most stored position and orientation. See {@link #push()}.
     */
    public void pop() {
        this.addAction(new Pop());
    }

    /**
     * Stores the current position and orientation.
     * By a subsequent {@link #pop()}, this position and orientation can be restored.
     */
    public void push() {
        this.addAction(new Push());
    }

    /**
     * Refreshes the shown frame.
     */
    public void refresh() {
        synchronized (this) {}
        this.drawing.revalidate();
        this.ui.revalidate();
        this.ui.repaint();
    }

    /**
     * Turns the current orientation by {@code degrees} degrees (360 degrees form a circle) clockwise.
     * Negative values turn counter-clockwise.
     * @param degrees The degrees to turn the orientation.
     */
    public void rotate(int degrees) {
        this.addAction(new Rotate(degrees));
    }

    /**
     * Draws a filled square of the specified length {@code length} at the current position and with the current 
     * orientation.
     * @param length The length of the square to draw.
     */
    public void square(double length) {
        this.addAction(new DrawSquare(length));
    }

    /**
     * Adds the specified action to the list of actions to be executed and updates the corresponding counter. Moreover, 
     * the current bounds are deleted.
     * @param action The action to add.
     */
    private void addAction(GraphicAction action) {
        synchronized (this) {
            this.actions.add(action);
            if (action.drawsSomething()) {
                this.draws++;
            }
            this.bounds = null;
        }
    }

    /**
     * Action to choose a color.
     */
    private class ChooseColor extends GraphicAction {

        /**
         * The color to choose.
         */
        private final Color color;

        /**
         * @param c The color to choose.
         */
        ChooseColor(Color c) {
            this.color = c;
        }

        @Override
        public boolean drawsSomething() {
            return false;
        }

        @Override
        void replay(Graphics2D g, boolean draw, Rectangle boundsParam) {
            g.setColor(this.color);
        }

    }

    /**
     * Action to draw a line forward.
     */
    private class DrawForward extends MoveForward {

        /**
         * @param length The length of the line to draw.
         */
        DrawForward(int length) {
            super(length);
        }

        @Override
        public boolean drawsSomething() {
            return true;
        }

        @Override
        void replay(Graphics2D g, boolean draw, Rectangle boundsParam) {
            if (draw) {
                g.drawLine(0, 0, 0, this.length);
            }
            super.replay(g, draw, boundsParam);
        }

    }

    /**
     * Action to draw a square.
     */
    private class DrawSquare extends GraphicAction {

        /**
         * The length of the square.
         */
        private final double length;

        /**
         * @param length The length of the square.
         */
        DrawSquare(double length) {
            this.length = length;
        }

        @Override
        void replay(Graphics2D g, boolean draw, Rectangle boundsParam) {
            double hl = this.length / 2;
            Rectangle2D r = new Rectangle2D.Double();
            r.setFrame(-hl, -hl, this.length, this.length);
            g.fill(r);
            if (boundsParam != null) {
                Canvas.addToBounds(r, boundsParam, g);
            }
        }

    }

    /**
     * Action to draw a text label.
     */
    private class DrawTextLabel extends GraphicAction {

        /**
         * The text of the label.
         */
        private final String text;

        /**
         * @param text The text of the label.
         */
        DrawTextLabel(String text) {
            this.text = text;
        }

        @Override
        void replay(Graphics2D g, boolean draw, Rectangle boundsParam) {
            AffineTransform origTrans = g.getTransform();
            Point2D center = origTrans.transform(new Point2D.Double(0, 0), null);
            FontRenderContext frc = g.getFontRenderContext();
            Font f = g.getFont();
            TextLayout tl = new TextLayout(this.text, f, frc);
            double w = tl.getBounds().getWidth();
            double h = tl.getBounds().getHeight();
            // move the text to the center of the box
            AffineTransform trans = new AffineTransform();
            trans.setToTranslation(Math.round(center.getX() - w / 2), Math.round(center.getY() + h / 2));
            g.setTransform(trans);
            // draw the box
            Rectangle r = new Rectangle(0, (int)-h, (int)w, (int)h);
            if (draw) {
                Shape textShape = tl.getOutline(new AffineTransform());
                g.setColor(Color.BLACK);
                r.grow(4, 4);
                g.setColor(Color.WHITE);
                g.fill(r);
                g.setColor(Color.BLACK);
                g.draw(r);
                g.fill(textShape);
            }
            if (boundsParam != null) {
                Canvas.addToBounds(r, boundsParam, g);
            }
            // restore original orientation
            g.setTransform(origTrans);
        }

    }

    /**
     * An action for our canvas.
     */
    private abstract class GraphicAction {

        /**
         * @return True if this action actually draws something. False otherwise. Needs to be overridden by actions not 
         * drawing something.
         */
        public boolean drawsSomething() {
            return true;
        }

        /**
         * The drawing method used in this canvas.
         * @param g The graphics object to draw on.
         * @param draw Flag to indicate whether the drawing action should actually be displayed.
         * @param boundsParam The bounds of the canvas.
         */
        abstract void replay(Graphics2D g, boolean draw, Rectangle boundsParam);

    }

    /**
     * Action for moving the current position.
     */
    private class Move extends GraphicAction {

        /**
         * The measure to move right.
         */
        private final double x;

        /**
         * The measure to move down.
         */
        private final double y;

        /**
         * @param x The measure to move right.
         * @param y The measure to move down.
         */
        Move(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean drawsSomething() {
            return false;
        }

        @Override
        void replay(Graphics2D g, boolean draw, Rectangle boundsParam) {
            g.translate(this.x, this.y);
            if (boundsParam != null) {
                Canvas.addToBounds(new Point2D.Double(this.x, this.y), boundsParam, g);
            }
        }

    }

    /**
     * Action for moving forward.
     */
    private class MoveForward extends GraphicAction {

        /**
         * The number of units to move forward.
         */
        final int length;

        /**
         * @param length The number of units to move forward.
         */
        MoveForward(int length) {
            this.length = length;
        }

        @Override
        public boolean drawsSomething() {
            return false;
        }

        @Override
        void replay(Graphics2D g, boolean draw, Rectangle boundsParam) {
            Canvas.addCurrentPos(g, boundsParam);
            AffineTransform t = g.getTransform();
            t.translate(0, this.length);
            g.setTransform(t);
            Canvas.addCurrentPos(g, boundsParam);
        }

    }

    /**
     * Action for restoring a position and orientation.
     */
    private class Pop extends GraphicAction {

        @Override
        void replay(Graphics2D g, boolean draw, Rectangle boundsParam) {
            g.setTransform(Canvas.this.transformations.pop());
        }

    }

    /**
     * Action for storing the current position and orientation.
     */
    private class Push extends GraphicAction {

        @Override
        void replay(Graphics2D g, boolean draw, Rectangle boundsParam) {
            Canvas.this.transformations.push(g.getTransform());
        }

    }

    /**
     * Action for rotation the current orientation.
     */
    private class Rotate extends GraphicAction {

        /**
         * The degrees to turn the current orientation.
         */
        final int degree;

        /**
         * @param degree The degrees to turn the current orientation.
         */
        Rotate(int degree) {
            this.degree = degree;
        }

        @Override
        public boolean drawsSomething() {
            return false;
        }

        @Override
        void replay(Graphics2D g, boolean draw, Rectangle boundsParam) {
            AffineTransform t = g.getTransform();
            t.rotate(this.degree / 180.0 * Math.PI);
            g.setTransform(t);
        }

    }

}
