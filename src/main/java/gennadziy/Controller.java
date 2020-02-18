package gennadziy;

import com.google.gson.Gson;
import gennadziy.model.Dom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

@Autowired
private  DomRepo domRepo;

    @RequestMapping(value = "/api/all")
    public String getAll(){
        Gson json = new Gson ();
        String str=json.toJson ( domRepo.findAll() ).concat ( "NDFSBFVSDFVSDFSDj" );
        //    return domRepo.findAll();
    return str;
    }


    @RequestMapping(value = "/api/{id}")
    public Optional <Dom> getOne( @PathVariable("id") Long id, Dom dom ) {

    return domRepo.findById ( id );

    }
}