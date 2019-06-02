package ru.avalon.java;

import java.io.BufferedReader;
import ru.avalon.java.console.ConsoleUI;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.avalon.java.actions.FileCopyAction;
import ru.avalon.java.actions.FileDeleteAction;
import ru.avalon.java.actions.FileDirAction;
import ru.avalon.java.actions.FileMoveAction;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "Программирование на платформе Java. Разработка
 * многоуровневых приложений"
 * <p>
 * Тема: "Потоки исполнения (Threads) и многозадачность" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Lab3 extends ConsoleUI<Commands> {
    /**
     * Точка входа в приложение.
     * 
     * @param args 
     */
    
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public final static Object monitor = new Object();
    private String path2source;
    private String path2target;
    
    public static void main(String[] args) {
        System.out.println("Type start to examine available commands.\n");
        new Lab3().run();
    }
    /**
     * Конструктор класса.
     * <p>
     * Инициализирует экземпляр базового типа с использоавнием
     * перечисления {@link Commands}.
     */
    Lab3() {
        super(Commands.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) throws IOException {
    try {
        synchronized(monitor){
        switch (command) {
            case copy:
                /*
                 * TODO №6 Обработайте команду copy
                 */
                System.out.println("\nEnter path to file you want to copy.");
                path2source = reader.readLine();
                System.out.println("Now enter path to destiantion directory.");
                path2target = reader.readLine();
                FileCopyAction copy = new FileCopyAction(path2source, path2target);
                copy.start();
                monitor.wait();
                break;
                
            case move:
                /*
                 * TODO №7 Обработайте команду move
                 */
                System.out.println("\nEnter path to file you want to move.");
                path2source = reader.readLine();
                System.out.println("Now enter path to destiantion directory.");
                path2target = reader.readLine();
                FileMoveAction move = new FileMoveAction(path2source, path2target);
                move.start();
                monitor.wait();
                break;
                
            case delete:
                System.out.println("\nEnter path to file you want to delete.");
                path2source = reader.readLine();
                FileDeleteAction delete = new FileDeleteAction(path2source);
                delete.start();
                monitor.wait();
                break;                
                
            case dir:
                System.out.println("\nEnter path to directory.");
                path2source = reader.readLine();
                FileDirAction dir = new FileDirAction(path2source);
                dir.start();
                monitor.wait();
                break;                 
                
            case exit:
                System.out.println("\nThe programm will now be terminated.");
                close();
                break;
                /*
                 * TODO №9 Обработайте необработанные команды
                 */
            
            case start:
                System.out.println("\nAvailable commands are:\n" + "\t- copy\n" + "\t- move\n" + "\t- delete\n" + "\t- dir\n" + "\t- exit\n");
                break;
        }
        }
    } catch (InterruptedException ex) {
        System.out.println(ex.getMessage());
            }
    }  
}
