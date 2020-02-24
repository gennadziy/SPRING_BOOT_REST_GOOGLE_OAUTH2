package gennadziy.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

@Slf4j
public class DbInsertion extends Thread  {

    // JDBC driver name and database URL local disk database.
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=Europe/Minsk&useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "251284251284";


    public static void main(String[] args) {
        System.out.println (getAllStackTraces ().toString ());
        URL url;

        try {
            //get URL content
            url = new URL("http://www.nbrb.by/api/exrates/rates/145");
            URLConnection conn = url.openConnection();
            //http://www.nbrb.by/api/exrates/rates?periodicity=0
            //open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;

            //save to this filename
            String fileName = "C:/1.json";
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            //use FileWriter to write file
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            while ((inputLine = br.readLine()) != null) {
                bw.write(inputLine);
            }

            bw.close();
            br.close();

            System.out.println("Done");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection mysqlConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connection to database...");
            mysqlConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String query = " insert into mytable (Cur_ID, Date, Cur_Abbreviation, Cur_Scale, Cur_Name," +
                    " cur_official_rate) values (?, ?, ?, ?, ?, ?)";
            System.out.println("Creating sql statement...");
            preparedStatement = mysqlConnection.prepareStatement(query);
            mysqlConnection.setAutoCommit(false);
            getJsonStrings(mysqlConnection, preparedStatement);
            preparedStatement.close();
            mysqlConnection.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void getJsonStrings(Connection mySqlConnection, PreparedStatement preparedStatement) {
        LinkedHashSet<String> jsonLineSet = new LinkedHashSet<>();
        String reviewer = null;
        try {
            FileInputStream inputStream = new FileInputStream( "C://1.json" );
            Scanner sc = new Scanner(inputStream);
            while(sc.hasNextLine()) {
                for (int i = 0; i < 1; i++) {
                    if (sc.hasNextLine()) {
                        reviewer = sc.nextLine();
                        jsonLineSet.add(reviewer);
                    } else {
                        System.out.println("Done!");
                        insertData(mySqlConnection, preparedStatement, jsonLineSet);
                        break;
                    }
                }
                insertData(mySqlConnection, preparedStatement, jsonLineSet);
            }
            sc.close();
        }catch(IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void insertData(Connection mySqlConnection,
                                  PreparedStatement preparedStatement, LinkedHashSet<String> jsonLineSet) {
        for(String jsonLine : jsonLineSet) {
            try {
                System.out.println("Executing query...");
                JsonElement jsonElement = new JsonParser().parse(jsonLine);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                int id = jsonObject.get("Cur_ID").getAsInt ();
                String date = jsonObject.get("Date").getAsString();
                String Cur_Abbreviation = jsonObject.get("Cur_Abbreviation").getAsString();
                int Cur_Scale = jsonObject.get("Cur_Scale").getAsInt ();
                String Cur_Name = jsonObject.get("Cur_Name").getAsString();
                double Cur_OfficialRate = jsonObject.get("Cur_OfficialRate").getAsDouble ();

//                // Printing for debugging purposes.
//                System.out.println(id);
//                System.out.println(date);
//                System.out.println(Cur_Abbreviation);
//                System.out.println(Cur_Scale);
//                System.out.println(Cur_Name);
//                System.out.println(Cur_OfficialRate);

                // Insert statement.
                preparedStatement.setInt (1, id);
                preparedStatement.setString(2, date);
                preparedStatement.setString(3, Cur_Abbreviation);
                preparedStatement.setInt (4, Cur_Scale);
                preparedStatement.setString(5, Cur_Name);
                preparedStatement.setDouble (6, Cur_OfficialRate);
                preparedStatement.addBatch();
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
        try {
            preparedStatement.executeBatch();
            mySqlConnection.commit();
            preparedStatement.clearBatch();
        }catch(SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void run () {
        DbInsertion db = new DbInsertion ();
        db.run ();
        super.run ();
    }
}