package gennadziy.service;

import gennadziy.dao.DomRepo;
import gennadziy.model.Dom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: DomServiceImpl
Date: 2020-02-24
Time: 19:18
*/

@Service
@Transactional
public class DomServiceImpl implements DomService {

    @Autowired
    private DomRepo domRepo;
    @Override
    public List<Dom> getAllDom () {
        return this.domRepo.findAll ();
    }
}
