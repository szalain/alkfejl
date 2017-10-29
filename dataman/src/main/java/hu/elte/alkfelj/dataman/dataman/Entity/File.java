package hu.elte.alkfelj.dataman.dataman.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class File {
    @GeneratedValue
    @Id
    private Long ID;
    private String path;
    private int editLevel;
    private int viewLevel;

}
