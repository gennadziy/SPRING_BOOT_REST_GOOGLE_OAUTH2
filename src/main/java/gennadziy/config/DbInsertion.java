package gennadziy.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
@Component
public class DbInsertion extends Thread {
    public Object main() {
//        System.out.println (getAllStackTraces ().toString ());
        URL url;
        try {
            //get URL content
            url = new URL("http://www.nbrb.by/api/exrates/rates/145");
            log.info("POBRANE KURSE");
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            String fileName = "C:/Users/Marcin/Pictures/1.json";
            File file = new File(fileName);

            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            while ((inputLine = br.readLine()) != null) {
                bw.write(inputLine);
            }

            bw.close();
            br.close();

            System.out.println("Done");
            System.out.println();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection mysqlConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connection to database...");
            mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=Europe/Minsk&useSSL=false", "root", "root");
            String query = " insert into mytable (Cur_ID, Date, Cur_Abbreviation, Cur_Scale, Cur_Name," +
                    " cur_official_rate) values (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE Cur_ID = Cur_ID";
//           String query = "  ON DUPLICATE KEY UPDATE ";
            System.out.println("Creating sql statement...");
            System.out.println("**********");
            preparedStatement = mysqlConnection.prepareStatement(query);
            mysqlConnection.setAutoCommit(false);
            getJsonStrings(mysqlConnection, preparedStatement);
            preparedStatement.close();
            mysqlConnection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getJsonStrings(Connection mySqlConnection, PreparedStatement preparedStatement) {
        LinkedHashSet<String> jsonLineSet = new LinkedHashSet<>();
        String reviewer = null;
        try {
            FileInputStream inputStream = new FileInputStream("C:/Users/Marcin/Pictures/1.json");
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNextLine()) {
                for (int i = 0; i < 1; i++) {
                    if (sc.hasNextLine()) {
                        reviewer = sc.nextLine();
                        jsonLineSet.add(reviewer);
                    } else {
                        System.out.println("Done 145");
                        insertData(mySqlConnection, preparedStatement, jsonLineSet);
                        break;
                    }
                }
                insertData(mySqlConnection, preparedStatement, jsonLineSet);
            }
            sc.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void insertData(Connection mySqlConnection,
                                  PreparedStatement preparedStatement, LinkedHashSet<String> jsonLineSet) {
        for (String jsonLine : jsonLineSet) {
            try {
                System.out.println("Executing query...");
                JsonElement jsonElement = new JsonParser().parse(jsonLine);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                int id = jsonObject.get("Cur_ID").getAsInt();
                String date = jsonObject.get("Date").getAsString();
                String Cur_Abbreviation = jsonObject.get("Cur_Abbreviation").getAsString();
                int Cur_Scale = jsonObject.get("Cur_Scale").getAsInt();
                String Cur_Name = jsonObject.get("Cur_Name").getAsString();
                double Cur_OfficialRate = jsonObject.get("Cur_OfficialRate").getAsDouble();
                // Insert statement.
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, date);
                preparedStatement.setString(3, Cur_Abbreviation);
                preparedStatement.setInt(4, Cur_Scale);
                preparedStatement.setString(5, Cur_Name);
                preparedStatement.setDouble(6, Cur_OfficialRate);
                preparedStatement.addBatch();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        try {
            preparedStatement.executeBatch();
            mySqlConnection.commit();
            preparedStatement.clearBatch();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public Object main1() {
//        System.out.println (getAllStackTraces ().toString ());
        URL url;
        try {
            //get URL content
            url = new URL("http://www.nbrb.by/api/exrates/rates/298");
            log.info("POBRANE KURSE 298");
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            String fileName = "C:/Users/Marcin/Pictures/2.json";
            File file = new File(fileName);

            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            while ((inputLine = br.readLine()) != null) {
                bw.write(inputLine);
            }

            bw.close();
            br.close();

            System.out.println("Done");
            System.out.println();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection mysqlConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connection to database...");
            mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=Europe/Minsk&useSSL=false", "root", "root");
            String query = " insert into mytable (Cur_ID, Date, Cur_Abbreviation, Cur_Scale, Cur_Name," +
                    " cur_official_rate) values (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE Cur_ID = Cur_ID";
//           String query = "  ON DUPLICATE KEY UPDATE ";
            System.out.println("Creating sql statement...");
            System.out.println("**********");
            preparedStatement = mysqlConnection.prepareStatement(query);
            mysqlConnection.setAutoCommit(false);
            getJsonStrings1(mysqlConnection, preparedStatement);
            preparedStatement.close();
            mysqlConnection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getJsonStrings1(Connection mySqlConnection, PreparedStatement preparedStatement) {
        LinkedHashSet<String> jsonLineSet = new LinkedHashSet<>();
        String reviewer = null;
        try {
            FileInputStream inputStream = new FileInputStream("C:/Users/Marcin/Pictures/2.json");
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNextLine()) {
                for (int i = 0; i < 1; i++) {
                    if (sc.hasNextLine()) {
                        reviewer = sc.nextLine();
                        jsonLineSet.add(reviewer);
                    } else {
                        System.out.println("Done 298!");
                        insertData(mySqlConnection, preparedStatement, jsonLineSet);
                        break;
                    }
                }
                insertData1(mySqlConnection, preparedStatement, jsonLineSet);
            }
            sc.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void insertData1(Connection mySqlConnection,
                                   PreparedStatement preparedStatement, LinkedHashSet<String> jsonLineSet) {
        for (String jsonLine : jsonLineSet) {
            try {
                System.out.println("Executing query...");
                JsonElement jsonElement = new JsonParser().parse(jsonLine);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                int id = jsonObject.get("Cur_ID").getAsInt();
                String date = jsonObject.get("Date").getAsString();
                String Cur_Abbreviation = jsonObject.get("Cur_Abbreviation").getAsString();
                int Cur_Scale = jsonObject.get("Cur_Scale").getAsInt();
                String Cur_Name = jsonObject.get("Cur_Name").getAsString();
                double Cur_OfficialRate = jsonObject.get("Cur_OfficialRate").getAsDouble();
                // Insert statement.
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, date);
                preparedStatement.setString(3, Cur_Abbreviation);
                preparedStatement.setInt(4, Cur_Scale);
                preparedStatement.setString(5, Cur_Name);
                preparedStatement.setDouble(6, Cur_OfficialRate);
                preparedStatement.addBatch();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        try {
            preparedStatement.executeBatch();
            mySqlConnection.commit();
            preparedStatement.clearBatch();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
