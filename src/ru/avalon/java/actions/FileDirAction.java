package ru.avalon.java.actions;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ru.avalon.java.Lab3.monitor;

/**
 * Действие, которое копирует файлы в пределах дискового
 * пространства.
 */
public class FileDirAction implements Action {

    private Path path2source;

    public FileDirAction(String source) {
        path2source = Paths.get(source);
    }


    private void dir() throws IOException {

        if (!Files.exists(path2source)) {
            System.out.println("There is no file with such name in source directory!" + "\n" + "Please, type correct file name.\n");
        }
        else {
            System.out.println(path2source.getFileName() + "/");

            DirectoryStream<Path> stream = Files.newDirectoryStream(path2source);
            for (Path files : stream) {
                System.out.print("\t\n - " + files.getFileName() + "\t");
            }
            System.out.println("\n");

            stream.close();
        }

    }
    
    @Override
    public void run() {
        
            /*
            * TODO №2 Реализуйте метод run класса FileCopyAction
            */
        synchronized(monitor){
        try {   
            dir();
        } catch (IOException ex) {
            System.out.println("Directory is empty!\n");
            System.out.println(ex.getMessage());
        }
        monitor.notifyAll();
    }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        /*
         * TODO №3 Реализуйте метод close класса FileCopyAction
         */
    /*
    *throw new UnsupportedOperationException("Not implemented yet!");
    */
}
}
