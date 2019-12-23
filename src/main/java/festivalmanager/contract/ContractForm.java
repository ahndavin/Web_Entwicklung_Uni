package festivalmanager.contract;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ContractForm {

	@NotEmpty
	private String name;

	@NotEmpty
	private String artist;

	@Min(0)
	private int price;

    private String accepted;

	@Min(0)
	private int technicianscount;

	@Min(0)
	private int workinghours;

	@Min(0)
    private int workerswage;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getArtist(){
        return artist;
    }
    
    public void setArtist(String artist){
        this.artist = artist;
    }

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public String getAccepted(){
        return accepted;
    }

    public void setAccepted(String accepted){
        this.accepted = accepted;
    }

    public int getTechniciansCount(){
        System.out.print(technicianscount);
        return this.technicianscount;
    }

    public void setTechniciansCount(int technicianscount){
        this.technicianscount = technicianscount;
    }

    public int getWorkingHours(){
        System.out.print(workinghours);
        return this.workinghours;
    }

    public void setWorkingHours(int workinghours){
        this.workinghours = workinghours;
    }

    public int getWorkersWage(){
        System.out.print(workerswage);
        return this.workerswage;
    }

    public void setWorkersWage(int workerswage){
        this.workerswage = workerswage;
    }
}