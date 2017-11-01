package alkfejl.bead.fileshare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int uploadCount = 0;

    @Column(nullable = false)
    private boolean banned = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Role role;

    public enum Role {
        GUEST, USER, MOD, ADMIN
    }
}