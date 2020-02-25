package gennadziy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: mAIN
Date: 2020-02-25
Time: 15:39
*/
public class mAIN {
    public static void main ( String[] args ) throws IOException {
        StringBuffer s=new StringBuffer ( "Wake up , Neo");
        String s1="         Wake up , Neo              ";
        System.out.println (s.append ( "fsdfsd" )
                .append ( 2342 )
                .delete ( 2,4 )
                .deleteCharAt ( 8 ) );
        System.out.println (s.equals ( s1 ) );
//        System.out.println (s.toUpperCase () );
//        System.out.println (s.trim () );
//        System.out.println (s.concat ( "dasdasd" ) );
//        System.out.println (s.substring ( 4,8 ) );
//        System.out.println (s.codePointBefore ( 3 ) );
        List<Integer> list=new ArrayList <> (  );
        list.add(3);
        list.add(3);
        list.add(3);
        list.add(3);
        list.forEach (System.out::print);
    }
}
