package recrutazerobarbershop.entities;

import recrutazerobarbershop.entities.Customer.CustomerCategory;

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

    CustomerCategory customerCategory;
		int zerosAmount = 0;
		int i = 0;

    while ( i < barbershop.getCustomersList().size() && zerosAmount < 3 ) {
			try {
				Thread.sleep(Barbershop.tainhaSleepingTime * 1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			customerCategory = barbershop.getCustomersList().get(i).getCutomerCategory();

			if (customerCategory == CustomerCategory.PAUSE) {
				zerosAmount++;
			} else {
				System.out.printf("O Sargento Tainha adicionou um novo cliente.\n");
				Thread t = new Thread(barbershop.getCustomersList().get(i));
				barbershop.getCustomerThreadList().add(t);
				t.start();
				zerosAmount = 0; 
			}

			i++;
		}

		finished = true;
		System.out.printf("Sergeant Tainha has left!\n");
	}
}
