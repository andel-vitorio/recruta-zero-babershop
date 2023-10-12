package recrutazerobarbershop.entities;

public class Tainha implements Runnable {

	private static boolean finished = false;
	private Barbershop barbershop;

	public Tainha(Barbershop barbershop) {
		this.barbershop = barbershop;
	}

	public static boolean isFinished() {
		return finished;
	}

	@Override
	public void run() {
		System.out.printf("Sergeant Tainha has arrived!\n");
		finished = true;
		System.out.printf("Sergeant Tainha has left!\n");
	}
}
