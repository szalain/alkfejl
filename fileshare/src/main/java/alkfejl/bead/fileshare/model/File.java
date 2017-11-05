package alkfejl.bead.fileshare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "FILE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class File extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String fullPath;

    @Column(nullable = false)
    private String path;

    @Column
    private String fileName;

    @Column(nullable = false)
    private int editLevel;

    @Column(nullable = false)
    private int viewLevel;

    @Column(nullable = false)
    private int owner;

    @Column
    private boolean isDir;

    @Override
    public String toString(){
        if(!(this.isDir)) {
            return this.fileName;
        } else {
            return this.fileName;
        }
    }

    public String URL(){
        if(!(this.isDir)) {
            return "/uploadFiles/files" + this.fullPath;

        } else {
            return "/uploadFiles"+this.fullPath;
        }
    }

}
