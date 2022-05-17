package FilePractice.java;

import javax.swing.*;
import java.io.File;
import java.time.LocalDate;


public class FilePractice {

    public static void main(String[] args) {
        //getDataFolderFromSystem();
        File dir = new File("E:/");
        System.out.println(LocalDate.now());
        File[] fileArray = dir.listFiles((dir1, name) -> name.endsWith(".csv"));
        for (File f : fileArray) {
            System.out.println(f.getName());
        }
    }

    private static File getDataFolderFromSystem() {
        JFileChooser chooser = new JFileChooser(".csv");
        chooser.setDragEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int choice = chooser.showOpenDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION) {
            System.out.println("File not selected");
        }
        return chooser.getSelectedFile();
    }
}
