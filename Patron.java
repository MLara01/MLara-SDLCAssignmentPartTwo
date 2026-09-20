// This class is primarily holding the individual patron's information fields

public class Patron
{
    // Setting up variables
        private String id;
        private String name;
        private String address;
        private double overdueFine;

        public Patron(String id, String name, String address, double overdueFine)
        {
            this.id = id;
            this.name = name;
            this.address = address;
            this.overdueFine = overdueFine;
        }

        // Getters
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public double getOverdueFine() {
            return overdueFine;
        }

        // Setters
        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setOverdueFine(double overdueFine) {
            this.overdueFine = overdueFine;
        }

        // This is to ensure that the patron's information is returned in the detailed format from the SDLC document
        @Override
        public String toString() {
            return String.format(
                    "ID: %s | Name: %s | Address: %s | Overdue Fine: $%.2f",
                    id, name, address, overdueFine
            );
        }
}
