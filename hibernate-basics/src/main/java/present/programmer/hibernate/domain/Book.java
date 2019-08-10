package present.programmer.hibernate.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "BOOK")
@Data
@SuppressWarnings("JpaDataSourceORMInspection")
public class Book {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    private String title;

    private String author;


    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals( id, book.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id );
    }
}
