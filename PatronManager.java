// This class is primarily for the manipulation of different aspects of the patron list using the Patron class
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatronManager
{
    // Setting up the Patron List
    private ArrayList<Patron> patrons;

    public PatronManager()
    {
        patrons = new ArrayList<>();
    }

    // Add a patron
    public boolean addPatron(Patron patron)
    {
        // Check for duplicate ID
        if (findPatron(patron.getId()) != null)
        {
            return false;
        }
        patrons.add(patron);
        return true;
    }

    // Remove a patron using ID, as detailed specifically in the SDLC document
    public boolean removePatron(String id)
    {
        Patron patron = findPatron(id);

        if (patron != null)
        {
            patrons.remove(patron);
            return true;
        }

        return false;
    }

    // Find a patron using ID
    public Patron findPatron(String id)
    {

        for (Patron patron : patrons)
        {
            if (patron.getId().equals(id))
            {
                return patron;
            }
        }

        return null;
    }

    // Display all patrons
    public void displayPatrons()
    {
        if (patrons.isEmpty())
        {
            System.out.println("\nThere are currently no patrons.");
            return;
        }
        System.out.println("\n========== PATRONS ==========");

        for (Patron patron : patrons)
            {
                System.out.println(patron);
            }

            System.out.println("=============================");
        }

        // This section will detail all the different sorting options

        // Sort patrons by ID
        public void sortById()
        {
            patrons.sort(Comparator.comparing(Patron::getId));
            System.out.println("\nPatrons sorted by ID.");
        }

        // Sort patrons by name
        public void sortByName()
        {
            patrons.sort(Comparator.comparing(Patron::getName, String.CASE_INSENSITIVE_ORDER));
            System.out.println("\nPatrons sorted by name.");
        }

        // Sort patrons by address
        public void sortByAddress()
        {
            patrons.sort(Comparator.comparing(Patron::getAddress, String.CASE_INSENSITIVE_ORDER));
            System.out.println("\nPatrons sorted by address.");
        }

        // Sort patrons by overdue fine
        public void sortByFine() {

            patrons.sort(Comparator.comparingDouble(Patron::getOverdueFine) );
            System.out.println("\nPatrons sorted by overdue fine.");
        }

        // Load patrons from a text file
        public int loadFromFile(String fileName)
        {
            int patronsAdded = 0;

            try
            {
                File file = new File(fileName);
                Scanner fileScanner = new Scanner(file);

                while (fileScanner.hasNextLine())
                {
                    String line = fileScanner.nextLine().trim();

                    if (line.isEmpty())
                    {
                        continue;
                    }

                    try
                    {
                        // This allows the formatting of the data to follow the client's desires as stated in the SDLC
                        // Specifically stated as ID-Name-Address-Overdue Amount
                        String[] data = line.split("-");

                        if (data.length != 4)
                        {
                            System.out.println("Invalid line: " + line);
                            continue;
                        }

                        String id = data[0].trim();
                        String name = data[1].trim();
                        String address = data[2].trim();
                        double fine = Double.parseDouble(data[3].trim());

                        if (!isValidId(id))
                        {
                            System.out.println("Invalid ID in file: " + id);
                            continue;
                        }

                        if (fine < 0 || fine > 250)
                        {
                            System.out.println("Invalid fine in file: " + fine);
                            continue;
                        }

                        Patron patron = new Patron(id, name, address, fine);

                        if (addPatron(patron))
                        {
                            patronsAdded++;
                        }
                        else
                        {
                            System.out.println("Duplicate ID skipped: " + id);
                        }

                    }

                    catch (NumberFormatException e)
                    {
                        System.out.println("Invalid fine amount in line: " + line);
                    }
                }

                fileScanner.close();

            }
            catch (FileNotFoundException e)
            {
                System.out.println("File could not be found: " + fileName);
            }

            return patronsAdded;
        }

        // Validate 7-digit ID
        public boolean isValidId(String id)
        {
            return id != null && id.matches("\\d{7}");
        }

        // Validate fine
        public boolean isValidFine(double fine)
        {
            return fine >= 0 && fine <= 250;
        }

        // Return number of patrons
        public int getPatronCount()
        {
            return patrons.size();
        }
}
