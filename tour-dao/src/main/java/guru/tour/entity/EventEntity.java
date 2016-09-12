package guru.tour.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@Table(name="event")
@NamedQuery(name="EventEntity.findAll", query="SELECT e FROM EventEntity e")
public class EventEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endday;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startday;

	//bi-directional many-to-one association to LocationEntity
	@ManyToOne
	@JoinColumn(name="local_id")
	private LocationEntity location;

	//bi-directional many-to-one association to AddressEntity
	@ManyToOne
	@JoinColumn(name="add_id")
	private AddressEntity address;

	public EventEntity() {
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

	public Date getEndday() {
		return this.endday;
	}

	public void setEndday(Date endday) {
		this.endday = endday;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartday() {
		return this.startday;
	}

	public void setStartday(Date startday) {
		this.startday = startday;
	}

	public LocationEntity getLocation() {
		return this.location;
	}

	public void setLocation(LocationEntity location) {
		this.location = location;
	}

	public AddressEntity getAddress() {
		return this.address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

}