import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Resevasjon.java E.L. 2010-01-16
 * Oppdatert av Nils Tesdal 2016-01-25
 * Oppdatert av Kim Røstgård 2019-02-24
 *
 * Et objekt inneholder data om en reservasjon.
 * Operasjoner for å hente ut data, og for å sjekke om overlapp
 * med annen reservasjon.
 */

class Reservation {
    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;
    private final Customer customer;



    /**
     * Konstruktør:
     * fraTid må være før tilTid.
     * Ingen av argumentene kan være null.
     */
    public Reservation(LocalDateTime fraTid, LocalDateTime tilTid, Customer customer) {
        if (fraTid == null || tilTid == null) {
            throw new IllegalArgumentException("Fra-tid og/eller til-tid er null");
        }
        if (fraTid.isAfter(tilTid) || fraTid.equals(tilTid)) {
            throw new IllegalArgumentException("Fra-tid er lik eller etter til-tid");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Kunde er null");
        }

        this.fromTime = fraTid;
        this.toTime = tilTid;
        this.customer = customer;
    }



    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }



    /**
     * Metoden returnerer true dersom tidsintervallet [sjekkFraTid, sjekkTilTid] overlapper
     * med det tidsintervallet som er i det reservasjonsobjektet vi er inne i [fraTid, tilTid].
     * Overlapp er ikke definert hvis sjekkFraTid eller sjekkTilTid er null.
     * Da kaster metoden NullPointerException.
     */
    public boolean checkReservationBlockOnTime(LocalDateTime sjekkFraTid, LocalDateTime sjekkTilTid) {
        return (sjekkTilTid.isAfter(fromTime) && sjekkFraTid.isBefore(toTime));
    }



    public String toString() {
        return "Kunde: " + customer.getName() + ", tlf: " + customer.getPhone() + ", fra: " +
                fromTime.format(DateTimeFormatter.ISO_DATE_TIME) + ", til " + toTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Metode som prøver klassen Reservasjon.
     */
    public static void main(String[] args) {
        Customer k = new Customer("Anne Hansen", "12345678");
        // Room r = new Room(1, 10);
        System.out.println("Totalt antall tester: ");
        Reservation r1 = new Reservation(LocalDateTime.of(2003, 2, 1, 10, 0), LocalDateTime.of(2003, 2, 1, 11, 0), k);
        System.out.println(r1);

        Reservation r2 = new Reservation(LocalDateTime.of(2003, 1, 21, 10, 0), LocalDateTime.of(2003, 1, 21, 10, 30), k);
        Reservation r3 = new Reservation(LocalDateTime.of(2003, 2, 1, 11, 30), LocalDateTime.of(2003, 2, 1, 13, 0), k);
        Reservation r4 = new Reservation(LocalDateTime.of(2003, 2, 1, 9, 0), LocalDateTime.of(2003, 2, 1, 11, 0), k);
        if (r1.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 2003-02-01T10:00:00, til 2003-02-01T11:00:00") &&
                r2.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 2003-01-21T10:00:00, til 2003-01-21T10:30:00") &&
                r3.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 2003-02-01T11:30:00, til 2003-02-01T13:00:00") &&
                r4.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 2003-02-01T09:00:00, til 2003-02-01T11:00:00")) {
            System.out.println("Reservasjon: Test 1 vellykket.");
        }

        if (r1.checkReservationBlockOnTime(LocalDateTime.of(2003, 2, 1, 10, 0), LocalDateTime.of(2003, 2, 1, 11, 0)) &&
                !r1.checkReservationBlockOnTime(LocalDateTime.of(2003, 2, 2, 10, 0), LocalDateTime.of(2003, 2, 2, 11, 0)) &&
                r1.checkReservationBlockOnTime(LocalDateTime.of(2003, 2, 1, 10, 30), LocalDateTime.of(2003, 2, 1, 11, 0)) &&
                r1.checkReservationBlockOnTime(LocalDateTime.of(2003, 2, 1, 9, 30), LocalDateTime.of(2003, 2, 1, 10, 30))) {
            System.out.println("Reservasjon: Test 2 vellykket.");
        }
    }
}