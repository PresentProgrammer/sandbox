package present.programmer.hibernate.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "LIBRARY")
@Data
@SuppressWarnings("JpaDataSourceORMInspection")
public class Library {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "library_id")
    private Set<Book> books = new HashSet<>();
}
