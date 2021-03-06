package alkfejl.bead.fileshare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REPORT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Report extends BaseEntity {

    /*@JoinColumn
    @ManyToOne(targetEntity = User.class)
    private User reporter;
*/
    @JoinColumn
    @ManyToOne(targetEntity = User.class)
    private User reported;

    @Column(nullable = false)
    private String description;

    @Column
    private Date date = new Date();
}