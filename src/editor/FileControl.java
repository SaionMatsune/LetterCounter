package editor;

import java.io.File;


public class FileControl {
    private static FileControl instance = new FileControl();
    private static File fileM;
    
    private FileControl() {
        fileM = null;
    }
    
    public static FileControl getSingleton() {
        return instance;
    }

    public static void setFileName(File file) {
        fileM = file;
    }
    
    public static File getFileName() {
        return fileM;
    }
    
}
