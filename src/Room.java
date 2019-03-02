import java.time.LocalDateTime;
import java.util.ArrayList;

public class Room {


    private final int roomNmb;
    private final int roomSizeByNmbOfPersons;
    // Vurder å legge til pris/ enhetspris,
    private ArrayList <Reservation> allReservationsOnRoom = new ArrayList <Reservation>();
    // private ArrayList <Reservation> allHistoricalReservations = new ArrayList <Reservation>(); // Potensielt til videreutvikling av programmet
    // private ArrayList <Reservation> allFuturedReservations = new ArrayList <Reservation>(); // Potensielt til videreutvikling av programmet


    public Room(int roomNmb, int roomSizeByNmbOfPersons, ArrayList<Reservation> allReservationsOnRoom){
        this.roomNmb=roomNmb;
        this.roomSizeByNmbOfPersons=roomSizeByNmbOfPersons;
        this.allReservationsOnRoom = (ArrayList)allReservationsOnRoom.clone();
    }

    public Room(int roomNmb, int roomSizeByNmbOfPersons){
        this.roomNmb=roomNmb;
        this.roomSizeByNmbOfPersons=roomSizeByNmbOfPersons;
    }


    public int getRoomNmb(){
        return roomNmb;
    }

    public int getRoomSizeByNmbOfPersons(){
        return roomSizeByNmbOfPersons;
    }



    public ArrayList<Reservation> getAllReservationsOnRoom(){
        return this.allReservationsOnRoom;//(ArrayList)allReservationsOnRoom.clone();
    }

    public void addReservation(Reservation reservation){
        //this.allReservationsOnRoom= (ArrayList)allReservationsOnRoom.clone();

        this.allReservationsOnRoom.add(reservation);
    }

    /*public ArrayList<Reservation> getAllHistoricalReservations(){
        return (ArrayList)allHistoricalReservations;
    }

    public ArrayList<Reservation> getAllFuturedReservations(){
        return (ArrayList)allFuturedReservations;
    }*/

    public String toString(){
        return "Romnummer: " + getRoomNmb() + "\n" + "Romstørrelse: " + getRoomSizeByNmbOfPersons();
    }

    public String toStringWithReservations(){
        return getRoomNmb() + ", " + getRoomSizeByNmbOfPersons()  + ", " + getAllReservationsOnRoom();
    }




    public static void main(String[] args) {
        // Test addReservation

        Room testRoom = new Room(1,10);
        Customer testCustomer = new Customer("k", "123");
        LocalDateTime timeFrom = LocalDateTime.of(2000, 1,1,10,00);  //getTimeFrom(); // Henter brukerinput
        LocalDateTime timeTo = LocalDateTime.of(2000, 1, 1, 12, 00);
        Reservation testReservasjon = new Reservation(timeFrom, timeTo, testCustomer);
        Reservation testReservasjon2 = new Reservation(timeFrom, timeTo, testCustomer);
        testRoom.addReservation(testReservasjon);
        testRoom.addReservation(testReservasjon2);
        System.out.println(testRoom.toStringWithReservations());
        System.out.println(testRoom.allReservationsOnRoom.toString());


        System.out.println("TEST 1 - Opprette kun");
    }
}
