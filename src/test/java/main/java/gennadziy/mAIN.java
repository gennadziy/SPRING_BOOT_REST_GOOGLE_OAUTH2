package gennadziy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: mAIN
Date: 2020-02-25
Time: 15:39
*/
public class mAIN {
    public static void main ( String[] args ) throws IOException, ClassNotFoundException {

            Class cls=Class.forName ( "java.io.IOException" );
        IOException fileWriter=new IOException ( );
            Class c=fileWriter.getClass ();
        System.out.println (c);
        System.out.println (Arrays.toString ( c.getDeclaredMethods () ) );
        }
}
