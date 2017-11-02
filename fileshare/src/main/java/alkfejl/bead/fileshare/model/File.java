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
    private String path;

    @Column(nullable = false)
    private int editLevel;

    @Column(nullable = false)
    private int viewLevel;

    @Column(nullable = false)
    private int owner;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getEditLevel() {
        return editLevel;
    }

    public void setEditLevel(int editLevel) {
        this.editLevel = editLevel;
    }

    public int getViewLevel() {
        return viewLevel;
    }

    public void setViewLevel(int viewLevel) {
        this.viewLevel = viewLevel;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
