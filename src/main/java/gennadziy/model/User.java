package gennadziy.model;

/*
@Author Gennadziy GITHUB/gennadziy
Class name: User
Date: 2020-03-11
Time: 17:37
*/

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "userssssssss")
public class User {
    @Id
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String local;
    private LocalDateTime lastVisit;


}
