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
		private int price;

		@Column(name = "accepted")
		private boolean accepted;

		@Column(name = "technicianscount")
		private int technicianscount;

		@Column(name = "workinghours")
		private int workinghours;

		@Column(name = "workerswage")
		private int workerswage;

		public Contract(){}

		public Contract(String name, String artist, int price, boolean accepted, int technicianscount,
						int workinghours, int workerswage) {
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

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public boolean getAccepted() {
			return accepted;
		}

		public int getWorkerswage() {
			return workerswage;
		}

		public void setWorkerswage(int workerswage) {
			this.workerswage = workerswage;
		}

		public void setAccepted(boolean accepted) {
			this.accepted = accepted;
		}

		public int getTechnicianscount() {
			return technicianscount;
		}

		public int getWorkinghours() {
			return workinghours;
		}

		public void setWorkinghours(int workinghours) {
			this.workinghours = workinghours;
		}

		public void setTechnicianscount(int technicianscount) {
			this.technicianscount = technicianscount;
		}

		public int totalCost(){
			return this.price + this.workinghours * this.workerswage * this.technicianscount;
		}

		public String toString(){
			return this.artist;
		}
}
