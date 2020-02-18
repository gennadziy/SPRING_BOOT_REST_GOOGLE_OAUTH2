package gennadziy.model;

import org.apache.commons.io.FileUtils;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

public class Main {
//
    public static void main ( String[] args ) throws ClassNotFoundException, IOException {
//
//        String nameF="C:/"+new SimpleDateFormat ("yyyy-mm-dd_hh-mm-ss").format(new Date())+".jpg";
//        Image bufferimage = ImageIO.read(new URL ("http://www.brest.customs.gov.by/webcam/brst112_c1.jpg"));
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        ImageIO.write( (RenderedImage) bufferimage, "jpg", output );
//        byte [] data = output.toByteArray();
//
//        System.out.println (data );
//        ByteArrayInputStream bis = new ByteArrayInputStream (data);
//        BufferedImage bImage2 = ImageIO.read(bis);
//        ImageIO.write(bImage2, "jpg", new File (nameF) );
//        System.out.println("image created");
//        byte[] fileContent = FileUtils.readFileToByteArray(new File(nameF));
//        String encodedString = Base64.getEncoder().encodeToString(fileContent);
//        System.out.println (encodedString);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        URL url= new URL ("http://www.brest.customs.gov.by/webcam/brst112_c1.jpg");

            is = url.openStream ();
            byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
            int n;

            while ( (n = is.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }
        String encodedString = Base64.getEncoder().encodeToString(byteChunk);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        FileUtils.writeByteArrayToFile(new File("C:/111.jpg"), decodedBytes);
        }
    }

