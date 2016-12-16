import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Util {

    /**
     * Creates a new file- or directory name by appending "fileOrDirname" to "dirname".
     * Takes care of the operating-system specific file-separator which has to be in between (e.g., "/" on Linux, but "\" on Windows)
     * @return a new file- or directory name, pointing to the "fileOrDirname" in the directory "dirname"
     */
    public static String appendFileOrDirname(String dirname, String fileOrDirname) {
        return new File(dirname + File.separator + fileOrDirname).getAbsolutePath();
    }

    /**
     * Creates the directory "dirname".
     * Fails (and enforces termination) if creating "dirname" fails.
     */
    public static void mkdir(String dirname) {
        if (!new File(dirname).mkdir()) {
            System.err.println("error creating directory " + dirname);
            System.exit(-1);
        }
    }

    /**
     * Moves the file "srcFilename" to "destFilename".
     * Fails (and enforces termination) if moving fails
     * (e.g., if "destFilename" already exists).
     */
    public static void moveFile(String srcFilename, String destFilename) {
        try {
            Files.move(new File(srcFilename).toPath(), new File(destFilename).toPath());
        } catch (IOException e) {
            System.err.println("error writing " + srcFilename + " to " + destFilename);
            System.exit(-1);
        }
    }

    /**
     * Copies the file "srcFilename" to "destFilename".
     * Fails (and enforces termination) if copying fails
     * (e.g., if "destFilename" already exists).
     */
    public static void copyFile(String srcFilename, String destFilename) {
        try {
            Files.copy(new File(srcFilename).toPath(), new File(destFilename).toPath());
        } catch (IOException e) {
            System.err.println("error writing " + srcFilename + " to " + destFilename);
            System.exit(-1);
        }
    }

    /**
     * @return a string which uniquely identifies a point in time (search for "Unix timestamp" for further information)
     */
    public static String getTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * @return the names of all files (excluding directories) contained in the directory "dirname"
     */
    public static String[] listFiles(String dirname) {
        List<String> filenames = new ArrayList<>();
        for (File f: new File(dirname).listFiles()) {
            if (!f.isDirectory()) {
                filenames.add(f.getName());
            }
        }
        return filenames.toArray(new String[filenames.size()]);
    }

    /**
     * Terminates the program.
     */
    public static void exit() {
        System.exit(0);
    }

}
