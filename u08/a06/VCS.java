import java.io.*;
import java.util.*;

public class VCS {

    private String rootDir;

    public VCS(String dir) {
        this.rootDir = dir;
        if (!new File(getBackupDir()).exists()) {
            Util.mkdir(getBackupDir());
            System.out.println("initialized empty repository");
        }
    }

    /**
     * @return the backup directory of the version control system
     */
    public String getBackupDir() {
        return Util.appendFileOrDirname(rootDir, "vcs");
    }

    /**
     * @return the root directory of the version control system
     */
    public String getRootDir() {
        return rootDir;
    }

}
