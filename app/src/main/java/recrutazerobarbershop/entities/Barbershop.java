package recrutazerobarbershop.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Barbershop {
	public static final int CHAIRS_AMOUNT = 5; /** Total number of chairs in the barbershop */

	private List<Thread> customerThreadList; /** List of client threads */
	private List<Thread> barberThreadList; /** List of barbers threads */

	public static int tainhaSleepingTime; /** Sergeant Tainha's sleep time */

	public static Semaphore customersSemaphore; /** Customer Semaphore */
	public static Semaphore barbersSemaphore; /** Barbers Semaphore */
	public static Semaphore mutex; /** Semaphore to control access to an exclusive area. */

	public static int barberAmount; /** Number of barbers */

	public static boolean barberShopIsClosed = false; /** Flag indicating if the barbershop is closed */
	public int officerAmount = 0; /** Official category customer amount */
	public int sergeantAmount = 0; /** Customer amount for the "Sergeant" category */
	public int corporalAmount = 0; /** Customer counter for the "Corporal" category */
	public int breakAmount = 0; /** Paused customer amount*/

	public char barbershopCase; /** Test case for the barbershop */

	public Barbershop() {
		customerThreadList = new ArrayList<>();
		barberThreadList = new ArrayList<>();
		customersSemaphore = new Semaphore(0, true);
		barbersSemaphore = new Semaphore(0, true);
		mutex = new Semaphore(1, true);
	}

	public void setBarbershopCase(char barbershopCase) {
		this.barbershopCase = barbershopCase;
	}

	public char getBarbershopCase() {
		return barbershopCase;
	}

	public List<Thread> getBarberThreadList() {
		return barberThreadList;
	}

	public List<Thread> getCustomerThreadList() {
		return customerThreadList;
	}

  public void start() {
    System.out.printf("*************************************\n");   
    System.out.printf("*          Open Barbershop          *\n");
    System.out.printf("*************************************\n");
  }
}
