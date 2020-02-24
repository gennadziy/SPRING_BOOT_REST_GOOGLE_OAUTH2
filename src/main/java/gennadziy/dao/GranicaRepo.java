package gennadziy.dao;

import gennadziy.model.Granica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.util.Optional;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: GranicaRepo
Date: 2020-02-15
Time: 19:55
*/
public interface GranicaRepo extends JpaRepository<Granica, Long > {
//
//    @Query(value = "select bytes from granica ", nativeQuery = true)
//    Granica findUserById(@Param("id") Long id);

}
