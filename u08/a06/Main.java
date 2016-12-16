import java.io.*;
import java.util.*;

public class Main {

    /**
     * @param args the only expected argument is the path to the root directory
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("wrong number of arguments");
        } else {
            File f = new File(args[0]);
            if (!f.exists()) {
                System.out.println(args[0] + " does not exist");
            } else if (!f.isDirectory()) {
                System.out.println(args[0] + " is not a directory");
            } else if (!f.canWrite()) {
                System.out.println(args[0] + " is read-only");
            } else {
                VCS vcs = new VCS(args[0]);
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("> ");
                    Command cmd = Command.parse(scanner.nextLine(), vcs);
                    if (cmd != null) {
                        cmd.execute();
                    }
                }
            }
        }
    }

}
