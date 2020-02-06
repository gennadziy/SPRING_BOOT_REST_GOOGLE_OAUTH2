package gennadziy;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: MAinConrtl
Date: 2020-01-31
Time: 21:04
*/

import com.google.gson.Gson;
//import gennadziy.model.CatFact;
import gennadziy.model.CatFact;
import gennadziy.model.DbInsertion;
import gennadziy.model.Dom;
import gennadziy.model.KursWalut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
@Slf4j
@Controller
public class MAinConrtl {

    @Autowired
    private DomRepo domRepo;
    @Autowired
    private KursyRepo kursWalut;

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

    @PostMapping("/add")
    public String addW( Model model ) {
        Model model1 = model.addAttribute ( "db", new DbInsertion () );
        log.info("POST ZAPROS");
        return "redirect:/valut";
    }
    @PostMapping("/save")
    public String addStudent(Dom dom,BindingResult bindingResult, Model model ) {
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
}


