package guru.tour.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the warning database table.
 * 
 */
@Entity
@Table(name="warning")
@NamedQuery(name="WarningEntity.findAll", query="SELECT w FROM WarningEntity w")
public class WarningEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

	private String name;

	//bi-directional many-to-one association to LocationEntity
	@ManyToOne
	@JoinColumn(name="local_id")
	private LocationEntity location;

	public WarningEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationEntity getLocation() {
		return this.location;
	}

	public void setLocation(LocationEntity location) {
		this.location = location;
	}

}