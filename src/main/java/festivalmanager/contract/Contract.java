package festivalmanager.contract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Name is mandatory")
	@Column(name = "name")
	private String name;

	@NotBlank(message = "Artist is mandatory")
	@Column(name = "artist")
	private String artist;

	@Column(name = "price")
	private String price;

	@NotBlank(message = "Accepted is mandatory")
	@Column(name = "accepted")
	private String accepted;

	@NotBlank(message = "Technicianscount is mandatory")
	@Column(name = "technicianscount")
	private String technicianscount;

	@NotBlank(message = "Workinghours are mandatory")
	@Column(name = "workinghours")
	private String workinghours;

	@NotBlank(message = "Workerswage are mandatory")
	@Column(name = "workerswage")
	private String workerswage;

	public Contract() {
	}

	public Contract(String name, String artist, String price, String accepted, String technicianscount,
					String workinghours, String workerswage) {
		this.name = name;
		this.artist = artist;
		this.price = price;
		this.accepted = accepted;
		this.technicianscount = technicianscount;
		this.workinghours = workinghours;
		this.workerswage = workerswage;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAccepted() {
		return accepted;
	}

	public String getWorkerswage() {
		return workerswage;
	}

	public void setWorkerswage(String workerswage) {
		this.workerswage = workerswage;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public String getTechnicianscount() {
		return technicianscount;
	}

	public String getWorkinghours() {
		return workinghours;
	}

	public void setWorkinghours(String workinghours) {
		this.workinghours = workinghours;
	}

	public void setTechnicianscount(String technicianscount) {
		this.technicianscount = technicianscount;
	}
}