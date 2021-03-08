package gennadziy.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: Granica
Date: 2020-02-15
Time: 19:48
*/
@Entity
@Table(name = "granica")
@Data
public class Granica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "time")
    @DateTimeFormat(pattern = "HH:dd")
    public LocalTime getDate1(LocalTime localTime) {
        return localTime.now();
    }

    private LocalTime time;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;


    @Column
    private String bytes;

}
