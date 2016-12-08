import java.util.Random;
import java.nio.file.*;
import java.io.*;

/**
 * Implementiert einen sortierten Binaerbaum mit Rotation-zur-Wurzel Optimierung.
 */
public class Tree {
  /**
   * Wurzel des Baums
   */
  private TreeNode root;

  /**
   * Erstellt einen leeren Baum
   */
  public Tree () {
    this.root = null;
  }

  /**
   * Erstellt einen Baum mit den vorgegebenen Zahlen.
   */
  public Tree(int ... xs) {
    for (int x : xs) {
      this.insert(x);
    }
  }

  /**
   * Test ob der Baum leer ist
   * @return true, falls der Baum leer ist, sonst false
   */
  public boolean isEmpty() {
    return this.root == null;
  }

  /**
   * Fuegt eine Zahl ein. Keine Aenderung, wenn das Element
   * schon enthalten ist.
   * @param x einzufuegende Zahl
   */
  public void insert(int x) {
    if (isEmpty()) {
      root = new TreeNode(x);
    } else {
      root.insert(x);
    }
  }

  /**
   * Sucht x, ohne den Baum zu veraendern.
   * @return true, falls x im Baum enthalten ist, sonst false
   */
  public boolean simpleSearch(int x) {
    if (!isEmpty()) {
      return this.root.simpleSearch(x);
    } else {
      return false;
    }
  }

  /**
   * Sucht x und rotiert den Knoten, bei dem die Suche nach x endet, in die Wurzel.
   * @return true, falls x im Baum enthalten ist, sonst false
   */
  public boolean search(int x) {
           TreeNode result = root.rotationSearch(x);
         
        if(result != null) {
            root = result;
            return true;
        }
        else {
            return false;
        }

      }
  /**
   * Sortierte Ausgabe aller Elemente.
   */
  public String toString() {
    String str=("tree[");
    if(!isEmpty()){
      str += root.toString();
    }
    str +="]";
    return str;
  }

  /**
   * Wandelt den Baum in einen Graphen im dot Format um.
   */
  public String toDot() {
    if (this.isEmpty()) {
      return "digraph { null[shape=point]; }";
    }
    StringBuilder str = new StringBuilder();
    this.root.toDot(str, 0);
    return "digraph {" + System.lineSeparator()
           + "graph [ordering=\"out\"];" + System.lineSeparator()
           + str.toString()
           + "}" + System.lineSeparator();
  }

  /**
   * Speichert die dot Repraesentation in einer Datei.
   *
   * @param path Pfad unter dem gespeichert werden soll (Dateiname)
   * @return true, falls erfolgreich gespeichert wurde, sonst false
   * @see toDot
   */
  public boolean writeToFile(String path) {
    boolean retval = true;
    try {
      Files.write(FileSystems.getDefault().getPath(path), this.toDot().getBytes());
    } catch (IOException x) {
      System.err.println("Es ist ein Fehler aufgetreten.");
      System.err.format("IOException: %s%n", x);
      retval = false;
    }
    return retval;
  }

  /**
   * Main-Methode, die einige Teile der Aufgabe testet.
   *
   * @param args Liste von Dateinamen unter denen Baeume als dot
   * gespeichert werden sollen. Es werden nur die ersten beiden verwendet.
   */
  public static void main(String[] args) {
    Random prng = new Random();

    int nodeCount = prng.nextInt(10) + 5;
    Tree myTree = new Tree();
    System.out.println("Aufgabe b): Zufaelliges Einfuegen");
    for (int i = 0; i < nodeCount; ++i) {
      myTree.insert(prng.nextInt(30));
    }

    myTree.insert(15);
    myTree.insert(3);
    myTree.insert(23);

    if (args.length > 0) {
      if (myTree.writeToFile(args[0])) {
        System.out.println("Baum als DOT File ausgegeben in Datei " + args[0]);
      }
    } else {
      System.out.println("Keine Ausgabe des Baums in Datei, zu wenige Aufrufparameter.");
    }

    System.out.println("Aufgabe a): Suchen nach zufaelligen Elementen");
    for (int i = 0; i < nodeCount; ++i) {
      int x = prng.nextInt(30);
      if (myTree.simpleSearch(x)) {
        System.out.println(x + " ist enthalten");
      } else {
        System.out.println(x + " ist nicht enthalten");
      }
    }

    System.out.println("Aufgabe c): geordnete String-Ausgabe");
    System.out.println(myTree.toString());

    System.out.println("Aufgabe d): Suchen nach vorhandenen Elementen mit Rotation.");
    myTree.search(3);
    myTree.search(23);
    myTree.search(15);
    if (args.length > 1) {
      if (myTree.writeToFile(args[1])) {
        System.out.println("Baum nach Suchen von 15, 3 und 23 als DOT File ausgegeben in Datei "
                           + args[1]);
      }
    } else {
      System.out.println("Keine Ausgabe des Baums in Datei, zu wenige Aufrufparameter.");
    }

  }


}
