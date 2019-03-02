import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// NB!! Alle ROM-objekter holder orden på sine egne reservasjoner, og følger med på når de er ledige og når de er booket

// Lag funksjon for å hente alle kunde objeketene
// Lag funksjon for å hente rom, gitt index
// (Evt lag funksjon for å hente kunde gitt index)

public class ConferanceCenter {
    private final String name;
    private ArrayList<Room> allRooms = new ArrayList<Room>();
    private ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    // Muliggjør å lagre registrerte kunder
    // til senere bruk, for å unngå å registrere kunder flere ganger



    public ConferanceCenter(String name, ArrayList<Room> allRooms){
        this.name= name; // NB. Må settes ved bruk av final!!
        this.allRooms = (ArrayList)allRooms.clone();  // Må bruke clone() for å unngå aksess utenfor CC-objektet.
    }

    public ConferanceCenter(String name){
        this.name = name; // NB. Må settes ved bruk av final!!
        // this.allRooms = new ArrayList<Room>();    Er allerede definert utenfor konstruktør!
    }

    public String getName(){
        return this.name;
    }



    /* Metoder for Rom-klassen: */

    // Index eksisterer tilsynelatende ikke i ArrayList, og metodene for dette utelates.



    public int fintTotalNumberOfRooms(){  // Returnerer objekt-størrelsen på ArrayListen allRomms, som blir antall rom;
        return allRooms.size();
    }

    private boolean checkIfRoomNmbExists(int roomNmb){ // Returnerer True dersom romnummer allerede eksisterer.
        for (Room r : allRooms){
            if (r.getRoomNmb()==roomNmb){
                return true;
            }
        }
        return false;
    }





    // Offentlig metode for å hente Rom-objekter fra rom-listen. Returner null, dersom objektet ikke eksisterer.
    public Room getRoomByIndex(int index){
        if (index >= 0 && index< allRooms.size()){
             return allRooms.get(index);
        }
        return null;
    }

    private ArrayList<Room> getAllRooms(){ // Hjelpemetode for å hente ut alle rom
        return (ArrayList)allRooms.clone();
    }


    /* Metoder for Reservation-Klassen: */

    private LocalDateTime getTimeFrom(){  // Hjelpemetode for å hente brukerinput om booking-tid fra tidspunkt.
        JTextField datoFraStr = new JTextField("Sett inn dato fra");
        JTextField mndFraStr = new JTextField("Sett inn måned fra");
        JTextField aarFraStr = new JTextField("Sett inn år fra");
        JTextField timeFraStr = new JTextField("Sett inn time fra");
        JTextField minuttFraStr = new JTextField("Sett inn minutt fra");
        Object[] localDateTime_Objekter = {datoFraStr, mndFraStr, aarFraStr, timeFraStr, minuttFraStr};
        JOptionPane.showConfirmDialog(null, localDateTime_Objekter, "Sett inn booking-tidspunkt FRA", JOptionPane.OK_CANCEL_OPTION);
        int datoFraInt= Integer.parseInt(datoFraStr.getText());
        int mndFraInt= Integer.parseInt(mndFraStr.getText());
        int aarFraInt= Integer.parseInt(aarFraStr.getText());
        int timeFraInt= Integer.parseInt(timeFraStr.getText());
        int minuttFraInt= Integer.parseInt(minuttFraStr.getText());
        LocalDateTime timeFrom = LocalDateTime.of(aarFraInt, mndFraInt, datoFraInt, timeFraInt, minuttFraInt);
        return timeFrom;
    }

    private LocalDateTime getTimeTo(){   // Hjelpemetode for å hente brukerinput om booking-tid til tidspunkt.
        JTextField datoTilStr = new JTextField("Sett inn dato til");
        JTextField mndTilStr = new JTextField("Sett inn måned til");
        JTextField aarTilStr = new JTextField("Sett inn årstall til");
        JTextField timeTilStr = new JTextField("Sett inn time til");
        JTextField minuttTilStr = new JTextField("Sett inn minutt til");
        Object[] localDateTime_Objekter = {datoTilStr, mndTilStr, aarTilStr, timeTilStr, minuttTilStr};
        JOptionPane.showConfirmDialog(null, localDateTime_Objekter, "Sett inn booking-tidspunkt TIL", JOptionPane.OK_CANCEL_OPTION);
        int datoTilInt= Integer.parseInt(datoTilStr.getText());
        int mndTilInt= Integer.parseInt(mndTilStr.getText());
        int aarTilInt= Integer.parseInt(aarTilStr.getText());
        int timeTilInt= Integer.parseInt(timeTilStr.getText());
        int minuttTilInt= Integer.parseInt(minuttTilStr.getText());
        LocalDateTime timeTo = LocalDateTime.of(aarTilInt, mndTilInt, datoTilInt, timeTilInt, minuttTilInt);
        return timeTo;
    }
    private ArrayList<Room> getAllRoomsWithMinRoomSize(int roomSize){ // Hjelpemetode for å sile ut aktuelle rom
        ArrayList<Room> potentialRooms = new ArrayList<>();
        for (Room r: allRooms){
            if (r.getRoomSizeByNmbOfPersons() >= roomSize){
                potentialRooms.add(r);
            }
        }
        return potentialRooms;
    }





    public Room findDedicatedRoomByRoomNmb(int roomNmb){ // Påkrevet metode i hht oppgaven.
        for (Room r : allRooms){
            if (r.getRoomNmb() == roomNmb){
                System.out.println("Rommet eksisterer. " + r.toString());
                return r;
            }
        }
        System.out.println("Rommet eksisterer ikke.");
        return null;
    }

    // sjekk tilgjengeligeReservasjonstidspunkt
    //
    // Det bør gå an at metoden sjekker neste rom i listen, dersom et rom er booket på tidspunkt
    // Metoden bør fortelle om booking går bra eller ikke

/*    private boolean tryToCreateNewReservation(LocalDateTime timeFrom, LocalDateTime timeTo, ArrayList<Room> potentialRooms, Customer customer){

        for (Room r : allRooms){
            if (r.getRoomSizeByNmbOfPersons()>=)
        }
        // Må først finne potensielt rom
        // må så sjekke om det er noen eksisterende reservasjoner der
        for (Room pR : potentialRooms){
            for (Reservation r : pR.getAllReservationsOnRoom()){
                if (!r.checkReservationBlockOnTime(timeFrom, timeTo)){
                    ArrayList <Reservation> allReservations = pR.getAllReservationsOnRoom();


                    pR.addReservation(newReservation);
                    JOptionPane.showMessageDialog(null, "Ny reservasjon lagt til på rommet");
                    return true; // Sjekk at dette går bra med to for løkker.
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Reservasjonstidpunktet er allerede booket. Reservasjon ble ikke lagt til");
        return false;
    }*/

    public boolean reserveRoom(){  // Metode for å reservere rom. Metoden benytter hjelpemetoder. (Rekursiv).

        String customerName = (JOptionPane.showInputDialog("Oppgi kundenavn1!"));
        if (checkExistanceOfCustomer(customerName)) {
            int neededRoomSizeInNmbPersons = Integer.parseInt(JOptionPane.showInputDialog("Oppgi ønsket romstørrelse i antall personer."));
            if (checkExistanceOfMinRoomSize(neededRoomSizeInNmbPersons)){ // Sjekker at et slikt rom eksisterer på konferansesenteret
                for (Room r : allRooms){
                    // Finner her første ledige rom med gitt min-størrelse:
                    if (r.getRoomSizeByNmbOfPersons()>=neededRoomSizeInNmbPersons){ // begrenser søket med rom-størrelse
                        Customer existingCustomer = getDedicatedCustomerByName(customerName); // Henter kundeobjekt,basert på kundenavn
                        System.out.println("Printer kundeobjekt: " + existingCustomer.toString());
                        LocalDateTime timeFrom = getTimeFrom(); // Henter brukerinput
                        LocalDateTime timeTo = getTimeTo();  // Henter brukerinput
                        Reservation newReservation = new Reservation(timeFrom, timeTo, existingCustomer);


                        ArrayList <Reservation> allExistingReservationsOnRoom = r.getAllReservationsOnRoom(); // Henter inn alle reservasjoner på rommet
                        System.out.println("Alle reservasjoner på rom: " + allExistingReservationsOnRoom.toString());

                        if (allExistingReservationsOnRoom.isEmpty()){ // Dersom det ikke eksisterer reservasjoner på rommet, skal denne bye reservasjonen legges inn.
                            r.addReservation(newReservation);
                            JOptionPane.showMessageDialog(null, "Nå skal reservasjonen være lagt til");

                            System.out.println("Printer alle reservasjoner på rom:" + r.getAllReservationsOnRoom()); // sjekk
                            System.out.println("Printer hele rom objektet: " + r.toStringWithReservations());
                            return true;
                        }

                        for (Reservation res : allExistingReservationsOnRoom){ // Dersom det eksisterer reservasjoner på rommet på i den nye reservasjonstidsrommet, skal det ikke legges til flere
                            if (res.checkReservationBlockOnTime(timeFrom, timeTo)){ // Sjekker at ikke noen reservasjonstidspunkt kolliderer
                                JOptionPane.showMessageDialog(null, "Reservasjonstidpunkt ikke tilgjengelig");
                                return false;
                            }
                        }
                        r.addReservation(newReservation);
                        JOptionPane.showMessageDialog(null, "Nå skal reservasjonen være lagt til");
                        System.out.println("Printer alle reservasjoner på rom2:" + r.getAllReservationsOnRoom()); // sjekk
                        System.out.println("Printer hele rom objektet2: " + r.toStringWithReservations());
                        return true;
                    }
                }
            }
            JOptionPane.showMessageDialog(null,"Rom-størrelse eksisterer ikke på konferansesenteret!!");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Kunde er ikke registrert, og må registreres først");
        return false;
    }

    public boolean registrerNewRoom(){  // Metode for å opprette et nytt rom i konferansesenteret.
        int roomNmb = Integer.parseInt(JOptionPane.showInputDialog("Skriv inn romnummer"));
        int sizeNmbOfPersons = Integer.parseInt(JOptionPane.showInputDialog("Skriv inn antall personer størrelse på konferanserommet"));
        if (checkIfRoomNmbExists(roomNmb)) {
            JOptionPane.showMessageDialog(null, "Rom med samme romnummer eksisterer!");
            return false;}
        Room newRoom = new Room(roomNmb, sizeNmbOfPersons);
        allRooms.add(newRoom);
        JOptionPane.showMessageDialog(null, "Nytt rom registrert!");
        return true;
    }


    private boolean checkExistanceOfMinRoomSize(int size){ // Hjelpemetode for å sjekke eksistens av rom med størrelse.
        for (Room r : allRooms){
            if (r.getRoomSizeByNmbOfPersons()>=size){
                JOptionPane.showMessageDialog(null, "Romstørrelse eksister");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Romstørrelse eksisterer ikke");
        return false;  // Om konferansesenteret ikke har spesifisert romstørrelse, returnreres False.
    }

    public void addRoom(Room room){
        //this.allReservationsOnRoom= (ArrayList)allReservationsOnRoom.clone();
        this.allRooms.add(room);
    }

    public String allRoomsToString(){
        String allRoomString = "";
        for (Room r : allRooms){
            allRoomString += r.getRoomNmb() + ", " + r.getRoomSizeByNmbOfPersons() + " \n";
        }
        return allRoomString;
    }








    /* Metoder for Customer-klassen: */


    public ArrayList<Customer> getAllCustomerObjects(){
        return allCustomers;  // Burde kanskje klones?
    }

    public boolean registerNewCustomer(){
        String customerName = JOptionPane.showInputDialog("Skriv inn Kundenavn");
        if (checkExistanceOfCustomer(customerName)){
            JOptionPane.showMessageDialog(null, "Kunde er registrert fra før");
            return false;
        }
        String phoneNmb = JOptionPane.showInputDialog("Skriv inn mobilnummer");
        Customer newCustomer = new Customer(customerName, phoneNmb);

        allCustomers.add(newCustomer);
        JOptionPane.showMessageDialog(null, "Ny kunde er registrert");
        return true;
    }

    // Offentlig metode for å hente Kunde-objketer fra kunde-listen. Returnerer null, dersom objektet ikke eksisterer.
    public Customer getCustomerObjectByname(String name){
        for (Customer c : allCustomers){
            if (c.getName().equals(name)){
                return c;
            }

        }

        /* Alternativ for-løkke:
        for (int i=0; i<allCustomers.size(); i++){
            if (allCustomers.get(i).getName().equals(name)){
                return allCustomers.get(i);
            }
        }*/
        return null;
    }

    public Customer getDedicatedCustomerByName(String custName){ // Hjelpemetode for å hente inn kundeObjekt basert på navn
        for (Customer c : allCustomers){
            if (c.getName().equals(custName)){
                JOptionPane.showMessageDialog(null, "Kunde eksisterer. " + c.getName());
                return c;
            }

        }

        JOptionPane.showMessageDialog(null,"Kunde eksisterer ikke.");
        return null;

        // NB!!
        // Prøvde først en variant der jeg returnerte kunde-objektet rett fra IF-setningen, der en også måtte returnere NULL.
        // Metoden ble imidlertid aldri null, ettersom den kun blir brukt i sammenheng med checkExistance.
        // Varienten her, er kanskje heller ikke optimal, men vet ikke hvordan jeg ellers kunne ha løst dette?
        // Målet er bare å få "Stemplet" reservasjonen med et kunde-objekt.

    }


    public boolean checkExistanceOfCustomer(String name){  // Offentlig helpemetode for å sjekke om kunde er registrert fra før
        for (Customer c : allCustomers){
            if (c.getName().equals(name)){
                return true; // Kunde eksisterer
            }
        }
        return false; // Kunde eksisterer ikke
    }



    // Evt en metode for å vise alle kundenavn i en liste, for å kunne se på hver enkelt kunde

    public String allCustomersToString(){
        String allCustomersString = "";
        for (Customer c : allCustomers){
            allCustomersString += c.getName() + ", " + c.getPhone() + " \n";
        }
        return allCustomersString;
    }






    public void addCustomer(Customer customer){
        //this.allReservationsOnRoom= (ArrayList)allReservationsOnRoom.clone();
        this.allCustomers.add(customer);
    }










    public static void main(String[] args) {
        ConferanceCenter testSenter1 = new ConferanceCenter("Mitt testsenter1");



/*        System.out.println("Test 1 - BGS - LocalDateTime");
        System.out.println("Tester getTimeFrom-metode - mot brukergrensesnitt");
        System.out.println(testSenter.getTimeFrom().toString());
        System.out.println("Tester getTimeTo-metode - mot brukergrensesnitt");
        System.out.println(testSenter.getTimeTo().toString());
        System.out.println();*/


/*        System.out.println("Test 2 - Registrer ny kunde BGS - metode:");
        testSenter1.registerNewCustomer();
        System.out.println(testSenter1.allCustomersToString());
        System.out.println("Registrer - 1 ny kunde - BGS-Metode OK");
        System.out.println();*/

/*        System.out.println("Test 3 - Registrer nytt rom - BGS-metode");
        testSenter1.registrerNewRoom();
        System.out.println(testSenter1.allRoomsToString());
        System.out.println("Registrer ett nytt rom - BGS-metode OK");
        System.out.println();*/

/*        System.out.println("Test 4 - legg til kunde med samme navn - BGS-metode");
        testSenter1.registerNewCustomer();
        System.out.println(testSenter1.allCustomersToString());
        System.out.println("Legg til kunde med samme navn - BGS-metod - OK");
        System.out.println();*/

/*        System.out.println("Test 5 - legg til nytt rom med samme romnummer - BGS-metode");
        testSenter1.registrerNewRoom();
        System.out.println(testSenter1.allRoomsToString());
        System.out.println("Registrer ett nytt rom med samme romnummer - BGS-metode OK");
        System.out.println();*/

/*        System.out.println("Test 6 - Legg til 2 nye kunder med ulike navn:");
        testSenter1.registerNewCustomer();
        System.out.println(testSenter1.allCustomersToString());
        System.out.println("Registrer - kunde nr 2 - BGS-Metode OK");
        testSenter1.registerNewCustomer();
        System.out.println();
        System.out.println(testSenter1.allCustomersToString());
        System.out.println("Registrer - kunde nr 3 - BGS-Metode OK");
        System.out.println();*/

/*        System.out.println("Test 7 - Legg til 2 nye rom med ulike romnummer - BGS-metode:");
        testSenter1.registrerNewRoom();
        System.out.println(testSenter1.allRoomsToString());
        System.out.println("Registrer rom nr 2 - BGS-metode OK");
        System.out.println();
        testSenter1.registrerNewRoom();
        System.out.println(testSenter1.allRoomsToString());
        System.out.println("Registrer rom nr 3 - BGS-metode OK");
        System.out.println();*/

/*        System.out.println("Test 8 - Finn dedikert rom med finnDedikertRom-Metoden");
        Room testRom1= new Room(1, 10);
        Room testRom2 = new Room(2,20);
        Room testRom3 = new Room(3, 30);
        testSenter1.allRooms.add(testRom1);
        testSenter1.allRooms.add(testRom2);
        testSenter1.allRooms.add(testRom3);
        System.out.println(testSenter1.allRoomsToString());
        testSenter1.findDedicatedRoomByRoomNmb(2); // Rom eksisterer
        testSenter1.findDedicatedRoomByRoomNmb(5); // Rom eksisterer ikke
        System.out.println("Test - 8 OK");
        System.out.println();*/

 /*       System.out.println("Test 9 - Finn dedikert kunde med getDedicatedCustomer-metoden");
        Customer testCustomer1 = new Customer("Jonny", "123");
        testSenter1.allCustomers.add(testCustomer1);
        Customer testCustomer2 = new Customer("Britt", "456");
        testSenter1.allCustomers.add(testCustomer2);
        Customer testCustomer3 = new Customer("Ole", "789");
        testSenter1.allCustomers.add(testCustomer3);
        System.out.println(testSenter1.allCustomersToString());
        testSenter1.getDedicatedCustomerByName("Britt"); // Kunde eksister
        testSenter1.getDedicatedCustomerByName("Lars"); // Kunde eksister ikke
        System.out.println("Test 9 - ok");
        System.out.println();*/

/*        System.out.println("Test 10 - getAllRoomsWithDedicatedRoomSize");
        Room testRom1= new Room(1, 10);
        Room testRom2 = new Room(2,20);
        Room testRom3 = new Room(3, 30);
        testSenter1.allRooms.add(testRom1);
        testSenter1.allRooms.add(testRom2);
        testSenter1.allRooms.add(testRom3);
        System.out.println(testSenter1.allRoomsToString());
        System.out.println(testSenter1.getAllRoomsWithMinRoomSize(20));
        System.out.println("Test 10 - OK");
        System.out.println();*/

/*        System.out.println("Test 11 - Prøver å lage 3 stk reservasjonsobjekt på ulike rom");
        // Registrerer rom på konferansesenteret
        Room testRom1= new Room(1, 10);
        Room testRom2 = new Room(2,20);
        Room testRom3 = new Room(3, 30);
        testSenter1.allRooms.add(testRom1);
        testSenter1.allRooms.add(testRom2);
        testSenter1.allRooms.add(testRom3);
        System.out.println(testSenter1.allRoomsToString());
        System.out.println();

        // Registrerer kunder på konferansesenteret
        Customer testCustomer1 = new Customer("Jonny", "123");
        testSenter1.allCustomers.add(testCustomer1);
        Customer testCustomer2 = new Customer("Britt", "456");
        testSenter1.allCustomers.add(testCustomer2);
        Customer testCustomer3 = new Customer("Ole", "789");
        testSenter1.allCustomers.add(testCustomer3);
        LocalDateTime tidFra1 = LocalDateTime.of(2000, 1,1,10,00);
        LocalDateTime tidTil1 = LocalDateTime.of(2000, 1, 1, 12, 00);
        LocalDateTime tidFra2 = LocalDateTime.of(2000, 1,2,8,00);
        LocalDateTime tidTil2 = LocalDateTime.of(2000, 1, 10, 16, 00);
        LocalDateTime tidFra3 = LocalDateTime.of(2000, 1,3,6,00);
        LocalDateTime tidTil3 = LocalDateTime.of(2000, 1, 5, 16, 00);

        Reservation testReservasjon1 = new Reservation(tidFra1, tidTil1, testCustomer1);
        Reservation testReservasjon2 = new Reservation(tidFra2, tidTil2, testCustomer2);
        Reservation testReservasjon3 = new Reservation(tidFra3, tidTil3, testCustomer3);

        System.out.println("Printer reservasjon 1 - objektet" + testReservasjon1.toString());
        System.out.println("Printer reservasjon 2 - objektet" + testReservasjon2.toString());
        System.out.println("Printer reservasjon 3 - objektet" + testReservasjon3.toString());
        System.out.println("Prøver å legge reservasjoner i listen:");
        testRom1.addReservation(testReservasjon1);
        System.out.println(testRom1.toStringWithReservations());
        testRom2.addReservation(testReservasjon2);
        System.out.println(testRom2.toStringWithReservations());
        testRom3.addReservation(testReservasjon3);
        System.out.println(testRom3.toStringWithReservations());
        System.out.println("Test ok for 1 reservasjon per rom");
        System.out.println();


        System.out.println("Test 12 - (test 11 må også kjøres samtidig) Tester å legge til flere reservasjoner på samme rom på like og ulike atributter");
        LocalDateTime tidFra4 = LocalDateTime.of(2000, 1,1,10,00);
        LocalDateTime tidTil4 = LocalDateTime.of(2000, 1, 1, 12, 00);
        LocalDateTime tidFra5 = LocalDateTime.of(2000, 2,2,8,00);
        LocalDateTime tidTil5 = LocalDateTime.of(2000, 2, 10, 16, 00);
        LocalDateTime tidFra6 = LocalDateTime.of(2000, 3,3,6,00);
        LocalDateTime tidTil6 = LocalDateTime.of(2000, 3, 5, 16, 00);
        Reservation testReservasjon4 = new Reservation(tidFra4, tidTil4, testCustomer1); // Lik tid som test11
        Reservation testReservasjon5 = new Reservation(tidFra5, tidTil5, testCustomer2);
        Reservation testReservasjon6 = new Reservation(tidFra6, tidTil6, testCustomer3);

        System.out.println("Printer reservasjon 4 - objektet" + testReservasjon1.toString());
        System.out.println("Printer reservasjon 5 - objektet" + testReservasjon2.toString());
        System.out.println("Printer reservasjon 6 - objektet" + testReservasjon3.toString());
        System.out.println("Prøver å legge reservasjoner i listen:");
        testRom1.addReservation(testReservasjon4);
        System.out.println(testRom1.toStringWithReservations());
        testRom2.addReservation(testReservasjon5);
        System.out.println(testRom2.toStringWithReservations());
        testRom3.addReservation(testReservasjon6);
        System.out.println(testRom3.toStringWithReservations());
        System.out.println("Test 12 - OK - klarte å legge flere reservasjoner i listene");*/


        // Må kanskje optimalisere toString for å skille av hver reservasjon i visningen

        /*System.out.println("Test 13 - reserveRoom() og tilhørende hjelpemetoder - BGS-metode");
        // Registrerer rom på konferansesenteret
        Room testRom1= new Room(1, 10);
        Room testRom2 = new Room(2,20);
        Room testRom3 = new Room(3, 30);
        testSenter1.allRooms.add(testRom1);
        testSenter1.allRooms.add(testRom2);
        testSenter1.allRooms.add(testRom3);
        System.out.println(testSenter1.allRoomsToString());

        // Registrerer kunder på konferansesenteret
        Customer testCustomer1 = new Customer("Jonny", "123");
        testSenter1.allCustomers.add(testCustomer1);
        Customer testCustomer2 = new Customer("Britt", "456");
        testSenter1.allCustomers.add(testCustomer2);
        Customer testCustomer3 = new Customer("Ole", "789");
        testSenter1.allCustomers.add(testCustomer3);
        System.out.println(testSenter1.allCustomersToString());


        // Legger inn reservasjoner
        LocalDateTime tidFra1 = LocalDateTime.of(2000, 1,1,10,00);
        LocalDateTime tidTil1 = LocalDateTime.of(2000, 1, 1, 12, 00);
        LocalDateTime tidFra2 = LocalDateTime.of(2000, 1,2,8,00);
        LocalDateTime tidTil2 = LocalDateTime.of(2000, 1, 10, 16, 00);
        LocalDateTime tidFra3 = LocalDateTime.of(2000, 1,3,6,00);
        LocalDateTime tidTil3 = LocalDateTime.of(2000, 1, 5, 16, 00);
        LocalDateTime tidFra4 = LocalDateTime.of(2000, 1,1,10,00);
        LocalDateTime tidTil4 = LocalDateTime.of(2000, 1, 1, 12, 00);

        Reservation testReservasjon1 = new Reservation(tidFra1, tidTil1, testCustomer1);
        Reservation testReservasjon2 = new Reservation(tidFra2, tidTil2, testCustomer2);
        Reservation testReservasjon3 = new Reservation(tidFra3, tidTil3, testCustomer3);
        Reservation testReservasjon4 = new Reservation(tidFra4, tidTil4, testCustomer1);
        testRom2.addReservation(testReservasjon4);

        System.out.println("Printer reservasjon 1 - objektet" + testReservasjon1.toString());
        System.out.println("Printer reservasjon 2 - objektet" + testReservasjon2.toString());
        System.out.println("Printer reservasjon 3 - objektet" + testReservasjon3.toString());
        System.out.println("Prøver å legge reservasjoner i listen:");
        testRom1.addReservation(testReservasjon1);
        System.out.println(testRom1.toStringWithReservations());
        testRom2.addReservation(testReservasjon2);
        System.out.println(testRom2.toStringWithReservations());
        testRom3.addReservation(testReservasjon3);
        System.out.println(testRom3.toStringWithReservations());

        testSenter1.reserveRoom();
        System.out.println("Test ok - umulig å reservere på samme tidspunkt");
        System.out.println();*/


        System.out.println("Test 14 - Reservere rom - BGS + hent antall rom");
        // Registrerer rom på konferansesenteret
        Room testRom1= new Room(1, 10);
        Room testRom2 = new Room(2,20);
        Room testRom3 = new Room(3, 30);
        testSenter1.allRooms.add(testRom1);
        testSenter1.allRooms.add(testRom2);
        testSenter1.allRooms.add(testRom3);

        // Registrerer kunder på konferansesenteret
        Customer testCustomer1 = new Customer("Jonny", "123");
        Customer testCustomer2 = new Customer("Britt", "456");
        Customer testCustomer3 = new Customer("Ole", "789");
        testSenter1.allCustomers.add(testCustomer1);
        testSenter1.allCustomers.add(testCustomer2);
        testSenter1.allCustomers.add(testCustomer3);


        // Tester getRoomByIndex()
        /*Room indexedRoom0 = testSenter1.getRoomByIndex(0);
        System.out.println("Indeksert rom - 0- er: " + indexedRoom0.toString());
        Room indexedRoom1 = testSenter1.getRoomByIndex(1);
        System.out.println("Indeksert rom - 1- er: " + indexedRoom1.toString());
        Room indexedRoom2 = testSenter1.getRoomByIndex(2);
        System.out.println("Indeksert rom - 2- er: " + indexedRoom2.toString());
        Room indexedRoom3 = testSenter1.getRoomByIndex(3);
        if (indexedRoom3 !=null){
            System.out.println("Indeksert rom - 3- er: " + indexedRoom3.toString());
        }
        else {
            System.out.println("Rommet eksisterer ikke.");
        }*/


        // Tester getCustomerObjectByname()
        /*Customer cus1 = testSenter1.getCustomerObjectByname("Britt");
        if (cus1!=null){
            System.out.println("Kunden eksisterer! " + "\n\n" + cus1.toString());
        }
        else{
            System.out.println("Kunden eksisterer ikke!");
        }*/





        System.out.println("Printer ut alle rom på senteret: " + testSenter1.allRoomsToString());
        System.out.println("Printer ut alle registrerte kunder på senteret: " + testSenter1.allCustomersToString());

        System.out.println("Totalt antall rom på konferansesenteret er: " + testSenter1.fintTotalNumberOfRooms());


    }

}
