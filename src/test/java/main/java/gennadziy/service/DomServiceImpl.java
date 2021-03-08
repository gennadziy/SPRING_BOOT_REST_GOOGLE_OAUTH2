package gennadziy.service;

import gennadziy.dao.DomRepo;
import gennadziy.model.Dom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DomServiceImpl implements DomService {

    @Autowired
    private DomRepo domRepo;
    @Override
    public List<Dom> getAllDom () {
        return this.domRepo.findAll ();
    }

    @Override
    public Optional <Dom> getId ( Long id ) throws ReflectiveOperationException {
        Optional <Dom> dom=domRepo.findById ( id );
        if(dom==null){
            throw new ReflectiveOperationException (  );
        }
        return dom;
    }
}
