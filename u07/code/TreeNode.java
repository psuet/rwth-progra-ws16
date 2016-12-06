/**
 * Ein Konten in einem binaeren Baum.
 *
 * Der gespeicherte Wert ist unveraenderlich,
 * die Referenzen auf die Nachfolger koennen aber
 * geaendert werden.
 *
 * Die Klasse bietet Methoden, um Werte aus einem Baum
 * zu suchen, einzufuegen und zu loeschen. Diese gibt
 * es jeweils noch in optimierten Varianten, um
 * rotate-to-root Baeume zu verwalten.
 */
public class TreeNode {
  /**
   * Linker Nachfolger
   */
  private TreeNode left;
  /**
   * Rechter Nachfolger
   */
  private TreeNode right;
  /**
   * Wert, der in diesem Knoten gespeichert ist
   */
  private final int value;

  /**
   * Erzeugt einen neuen Knoten ohne Nachfolger
   */
  public TreeNode(int val) {
    this.value = val;
    this.left = null;
    this.right = null;
  }

  /**
   * Erzeugt einen neuen Knoten mit den gegebenen Nachfolgern
   */
  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.value = val;
    this.left = left;
    this.right = right;
  }

  public int getValue() {
    return this.value;
  }

  /**
   * Der gespeicherte Wert, umgewandelt in einen String
   */
  public String getValueString() {
    return Integer.toString(this.value);
  }

  public boolean hasLeft() {
    return this.left != null;
  }

  public boolean hasRight() {
    return this.right != null;
  }

  public TreeNode getLeft() {
    return this.left;
  }

  public TreeNode getRight() {
    return this.right;
  }

  /**
   * Sucht in diesem Teilbaum nach x, ohne den Baum zu veraendern.
   * @return true, falls x enthalten ist, sonst false
   */
  public boolean simpleSearch(int x) {
    if (this.value == x) {
      return true;
    } else {
      if (this.value < x && this.left != null) {
        return this.left.simpleSearch(x);
      } else if (this.value > x && this.right != null) {
        return this.right.simpleSearch(x);
      } else {
        return false;
      }
    }
  }

  /**
   * Fuegt x in diesen Teilbaum ein.
   */
  public void insert(int x) {
    if (this.value != x) {
      if (this.value < x) {
        if (this.left != null) {
          this.left.insert(x);
        } else {
          this.left = new TreeNode(x);
        }
      } else {
        if (this.right != null) {
          this.right.insert(x);
        } else {
          this.right = new TreeNode(x);
        }
      }
    }
  }

  /**
   * Sucht in diesem Teilbaum nach x und rotiert den Endpunkt der Suche in die
   * Wurzel.
   * @param x der gesuchte Wert
   * @return die neue Wurzel des Teilbaums
   */
  public TreeNode rotationSearch(int x) {
    //TODO d)
    return null;
    //Ende TODO
  }

  /**
   * Geordnete Liste aller Zahlen, die in diesem Teilbaum gespeichert sind.
   */
  public String toString() {
    //TODO c) a 
    return "TreeNode.toString() nicht implementiert!";
    //Ende TODO
  }

  /**
   * Erzeugt eine dot Repraesentation in str
   */
  public int toDot(StringBuilder str, int nullNodes) {
    if (this.hasLeft()) {
      str.append(this.getValueString() + " -> " + this.left.getValueString() + ";"
                 + System.lineSeparator());
      nullNodes = this.left.toDot(str, nullNodes);
    } else {
      str.append("null" + nullNodes + "[shape=point]" + System.lineSeparator()
                 + this.getValueString() + " -> null" + nullNodes + ";" + System.lineSeparator());
      nullNodes += 1;
    }
    if (this.hasRight()) {
      str.append(this.getValueString() + " -> " + this.right.getValueString() + ";"
                 + System.lineSeparator());
      nullNodes = this.right.toDot(str, nullNodes);
    } else {
      str.append("null" + nullNodes + "[shape=point]" + System.lineSeparator()
                 + this.getValueString() + " -> null" + nullNodes + ";" + System.lineSeparator());
      nullNodes += 1;
    }
    return nullNodes;
  }

}
