package ru.avalon.java.actions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static ru.avalon.java.Lab3.monitor;

/**
 * Действие, которое перемещает файлы в пределах дискового
 * пространства.
 */
public class FileMoveAction implements Action {
    /**
     * {@inheritDoc}
     */
    private String name;
    private Path path2source;
    private Path path2target;

    public FileMoveAction(String source, String target) {
        String[] splitter = source.split("/");
        name = splitter[splitter.length - 1];
        path2source = Paths.get(source);
        path2target = Paths.get(target + "/"+ name);
    }
        
    private void move() throws IOException {

        if (!Files.exists(path2source)) {
            System.out.println("There is no file with such name in source directory!" + "\n" + "Please, type correct file name.\n");
            return;
        }         
        if (Files.exists(path2target)) {
            System.out.println("There is already file called " + name + "\n");
            return;
        }
        else {
            Files.move(path2source, path2target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Move operation completed.\n");
        }

    }
        
    
    @Override
    public void run() {
        /*
         * TODO №4 Реализуйте метод run класса FileMoveAction
         */
        synchronized(monitor){
        try {   
            move();
        } catch (IOException ex) {
            System.out.println("File move operation failed!\n");
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
         * TODO №5 Реализуйте метод close класса FileMoveAction
         */
    /*
    *throw new UnsupportedOperationException("Not implemented yet!");
    */
    }
}    
