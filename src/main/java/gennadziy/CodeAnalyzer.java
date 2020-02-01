package gennadziy;

import java.util.Arrays;
import java.util.Date;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: CodeAnalyzer
Date: 2020-02-01
Time: 14:05
*/
public class CodeAnalyzer {

    public static void analyzeClass ( Object o ) {
        Class clazz = o.getClass ( );
        System.out.println ( clazz );
        System.out.println("Имя класса: " + clazz);
        System.out.println("Поля класса: " + Arrays.toString(clazz.getDeclaredFields()));
        System.out.println("Родительский класс: " + clazz.getSuperclass());
        System.out.println("Методы класса: " +  Arrays.toString(clazz.getDeclaredMethods()));
        System.out.println("Конструкторы класса: " + Arrays.toString(clazz.getConstructors()));
    }

        public static void main (String[] args){

            analyzeClass (new Date ());
        }

}