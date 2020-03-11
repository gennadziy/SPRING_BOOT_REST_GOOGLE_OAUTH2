package gennadziy.model;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: Dom
Date: 2020-01-31
Time: 20:57
*/

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;
@Data
@Entity
@Table(name="dom")
public class Dom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


   @Column(name = "fact")
    private String cat1;
    @Column
    @NotEmpty(message = "not empty")
    private String name;



    private LocalTime date;
    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }



    public LocalTime getDate () {
        return date;
    }
    @Column(name = "date")
    @DateTimeFormat(pattern = "HH:dd")
    public LocalTime getDate1 (LocalTime localTime) {
        return  localTime.now ();
    }

    public void setDate ( LocalTime date ) {
        this.date = date;
    }

    @Override
    public String toString () {
        return "Dom{" +
                "id=" + id +
                ", cat1='" + cat1 + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public String getCat1 () {
        return cat1;
    }

    public void setCat1 ( String cat1 ) {
        this.cat1 = cat1;
    }

    public Long getId () {

        return id;

    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Dom () {

    }
}
