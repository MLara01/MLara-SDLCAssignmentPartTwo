/*
Name: Morgan Lara
Date Due: 9/21/2026
Expected Input: Patron ID, Name, Address, and Overdue Fee
Expected Output: Displaying Patron information, adding and removing patrons, and a patron list displayed
*/
import java.util.Scanner;
public class LibraryApp
{
    private static Scanner scanner = new Scanner(System.in);
    private static PatronManager patronManager = new PatronManager();

    public static void main(String[] args) {

        // Ensures that the system is running so that the while loop will break when the user chooses to exit
        boolean running = true;

        System.out.println("=================================");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=================================");

        while (running)
        {

            displayMenu();

            String choice = scanner.nextLine();

            switch (choice)
            {

                    case "1":
                    addPatron();
                    break;

                    case "2":
                        removePatron();
                        break;

                    case "3":
                        patronManager.displayPatrons();
                        break;

                    case "4":
                        sortPatrons();
                        break;

                    case "5":
                        loadFromFile();
                        break;

                    case "6":
                        System.out.println(
                                "\nTotal Patrons: " + patronManager.getPatronCount() );
                        break;

                    case "7":
                        running = false;
                        System.out.println("\nThank you for using the Library Management System.");
                        break;

                    default:
                        System.out.println("\nInvalid choice. Please select 1-7.");
            }
        }
            scanner.close();
    }

        // Display main menu
        private static void displayMenu()
        {

            System.out.println("\n========== MENU ==========");
            System.out.println("1. Add Patron");
            System.out.println("2. Remove Patron");
            System.out.println("3. Display Patrons");
            System.out.println("4. Sort Patrons");
            System.out.println("5. Load Patrons From File");
            System.out.println("6. Display Patron Count");
            System.out.println("7. Exit");
            System.out.println("==========================");
            System.out.print("Enter your choice: ");
        }

        // The next operations will directly allow the user to manipulate the patron list through the CLI
        // Add patron through CLI
        private static void addPatron()
        {
            String id;

            System.out.println("\n========== ADD PATRON ==========");
            while (true)
            {
                System.out.print("Enter 7-digit Patron ID: ");
                id = scanner.nextLine().trim();

                if (!patronManager.isValidId(id))
                {

                    System.out.println("ID must contain exactly 7 digits.");

                }
                else if (patronManager.findPatron(id) != null)
                {

                    System.out.println("That ID already exists.");

                }
                else
                {
                    break;
                }
            }

            System.out.print("Enter Patron Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Patron Address: ");
            String address = scanner.nextLine().trim();

            double fine;

            while (true)
            {

                System.out.print("Enter Overdue Fine ($0 - $250): ");

                try {

                    fine = Double.parseDouble(scanner.nextLine());

                    if (patronManager.isValidFine(fine))
                    {
                        break;
                    }

                    System.out.println("Fine must be between $0 and $250.");

                }
                catch (NumberFormatException e)
                {
                    System.out.println("Please enter a valid number.");
                }
            }

            Patron patron = new Patron(id, name, address, fine);

            if (patronManager.addPatron(patron))
            {
                System.out.println("\nPatron successfully added.");
            }
            else
            {
                System.out.println("\nUnable to add patron.");
            }
        }

        // Remove patron through CLI
        private static void removePatron()
        {
            System.out.println("\n========== REMOVE PATRON ==========");

            System.out.print("Enter Patron ID: ");
            String id = scanner.nextLine().trim();

            if (!patronManager.isValidId(id))
            {
                System.out.println("Invalid ID. ID must contain 7 digits.");
                return;
            }

            if (patronManager.removePatron(id)) {

                System.out.println(
                        "Patron successfully removed."
                );

            } else {

                System.out.println(
                        "No patron was found with that ID."
                );
            }
        }

        // Sorting PatronList through CLI
        private static void sortPatrons()
        {
            boolean sorting = true;
            while (sorting)
            {

                System.out.println("\n========== SORT PATRONS ==========");
                System.out.println("1. Sort by ID");
                System.out.println("2. Sort by Name");
                System.out.println("3. Sort by Address");
                System.out.println("4. Sort by Overdue Fine");
                System.out.println("5. Return to Main Menu");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine();

                switch (choice)
                {
                    case "1":
                        patronManager.sortById();
                        patronManager.displayPatrons();
                        break;

                    case "2":
                        patronManager.sortByName();
                        patronManager.displayPatrons();
                        break;

                    case "3":
                        patronManager.sortByAddress();
                        patronManager.displayPatrons();
                        break;

                    case "4":
                        patronManager.sortByFine();
                        patronManager.displayPatrons();
                        break;

                    case "5":
                        sorting = false;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }

        // Load patrons from external text file
        private static void loadFromFile()
        {
            System.out.println("\n========== LOAD FROM FILE ==========");

            System.out.print("Enter file name: ");
            String fileName = scanner.nextLine().trim();

            int numberAdded =
                    patronManager.loadFromFile(fileName);

            System.out.println(numberAdded + " patron(s) successfully loaded.");
    }
}
