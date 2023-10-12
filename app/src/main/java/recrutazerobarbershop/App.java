/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package recrutazerobarbershop;

import java.util.ArrayList;
import java.util.Scanner;

import recrutazerobarbershop.entities.Barbershop;
import recrutazerobarbershop.entities.Barbershop.BarbershopCase;
import recrutazerobarbershop.entities.Customer;

public class App {

  static Barbershop barbershop = new Barbershop();
  static BarbershopCase barbershopCase = BarbershopCase.A;

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Customer> customers = new ArrayList<>();
    int tainhaSleepingTime = 1, id = 0;

    for ( int i = 0; i < args.length; i++ ) {
      if (args[i].equals("--tainhaSleepingTime")) {
        if ( i + 1 < args.length ) {
          try {
            tainhaSleepingTime = Integer.parseInt(args[i + 1]);
            i++;
          } catch (NumberFormatException e) {
            System.err.println("Error: The value of --tainhaSleepingTime is not an integer.");
          }
        } else {
          System.err.println("Error: The --tainhaSleepingTime option requires an integer value.");
        }
      } else if (args[i].equals("--barbershopCase")) {
        if (i + 1 < args.length) {
          switch( args[i + 1] ) {
            case "A": { barbershopCase = BarbershopCase.A; break; }
            case "B": { barbershopCase = BarbershopCase.B; break; }
            case "C": { barbershopCase = BarbershopCase.C; break; }
          }
          i++; 
        } else {
          System.err.println("Error: The --barbershopCase option requires a value.");
        }
      }
    }

    while ( scanner.hasNext() ) {
			int category = scanner.nextInt();
			int cutHairTime = scanner.nextInt();

			Customer customer = new Customer(barbershop);
			customer.setCategory(category);
			customer.setCutHairTime(cutHairTime);
			customer.setId(id++);
    	customers.add(customer);
		}

    System.out.println("Arguments: [tainhaSleepingTime=" + tainhaSleepingTime + "; barbershopCase=" + barbershopCase + "]");

    Barbershop.setTainhaSleepingTime(tainhaSleepingTime);
    barbershop.setCustomersList(customers);
    barbershop.start(barbershopCase);

    scanner.close();
  }
}
