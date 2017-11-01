package alkfejl.bead.fileshare.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;

        @Version
        private int version;
}
