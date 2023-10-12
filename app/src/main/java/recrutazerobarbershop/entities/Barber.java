package recrutazerobarbershop.entities;

enum BarbersTag {
	RECRUTA_ZERO,
	DENTINHO, 
	OTTO 
}

public class Barber implements Runnable {

	private BarbersTag barberTag;
	Barbershop barbershop;

  public Barber(BarbersTag barberTag, Barbershop barbershop) {
		this.barbershop = barbershop;
		this.barberTag = barberTag;
	}

	@Override
	public void run() {
		System.out.printf("The barber %s arrived at the barbershop.\n", barberTag.name());
		System.out.printf("The barber %s left the barbershop.\n", barberTag.name());
	}

}
