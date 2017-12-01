package alkfejl.bead.fileshare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity {

    /*@JoinColumn
    @ManyToOne(targetEntity = User.class)
    private User reporter;
*/
    @JoinColumn
    @ManyToOne(targetEntity = File.class)
    private File commentedFile;

    @Column(nullable = false)
    private String text;

    @JoinColumn(nullable = false)
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column
    private Date date = new Date();

    @Override
    public String toString() {
        return this.date + " - " + this.user + ": " + this.text;
    }
}