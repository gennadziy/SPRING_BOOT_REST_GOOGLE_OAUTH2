package gennadziy.controller;

import gennadziy.dao.DomRepo;
import gennadziy.service.DomService;
import gennadziy.exception.ResourceNotFoundException;
import gennadziy.model.Dom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class Controller {

    @Autowired
    private DomRepo domRepo;
    @Autowired
    private DomService domService;


    @GetMapping("/12/{id}")
    public ResponseEntity<Dom> getOne(@PathVariable("id") Long id) {
        Dom dom = domRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("NOT FOUND ID ///" + id));
        return ResponseEntity.ok().body(dom);
    }

    @GetMapping(value = "/api/all")
    public ResponseEntity<List<Dom>> getAll(Long id) {
        return ResponseEntity.ok().body(domService.getAllDom());
    }


    @GetMapping(value = "/api/{id}")
    public ResponseEntity<Optional<Dom>> getOne(@PathVariable("id") Long id, Dom dom) {
        Optional<Dom> dom1 = domRepo.findById(id);
        if (dom1.isPresent()) {
            return ResponseEntity.ok().body(domRepo.findById(id));
        } else {
            throw new ResourceNotFoundException("нeт такого ID : " + id);
        }
    }
}
