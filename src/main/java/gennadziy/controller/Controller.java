package gennadziy.controller;

import gennadziy.dao.DomRepo;
import gennadziy.service.DomService;
import gennadziy.exception.ResourceNotFoundException;
import gennadziy.model.Dom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private DomRepo domRepo;
    @Autowired
    private DomService domService;

    @RequestMapping(value = "/api/all")
    public ResponseEntity<List <Dom>> getAll ( Long id ) {
        //        Gson json = new Gson ();
//        String str=json.toJson ( domRepo.findAll());
//        return domRepo.findAll ( );
//    return str;
        return ResponseEntity.ok ().body(domService.getAllDom ());
    }


    @RequestMapping(value = "/api/{id}")
    public ResponseEntity <Optional <Dom>> getOne ( @PathVariable("id") Long id, Dom dom ) {
        Optional <Dom> dom1 = domRepo.findById ( id );
        if (dom1.isPresent ( )) {
            return ResponseEntity.ok ( ).body ( domRepo.findById ( id ) );
        }
        else {
            throw new ResourceNotFoundException ("нукт такого ID : " + id );
    }
}
}
