
/**
 * Customer.java  E.L. 2010-01-16
 * Inneholder kundedata.
 */
class Customer {
    private final String name;
    private final String phone;

    /**
     * Konstruktør:
     * Både navn og telefon må oppgis, de kan ikke være verken null eller tomme strenger.
     */
    public Customer(String name, String phone) {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("Name is not defined!");
        }
        if (phone == null || phone.trim().equals("")) {
            throw new IllegalArgumentException("Phone-number is not defined!");
        }
        this.name = name.trim();
        this.phone = phone.trim();
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }



    // Istedet for set-,etoder, endres objekter direkte edit metoden.

    public String toString() {
        return "Navn: " + name + "\n\n" +"Telefon: " + phone;
    }
}