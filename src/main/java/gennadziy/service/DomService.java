package gennadziy.service;

import gennadziy.model.Dom;
import java.util.List;
import java.util.Optional;

public interface DomService {
    List<Dom> getAllDom();
    public Optional <Dom> getId( Long id) throws ReflectiveOperationException;
}
