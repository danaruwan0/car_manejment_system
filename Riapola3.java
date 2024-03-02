import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

class Riapola3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Riapola3 Car Management System");

        // Printing current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Date and Time: " + currentDateTime.format(formatter));

        // Initializing array to store car details
        CarShould[] carShoulds = new CarShould[50]; // Array to store CarShould objects

        int option;
        do {
            // Displaying menu options
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a car to the system");
            System.out.println("2. View all existing cars and details");
            System.out.println("3. View specific car details (Search a car by Id)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            option = scan.nextInt(); // Reading user choice

            switch (option) {
                case 1:
                    addCar(scan, carShoulds); // Invoking method to add a car
                    break;
                case 2:
                    viewAllCars(carShoulds); // Invoking method to view all cars
                    break;
                case 3:
                    viewSpecificCar(scan, carShoulds); // Invoking method to view specific car
                    break;
                case 4:
                    System.out.println("Exiting the program..."); // Exiting the program
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Displayed for invalid choices
            }
        } while (option != 4); // Loop continues until user chooses to exit
    }

    public static void addCar(Scanner scan, CarShould[] carShoulds) {
        // Adding a new car to the system
        System.out.println("\nEnter car details:");
        int id = generateRandomId(); // Generating random 10-bit ID
        System.out.println("Generated ID: " + id);
        scan.nextLine(); // Consume newline
        System.out.print("Enter brand: ");
        String brand = scan.nextLine(); // Reading car brand
        System.out.print("Enter model: ");
        String model = scan.nextLine(); // Reading car model
        System.out.print("Enter engine number: ");
        int engineNumber = scan.nextInt(); // Reading engine number
        System.out.print("Enter transmission mode (true/false): ");
        boolean transmissionMode = scan.nextBoolean(); // Reading transmission mode
        System.out.print("Does it have power shutter? (true/false): ");
        boolean isHavePowerShutter = scan.nextBoolean(); // Reading power shutter status

        CarShould car = new CarShould(); // Creating a new CarShould object
        car.id = id;
        car.brand = brand;
        car.model = model;
        car.engineNumber = engineNumber;
        car.transmissionMode = transmissionMode;
        car.isHavePowerShutter = isHavePowerShutter;

        // Finding the first available index and adding the car
        for (int i = 0; i < carShoulds.length; i++) {
            if (carShoulds[i] == null) {
                carShoulds[i] = car; // Adding the car to the array
                System.out.println("Car added successfully!"); // Confirmation message
                return;
            }
        }
        System.out.println("Failed to add car. System is full."); // Displayed if system is full
    }

    public static void viewAllCars(CarShould[] carShoulds) {
        // Displaying details of all existing cars
        System.out.println("\nAll Existing Cars and Details:");
        for (CarShould car : carShoulds) {
            if (car != null) {
                // Printing car details
                System.out.println("ID: " + car.id);
                System.out.println("Brand: " + car.brand);
                System.out.println("Model: " + car.model);
                System.out.println("Engine Number: " + car.engineNumber);
                System.out.println("Transmission Mode: " + (car.transmissionMode ? "Automatic" : "Manual"));
                System.out.println("Power Shutter: " + (car.isHavePowerShutter ? "Yes" : "No"));
                System.out.println("-----------------------");
            }
        }
    }

    public static void viewSpecificCar(Scanner scan, CarShould[] carShoulds) {
        // Viewing details of a specific car based on ID
        System.out.print("\nEnter car ID to view details: ");
        int searchId = scan.nextInt(); // Reading car ID for search
        boolean found = false;
        for (CarShould car : carShoulds) {
            if (car != null && car.id == searchId) {
                // Printing details of the found car
                System.out.println("\nCar Details for ID " + searchId + ":");
                System.out.println("Brand: " + car.brand);
                System.out.println("Model: " + car.model);
                System.out.println("Engine Number: " + car.engineNumber);
                System.out.println("Transmission Mode: " + (car.transmissionMode ? "Automatic" : "Manual"));
                System.out.println("Power Shutter: " + (car.isHavePowerShutter ? "Yes" : "No"));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Car with ID " + searchId + " not found."); // Displayed if car is not found
        }
    }

    public static int generateRandomId() {
        Random rand = new Random();
        // Generating random 10-bit ID (1024 possible IDs)
        return rand.nextInt(1024);
    }
}

class CarShould {
    // Defining CarShould class with attributes
    int id;
    String brand;
    String model;
    int engineNumber;
    boolean transmissionMode;
    boolean isHavePowerShutter;
}
