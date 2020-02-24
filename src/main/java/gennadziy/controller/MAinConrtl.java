package gennadziy.controller;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: MAinConrtl
Date: 2020-01-31
Time: 21:04
*/

import com.google.gson.Gson;
import gennadziy.config.DbInsertion;
import gennadziy.dao.DomRepo;
import gennadziy.dao.GranicaRepo;
import gennadziy.dao.KursyRepo;
import gennadziy.model.Dom;
import gennadziy.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class MAinConrtl {

    @Autowired
    private DomRepo domRepo;
    @Autowired
    private KursyRepo kursWalut;
    @Autowired
    private GranicaRepo granicaRepo;

    @GetMapping("/")
    public String hello(Model model)throws IOException {
        URL url=new URL ( "http://cat-fact.herokuapp.com/facts/random" );
        InputStreamReader reader=new InputStreamReader ( url.openStream () );
        CatFact catFact= new Gson ().fromJson ( reader, CatFact.class );
        String cat1=catFact.getText ();
        model.addAttribute ( "cat1",cat1 );
        System.out.println (cat1);
        return "home";
    }

    @GetMapping("/new")
    public  String addDom(Model model) throws IOException{
        URL url=new URL ( "http://cat-fact.herokuapp.com/facts/random" );
        InputStreamReader reader=new InputStreamReader ( url.openStream () );
        CatFact catFact1= new Gson ().fromJson ( reader, CatFact.class );
        String cat2=catFact1.getText ();
        model.addAttribute ( "cat2",cat2 );
        System.out.println (cat2);
        return "new";
    }

    @GetMapping("/valut")
    public  String valut(Model model){
        List<KursWalut> kurs=kursWalut.findAll ();
        model.addAttribute ( "kurs",kurs );
        System.out.println ( kurs);
        return "valut";
    }

    @GetMapping("/valut/delete/{id}")
    public  String addValut(@PathVariable("id") Integer id, KursWalut kursWalut1){
       kursWalut.deleteById ( id );
        return "redirect:/valut";
    }


    @PostMapping("/add")
    public String addW( Model model ) {
        Model model1 = model.addAttribute ( "db", new DbInsertion () );
        log.info("POST ZAPROS");
        return "redirect:/valut";
    }
    @PostMapping("/save")
    public String addStudent( Dom dom, BindingResult bindingResult, Model model ) {
        if(dom==null ){
            return "new";
        }
        if (bindingResult.hasErrors ( )) {
            return "new";
        }
        domRepo.save ( dom );
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String listDom(Model model){
        List<Dom> doms=domRepo.findAll ();
model.addAttribute ( "doms", doms );
        return "index";
    }
    @GetMapping("/delete/{id}")
    public String deleteDom(@PathVariable("id" ) Long id, Dom dom){
        domRepo.deleteById ( id );
        return "redirect:/main";
    }


    @PostMapping("/saveGr")
    public String granicaSav(Model model,Granica granica) throws IOException {
            granicaRepo.save ( granica );

            return "redirect:/granica";

    }

    @GetMapping("/granica")
    public String granica( Model model) throws IOException {
        List<Granica> list=granicaRepo.findAll ( );
        model.addAttribute ( "granica",list );
        String nameF="C:/"+new SimpleDateFormat ("yyyy-mm-dd_hh-mm-ss").format(new Date())+".jpg";
        Image bufferimage = ImageIO.read(new URL ("http://www.brest.customs.gov.by/webcam/brst112_c1.jpg"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write( (RenderedImage) bufferimage, "jpg", output );
        byte [] data = output.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream (data);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg", new File (nameF) );
        byte[] fileContent = FileUtils.readFileToByteArray(new File(nameF));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        model.addAttribute ( "data", encodedString );
        System.out.println (encodedString );
        return "granica";
    }

    @GetMapping("/granica/delete/{id}")
    public String deleteGranica(@PathVariable("id" ) Long id){
        granicaRepo.deleteById ( id );
        return "redirect:/granica";
    }

    @GetMapping("/view/{id}")
    public String viewGranica( @PathVariable("id" ) Long id,Granica granica, Model model) {
            String s=granicaRepo.findById ( id ).toString ();
        model.addAttribute ( "bytes", Optional.ofNullable(s)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(59, str.length() - 2))
                .orElse(s));
        model.addAttribute ( "granic", granicaRepo.findById ( id ) );
        return "view";
    }
}



