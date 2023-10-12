package recrutazerobarbershop.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;


public class Barbershop {
  
  public static enum BarbershopCase { A, B, C }

	public static final int CHAIRS_AMOUNT = 5; /** Total number of chairs in the barbershop */

	private List<Thread> customerThreadList; /** List of client threads */
	private List<Thread> barberThreadList; /** List of barbers threads */

	public static int tainhaSleepingTime; /** Sergeant Tainha's sleep time */

  public Queue<Customer> officerQueue = new LinkedList<>(); /** Officer Queue */
	public Queue<Customer> sergeantQueue = new LinkedList<>(); /** Sergeant Queue */
	public Queue<Customer> corporalQueue = new LinkedList<>(); /** Corporal Queue */

	public static Semaphore customersSemaphore; /** Customer Semaphore */
	public static Semaphore barbersSemaphore; /** Barbers Semaphore */
	public static Semaphore mutex; /** Semaphore to control access to an exclusive area. */

	public static int barberAmount; /** Number of barbers */

	public static boolean barberShopIsClosed = false; /** Flag indicating if the barbershop is closed */
	public int officerAmount = 0; /** Official category customer amount */
	public int sergeantAmount = 0; /** Customer amount for the "Sergeant" category */
	public int corporalAmount = 0; /** Customer counter for the "Corporal" category */
	public int breakAmount = 0; /** Paused customer amount*/

	public BarbershopCase barbershopCase; /** Test case for the barbershop */

  private List<Customer> customersList; /** Customer List */

	public Barbershop() {
		customerThreadList = new ArrayList<>();
		barberThreadList = new ArrayList<>();
		customersSemaphore = new Semaphore(0, true);
		barbersSemaphore = new Semaphore(0, true);
		mutex = new Semaphore(1, true);
	}

  public void setCustomersList(List<Customer> customersList) {
    this.customersList = customersList;
  }

  public static void setTainhaSleepingTime(int tainhaSleepingTime) {
    Barbershop.tainhaSleepingTime = tainhaSleepingTime;
  }

  public static int getTainhaSleepingTime() {
    return tainhaSleepingTime;
  }

	public BarbershopCase getBarbershopCase() {
		return barbershopCase;
	}

	public List<Thread> getBarberThreadList() {
		return barberThreadList;
	}

	public List<Thread> getCustomerThreadList() {
		return customerThreadList;
	}

  public List<Customer> getCustomersList() {
    return customersList;
  }

  public boolean allQueuesEmpty() {
		return officerQueue.isEmpty() && sergeantQueue.isEmpty()
				&& corporalQueue.isEmpty();
	}

  public void start(BarbershopCase barbershopCase) {
    System.out.printf("\n*************************************\n");   
    System.out.printf("*          Open Barbershop          *\n");
    System.out.printf("*************************************\n");

    this.barbershopCase = barbershopCase;

    switch ( barbershopCase ) {
      case A: { barberAmount = 1; break; }      
      case B: { barberAmount = 2; break; }
      case C: { barberAmount = 3; break; }
    }

    for (int i = 0; i < barberAmount; i++) {
			Barber barber = new Barber(BarbersTag.values()[i], this);
			Thread t = new Thread(barber);
			barberThreadList.add(t);

			t.start();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

    Thread tainhaThread = new Thread(new Tainha(this));
    tainhaThread.start();

    try {
			tainhaThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Thread customerThread : customerThreadList) {
			try {
				customerThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (Thread barberThread : barberThreadList) {
			barberThread.interrupt();
		}

		barberShopIsClosed = true;
  }
}
