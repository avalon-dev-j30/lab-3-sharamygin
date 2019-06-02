package ru.avalon.java.actions;

import java.io.IOException;
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
public class FileDeleteAction implements Action {

    private Path path2source;

    public FileDeleteAction(String source) {
        path2source = Paths.get(source);
    }

    private void delete() throws IOException {

        if (!Files.exists(path2source)) {
            System.out.println("There is no file with such name in source directory!" + "\n" + "Please, type correct file name.\n");
        } 
        
        else {
            Files.delete(path2source);
            System.out.println("Delete operation completed.\n");
        }

    }
    
    @Override
    public void run() {
        
            /*
            * TODO №2 Реализуйте метод run класса FileCopyAction
            */
        synchronized(monitor){
        try {   
            delete();
        } catch (IOException ex) {
            System.out.println("File delete operation failed!\n");
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
