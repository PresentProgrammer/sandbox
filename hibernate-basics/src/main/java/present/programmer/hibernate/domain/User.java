package present.programmer.hibernate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("JpaDataSourceORMInspection")
public class User {

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "USER_NAME")
	@Basic()
	private String name;
}