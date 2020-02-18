package gennadziy;

import java.util.Arrays;
import java.util.Scanner;

public class CodeAnalyzer {
    public static void analyzeClass ( String string ) throws ClassNotFoundException {
        Class clazz = Class.forName ( string );
        System.out.println ( clazz );
         System.out.println("Поля класса: " + Arrays.toString(clazz.getDeclaredFields()));
        System.out.println("Родительский класс: " + clazz.getSuperclass());
//        System.out.println("Методы класса: " +  Arrays.toString(clazz.getDeclaredMethods()));
//        System.out.println("Конструкторы класса: " + Arrays.toString(clazz.getConstructors()));
    }
    public static void main (String[] args) throws ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        System.out.println ("Ввидеите назввание класса  ");
        analyzeClass (sc.nextLine ());
    }
}