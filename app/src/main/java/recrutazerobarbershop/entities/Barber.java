package recrutazerobarbershop.entities;

import java.time.Duration;
import java.time.Instant;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import recrutazerobarbershop.entities.Barbershop.BarbershopCase;
import recrutazerobarbershop.entities.Customer.CustomerCategory;

enum BarbersTag {
	RECRUTA_ZERO,
	DENTINHO, 
	OTTO 
}

public class Barber implements Runnable {

	private BarbersTag barberTag;
	Barbershop barbershop;

  private static final Map<BarbersTag, CustomerCategory> barberCategoryMap = new EnumMap<>(BarbersTag.class);

	static {
		barberCategoryMap.put(BarbersTag.RECRUTA_ZERO, CustomerCategory.OFFICER);
		barberCategoryMap.put(BarbersTag.DENTINHO, CustomerCategory.SERGEANT);
		barberCategoryMap.put(BarbersTag.OTTO, CustomerCategory.CORPORAL);
	}

  public Barber(BarbersTag barberTag, Barbershop barbershop) {
		this.barbershop = barbershop;
		this.barberTag = barberTag;
	}

	@Override
	public void run() {
		System.out.printf("The barber %s arrived at the barbershop.\n", barberTag.name());
		
    Customer currentCustomer = null;

		while (true) {

			if (barbershop.allQueuesEmpty() && Tainha.isFinished()) {
        System.out.printf("The barber %s left the barbershop.\n", barberTag.name());
				return;
			}

			System.out.printf("The barber %s is waiting for customers...\n", barberTag.name());

			try {
				Barbershop.barbersSemaphore.acquire();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			currentCustomer = getNextCustomerToServe();
			if (currentCustomer == null) {
				continue;
			}

			currentCustomer.setEndTime(Instant.now());
			currentCustomer.setElapsedTime(Duration.between(currentCustomer.getStartTime(), currentCustomer.getEndTime()));

			System.out.printf("The %s started the service of a %s [id=%d].\n", barberTag.name(),
					currentCustomer.getCutomerCategory().name(), currentCustomer.getId());

			try {
				Thread.sleep(currentCustomer.getCutHairTime() * 1000); // Simula o corte de cabelo
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			System.out.printf("The %s has finished serving customer %s [id=%d].\n", barberTag.name(),
					currentCustomer.getCutomerCategory().name(), currentCustomer.getId());

			if ( currentCustomer.getCutomerCategory() == CustomerCategory.CORPORAL )
				barbershop.corporalAmount++;
			else if ( currentCustomer.getCutomerCategory() == CustomerCategory.OFFICER)
				barbershop.officerAmount++;
			else if ( currentCustomer.getCutomerCategory() == CustomerCategory.SERGEANT)
				barbershop.sergeantAmount++;

			Barbershop.customersSemaphore.release(); // Libera o cliente após o serviço
		}
  }

  private Customer getNextCustomerToServe() {
		Map<CustomerCategory, Queue<Customer>> categoryQueueMap = new HashMap<>();
		categoryQueueMap.put(CustomerCategory.OFFICER, barbershop.officerQueue);
		categoryQueueMap.put(CustomerCategory.SERGEANT, barbershop.sergeantQueue);
		categoryQueueMap.put(CustomerCategory.CORPORAL, barbershop.corporalQueue);

		BarbershopCase barbershopCase = barbershop.getBarbershopCase();

		if (barbershopCase == BarbershopCase.A || barbershopCase == BarbershopCase.B) {
			for (BarbersTag barbersTag : BarbersTag.values()) {
				if (!categoryQueueMap.get(barberCategoryMap.get(barbersTag)).isEmpty()) {
					return categoryQueueMap.get(barberCategoryMap.get(barbersTag)).poll();
				}
			}
		} else if (barbershopCase == BarbershopCase.C) {
			if (!categoryQueueMap.get(barberCategoryMap.get(barberTag)).isEmpty()) {
				return categoryQueueMap.get(barberCategoryMap.get(barberTag)).poll();
			} else {
				for (BarbersTag barbersTag : BarbersTag.values()) {
					if (!categoryQueueMap.get(barberCategoryMap.get(barbersTag)).isEmpty()) {
						return categoryQueueMap.get(barberCategoryMap.get(barbersTag)).poll();
					}
				}
			}
		}
		return null;
	}

}
