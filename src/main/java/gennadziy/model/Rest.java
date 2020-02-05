package gennadziy.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: Rest
Date: 2020-02-04
Time: 20:20
*/
public class Rest {
    public static void main ( String[] args ) throws IOException {
        URL url=new URL ( "http://cat-fact.herokuapp.com/facts/random" );
        InputStreamReader reader=new InputStreamReader ( url.openStream () );
        CatFact catFact=new Gson ().fromJson ( reader, CatFact.class );
        System.out.println (catFact.getText () );

//        URL url1=new URL ( "https://aws.random.cat/meow" );
//        InputStreamReader reader1=new InputStreamReader ( url1.openStream () );
//        JsonObject object= new JsonParser ().parse(reader1).getAsJsonObject ();
//        System.out.println (object.get("file").getAsString());
    }
}
