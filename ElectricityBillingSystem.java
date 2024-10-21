import java.util.ArrayList;
import java.util.Scanner;

class Customer {
    private int customerId;
    private String customerName;
    private int unitsConsumed;

    public Customer(int customerId, String customerName, int unitsConsumed) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.unitsConsumed = unitsConsumed;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + customerName + ", Units Consumed: " + unitsConsumed;
    }
}

public class ElectricityBillingSystem {
    private ArrayList<Customer> customers;
    private Scanner sc;

    public ElectricityBillingSystem() {
        customers = new ArrayList<>();
        sc = new Scanner(System.in);

    }

    public void addCustomer() {
        System.out.println("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Customer Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Units Consumed: ");
        int Units = sc.nextInt();

        customers.add(new Customer(id, name, Units));
        System.out.println("Customer added successfully.");

    }

    public void calculatebill() {
        System.out.println("Enter customer ID to calculate the bill:");
        int id = sc.nextInt();
        Customer customer = findCustomerByID(id);

        if (customer != null) {
            int units = customer.getUnitsConsumed();
            double billAmount = calculatebillAmount(units);
            System.out.println("Bill for " + customer.getCustomerName() + ": $" + billAmount);
        } else {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }

    private Customer findCustomerByID(int id) {
        
    }

    private double calculatebillAmount(int units) {
        double rate;

        if (units <= 100) {
            rate = 1.20;
        } else if (units <= 300) {
            rate = 2.00;
        } else {
            rate = 3.00;
        }
        return units * rate;
    }

    public void viewCustomers(){
        if(customers.isEmpty()){
            System.out.println("No customers in the system.");
        }else{
            for(Customer customer:customers){
                System.out.println(customer);
            }
        }
       public Customer findCustomerByID(int id){
for(Customer customer:customers){
    if(customer.getCustomerId()==id){
        return customer;
    }
}
return null;
        }
    }
    }

public class BillingSystem {
    public static void main(String[] args) {

    }
}