import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class car {
    private String cardid;

    private String brand;

    private String model;

    private double basepriceperday;

    private boolean isavailable;

    public car(String carid, String brand, String model, double basepriceperday) {
        this.cardid = carid;
        this.brand = brand;
        this.model = model;
        this.basepriceperday = basepriceperday;
        this.isavailable = true;
    }

    public String getcarid() {
        return cardid;
    }

    public String getbrand() {
        return brand;

    }

    public String getmodel() {
        return model;
    }

    public double calculateprice(int rentalDays) {

        return basepriceperday + rentalDays;
    }

    public boolean isavailable() {
        return isavailable;
    }

    public void rent() {
        isavailable = false;
    }

    public void returncar() {
        isavailable = true;
    }
}

class customer {
    private String customerid;
    private String name;

    public customer(String customerid, String name) {
        this.customerid = customerid;
        this.name = name;
    }

    public String getcustomerid() {
        return customerid;
    }

    public String getname() {
        return name;
    }
}

class Rental {

    private car car;
    private customer customer;
    private int days;

    public Rental(car car, customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public car getCar() {
        return car;
    }

    public customer getCustomer() {
        return customer;
    }

    public int getdays() {
        return days;
    }
}

class CarRentalSystem {
    private List<car> cars;
    private List<customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(car car) {
        cars.add(car);
    }

    public void addcustomer(customer customer) {
        customers.add(customer);
    }

    public void rentcar(car car, customer customer, int days) {
        if (car.isavailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is not available for rent. ");
        }
    }

    public void returncar(car car) {
        car.returncar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            System.out.println("Car returned succesfully. ");
        } else {
            System.out.println("Car was not rented. ");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("***** Car Rental System *****");
            System.out.println("1. Rent a Car ");
            System.out.println("2. Return a Car ");
            System.out.println("3. Exit ");
            System.out.println(" Enter your Choice ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.println("\n** Rent a Car **\n");
                System.out.println("Enter your name: ");
                String customername = sc.nextLine();

                System.out.println("\nAvailable Cars: ");
                for (car car : cars) {
                    if (car.isavailable()) {
                        System.out.println(car.getcarid() + " - " + car.getbrand() + " - " + car.getmodel());
                    }
                }
                System.out.println("\n Enter the car ID you want to rent: ");
                String carid = sc.nextLine();

                System.out.println("\n Enter the number of days for rental: ");
                int rentalDays = sc.nextInt();
                sc.nextLine();

                customer newCustomer = new customer( "CUS" + (customers.size() + 1), customername);
                addcustomer(newCustomer);

                car selectedcar = null;
                for (car car : cars) {
                    if (car.getcarid().equals(carid) && car.isavailable()) {
                        selectedcar = car;
                        break;
                    }
                }
                if (selectedcar != null) {
                    double totalprice = selectedcar.calculateprice(rentalDays);
                    System.out.println("\n== Rental Information ==");
                    System.out.println("Customer ID: " + newCustomer.getcustomerid());
                    System.out.println("Customer Name: " + newCustomer.getname());
                    System.out.println("Car: " + selectedcar.getbrand() + " " + selectedcar.getmodel());
                    System.out.println("Rental days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f\n", totalprice);

                    System.out.println("\nConfirm Rental (Y/N): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y"))
                        ;
                    rentcar(selectedcar, newCustomer, rentalDays);
                    System.out.println("\nCar rented successfully. ");
                } else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n** Return a car ** \n");
                System.out.println("Enter the car id you want to return: ");
                String carid = sc.nextLine();
                car cartoreturn = null;
                for (car car : cars) {
                    if (car.getcarid().equals(carid) && !car.isavailable()) {
                        cartoreturn = car;
                        break;
                    }
                }
                if (cartoreturn != null) {
                    customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == cartoreturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if(customer !=null){
                        returncar(cartoreturn);
                        System.out.println("Car returned successfullt by "+ customer.getname());

                    } else{
                        System.out.println("Car was not rented or renal information is missing.");
            
                    }
                 } else{
                        System.out.println("Invalid car id or car is not rented.");
                    }
                }
                    else if(choice==3){
                      break;
                    } else{
                      System.out.println("Invalid choice. Please enter a valid option.");
                    }
                }
                System.out.println("Thank you for using the car rental system!");
            }
        }
    


public class RentalCar {
    public static void main(String[] args) {
CarRentalSystem rental=new CarRentalSystem();
car car1=new car("C001", "mahindra", "Scorpio", 1200);
car car2=new car("C002", "Mercedes","Maybach", 12000);
car car3=new car("C003", "Skoda", "Taigun", 1500);
rental.addCar(car1);
rental.addCar(car2);
rental.addCar(car3);

rental.menu();
    }
}
