package gennadziy.model;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@Table(name = "userssssssss")
public class User implements Serializable {
    private static final long serialVersionUID = -305726463442998984L;

    @Id
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String local;
    private LocalDateTime lastVisit;


}
