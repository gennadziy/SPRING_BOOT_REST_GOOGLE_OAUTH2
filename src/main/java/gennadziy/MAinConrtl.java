package gennadziy;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: MAinConrtl
Date: 2020-01-31
Time: 21:04
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Time;
import java.util.List;

import static org.aspectj.bridge.Version.getTime;

@Controller
public class MAinConrtl {
    @Autowired
    private DomRepo domRepo;

    @GetMapping("/")
    public String hello(){
        return "home";
    }

    @GetMapping("/new")
    public  String addDom(Model model){
        return "new";
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


