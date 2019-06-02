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
public class FileCopyAction implements Action {

    private String name;
    private Path path2source;
    private Path path2target;

    public FileCopyAction(String source, String target) {
        String[] splitter = source.split("/");
        name = splitter[splitter.length - 1];
        path2source = Paths.get(source);
        path2target = Paths.get(target + "/"+ name);
    }

    private void copy() throws IOException {

        if (!Files.exists(path2source)) {
            System.out.println("There is no file with such name in source directory!" + "\n" + "Please, type correct file name.\n");
            return;
        } 
        if (Files.exists(path2target)) {
            System.out.println("There is already file called " + name + "\n");
            return;
        }
        else {
            Files.copy(path2source, path2target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copy operation completed.\n");
        }

    }
    
    @Override
    public void run() {
        
            /*
            * TODO №2 Реализуйте метод run класса FileCopyAction
            */
        synchronized(monitor){
        try {   
            copy();
        } catch (IOException ex) {
            System.out.println("File copy operation failed!\n");
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
