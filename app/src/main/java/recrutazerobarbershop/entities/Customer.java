package recrutazerobarbershop.entities;

import java.time.Duration;
import java.time.Instant;

public class Customer implements Runnable {

  public static enum CustomerCategory {
    PAUSE,      
    OFFICER,  
    SERGEANT, 
    CORPORAL  
  }

	private CustomerCategory cutomerCategory;
	private Barbershop barbershop;
  private Duration elapsedTime;
	private Instant startTime;
	private Instant endTime;
	private int cutHairTime;
	private int id;

	public Customer(Barbershop barbershop) {
		this.barbershop = barbershop;
	}

	public CustomerCategory getCutomerCategory() {
		return cutomerCategory;
	}

	public void setCutomerCategory(CustomerCategory category) {
		this.cutomerCategory = category;
	}

	public void setCategory(int category) {
		this.cutomerCategory = CustomerCategory.values()[category];
	}

	public int getCutHairTime() {
		return cutHairTime;
	}

	public void setCutHairTime(int serviceTime) {
		this.cutHairTime = serviceTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public Instant getEndTime() {
		return endTime;
	}

	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	public Duration getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Duration elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	@Override
	public void run() {
		System.out.printf("Um %s [id=%d] chegou na barbearia.\n", cutomerCategory.name(), id);
		System.out.printf("Um %s [id=%d] saiu da barbearia\n", cutomerCategory.name(), id);
	}
}
