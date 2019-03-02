import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.imageio.*;
import javax.swing.event.ListDataListener;


public class BGS_Test extends JFrame implements ActionListener {

    static final long serialVersionUID = 1L;
    private static ConferanceCenter conferanceCenter;

    public BGS_Test(ConferanceCenter c){
        this.conferanceCenter = c;
    }


    /** Returns an ImageIcon, or null if the path was invalid. */
    /*protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }*/

    //ImageIcon imageIcon = new ImageIcon("src/images/customer.png");

    //ImageIcon icon2 = createImageIcon("images/middle.gif", "a pretty but meaningless splat");



    /*// Første del av BGS-kode, settes i konstruktør
    public BGS_Test() throws IOException{
        *//*JButton button = new JButton("Java Code Geeks - Java Examples");
        add(button);
        button.addActionListener(this);
        button.setActionCommand("Geeks");
        add(button);*//*
        //JLabel label1 = new JLabel();
        //add(label1);
    }*/

    @Override  // Interface metode (følger med actionListner)
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        /*if (action.equals("Geeks")){
            System.out.println("Button pressed");
        }*/
    }



    public void getCustomerFeatures(ConferanceCenter conferanceCenter) {

        Object[] customerOptions = {"Registrer ny kunde", "Vis alle kunder", "Søk kunde gitt navn", "Cancel"};
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        panel1.add(new JLabel("Velg ønsket kategori"));
        panel2.add(new JLabel("Viser alle kunder"));

        int result1 =JOptionPane.showOptionDialog(null, panel1, "Kunde",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, customerOptions, null);
        boolean run1=true;
        while (run1){
            switch (result1){
                case (0): // Registrer ny kunde
                    JOptionPane.showMessageDialog(null,"Registrer ny kunde");
                    conferanceCenter.registerNewCustomer();


                    break;





                case (1):// Vis alle registrerte kunder (i liste?) - må hente inn kundeobjekter, og legge de i en DataModel/ ListModel, deretter vise de i listen, og gjøre de klikkbare


                    ArrayList <Customer> allCustomerObjectsArray = new ArrayList<>();

                    // Lag en metode som tar inn
                    // lag metode
                     // Hent alle kunder i en liste?

                    ListModel <Customer> listModel = new DefaultListModel<>() {


                        @Override
                        public int getSize() {
                            return 0;
                        }

                        @Override
                        public Customer getElementAt(int index) {
                            return null;
                        }

                        @Override
                        public void addListDataListener(ListDataListener l) {

                        }

                        @Override
                        public void removeListDataListener(ListDataListener l) {

                        }
                    };
                    // FLytter over alle kundeobjektene fra ArrayList til ListModel:
                    for (Customer c : allCustomerObjectsArray){
                        ((DefaultListModel<Customer>) listModel).addElement(c);
                    }




                    //JOptionPane.showOptionDialog(null, panel2, "Viser alle kunder", JOptionPane.CANCEL_OPTION, conferanceCenter.allCustomersToString()), null, null;
                    //JOptionPane.showMessageDialog(null, conferanceCenter.toString()), "Dette";
                    //JOptionPane.showMessageDialog(null, conferanceCenter.allCustomersToString());
                    //JOptionPane.showInternalMessageDialog(null, conferanceCenter.allCustomersToString());
                    break;
                case (2): // Søk etter kunde gitt navn;
                    String customerName = JOptionPane.showInputDialog("Skriv inn kundenavn");

                    Customer cus1 = conferanceCenter.getCustomerObjectByname("Britt");
                    if (cus1!=null){
                        System.out.println("Kunden eksisterer! " + "\n\n" + cus1.toString());
                    }
                    else{
                        System.out.println("Kunden eksisterer ikke!");
                    }
                    break;
                case (3):
                    run1=false;

                default:
                    break;
            }
        }
        //break;
    }


    // Henter rom funksjonalitet:
    public void getRoomFeatures(ConferanceCenter conferanceCenter) {

        Object[] roomOptions = {"Registrer nytt rom", "Vis rom gitt romnummer", "Vis rom gitt indeks", "Vis antall rom totalt", "Cancel"}; // Evt: Vis alle rom, Vis alle reservasjoner på gitt rom,
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Velg ønsket kategori"));
        int result1 =JOptionPane.showOptionDialog(null, panel1, "Rom",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, roomOptions, null);
        boolean run1=true;
        while (run1){
            switch (result1){
                case (0): // Registrer nytt rom
                    JOptionPane.showMessageDialog(null,"Registrer nytt rom");
                    conferanceCenter.registrerNewRoom();
                    break;
                case (1):// Vis rom gitt rom-nummer
                    JOptionPane.showMessageDialog(null, "Vis rom, gitt rom nummer TEST");
                    break;
                case (2): // Vis rom gitt index
                    JOptionPane.showMessageDialog(null, "Vis rom, gitt indeks - TEST");
                    int index = Integer.parseInt(JOptionPane.showInputDialog("Skriv inn indeks for rommet du ønsker"));
                    if (conferanceCenter.getRoomByIndex(index)!=null){ // Metoden sjekker at det ikke gis et nullpointer Exeption!

                        Room roomObject = conferanceCenter.getRoomByIndex(index);


                        // Lag et egnet panel for en slik oppgave, tittel, tekst, ok-knapp for å avslutte


                        JOptionPane.showMessageDialog(null, "Rommet eksisterer: " + "\n" +roomObject.toString());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Rommet eksisterer ikke");
                    }



                    break;
                case (3):
                    // Vis antall rom på konferansesenteret
                    int nmbOfRoom = conferanceCenter.fintTotalNumberOfRooms();
                    JOptionPane.showMessageDialog(null, "Antall rom totalt er: " + nmbOfRoom);
                    // Må kanskje benytte et JPanel?? eller noe annet for å vise antall grafisk?
                    break;
                case (4):// Cancel
                        run1=false;
                default:
                    break;
            }
        }
        //break;
    }



    // Henter reservasjons-funksjonalitet:
    // Henter rom funksjonalitet:
    public void getReservationFeatures(ConferanceCenter conferanceCenter) {

        Object[] reservationOptions = {"Registrer ny reservasjon", "Cancel"};

        JPanel panel1 = new JPanel();  // Opretter Jpanel for å kunne vise
        panel1.add(new JLabel("Velg ønsket kategori - dette er JPanel"));


        int result1 =JOptionPane.showOptionDialog(null, panel1, "Reservasjon",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, reservationOptions, null);
        boolean run1=true;
        while (run1){
            switch (result1){
                case (0): // Registrer ny kunde
                    JOptionPane.showMessageDialog(null,"Registrer ny reservasjon");
                    conferanceCenter.registerNewCustomer();
                    break;
                case (1):// Cancel
                    run1= false;
                    break;

                default:
                    break;
            }


        }
        //break;
    }






























    private static void createAndShowGUI(ConferanceCenter conferanceCenter) {
        BGS_Test bgs = new BGS_Test(conferanceCenter);  // Oppretter et BGS_Test-objekt (som tar inn et Konferansesenterobjekt) for å kunne benytte brukergrensesnittet

        //JFrame frame1 = new BGS_Test();

        //frame1.pack();
        GridLayout layout = new GridLayout(3,2);
        JPanel panel1 = new JPanel(layout);
        panel1.setPreferredSize(new Dimension(300, 300));

        // Henter inn png-filer til iconer i hovedmenyen, og samtidig skaleres de, til ønsket størrelse
        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("src/images/customer.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));;
        ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("src/images/room.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));;
        ImageIcon imageIcon3 = new ImageIcon(new ImageIcon("src/images/reservation.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));;


        //BufferedImage image = ImageIO.read(getClass().getResource("src/images/customer.png"));
        // Legger til ImageIcon-objektene i JLabel-objektene, og sentrerer iconet på JLabelet (For visning)
        JLabel labelImage1 = new JLabel(imageIcon1, JLabel.CENTER);
        JLabel labelImage2 = new JLabel(imageIcon2, JLabel.CENTER);
        JLabel labelImage3 = new JLabel(imageIcon3, JLabel.CENTER);

        JFrame frame1 = new JFrame("THON - CONFERANCE-IMPERIUM");
        frame1.pack();
        panel1.add(labelImage3, 2, 0);
        frame1.pack();


        JButton b3 = new JButton("RESERVASJON");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dette er en test for visning av RESERVASJONS-kategorien");
                bgs.getReservationFeatures(conferanceCenter);
            }
        });
        b3.setPreferredSize(new Dimension (5, 5));

        panel1.add(b3,2,1);

        labelImage2.setIcon(imageIcon2);
        panel1.add(labelImage2, 1,0);
        frame1.pack();


        JButton b2 = new JButton("ROM");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dette er en test for visning av ROM-kategorien");
                bgs.getRoomFeatures(conferanceCenter);
            }
        });
        panel1.add(b2,1,1);
        //panel1.add(b2, gbc2);

        labelImage1.setIcon(imageIcon1);
        panel1.add(labelImage1, 0,0);
        frame1.pack();

        // Knapper og Iconer --> må stå i motsatt rekkefølge for å fylle grid-en i ønsket rekkefølge (Muligens et macIOS-problem)
        JButton b1 = new JButton("KUNDE");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dette er en test for visning av KUNDE-kategorien");
                bgs.getCustomerFeatures(conferanceCenter);
                //conferanceCenter.allCustomersToString();
            }
        });

        //b1.setPreferredSize(new Dimension(100, 50));


        //b1.setLocation(null);

        //Box box = new Box(BoxLayout.Y_AXIS);
        //box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //box.add(b1);


        //test.set(null);

        //JPanel test = new JPanel();
        //test.add(b1);

        panel1.add(b1,0,1);
        frame1.pack();

        frame1.add(panel1);
        frame1.pack();
        Dimension panelSize = panel1.getSize();   // Henter ut dimensjonene for å bruke de til å sentrere vinduet
        int pHeight = panelSize.height;
        int pWidth = panelSize.width;
        System.out.println(pHeight);
        System.out.println(pWidth);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Henter ut vindusstørrelsen for å sentrere vinduet på skjerm
        int height = screenSize.height;
        int width = screenSize.width;
        System.out.println(height);
        System.out.println(width);
        int newHeight = (height/2) - (pHeight/2);
        int newWidth = (width/2) - (pWidth/2);

        frame1.setLocation(newWidth, newHeight);
        frame1.setLocationRelativeTo(null);
        frame1.pack();
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();

        createConferanceCenter();
    }



    public static void createConferanceCenter(){

    }













    public static void main(String[] args) {
        // Oppretter konferansesenterobjekt
        ConferanceCenter conferanceCenter = new ConferanceCenter("Thon - Konferanseimperium");

        //Oving3_Java_BGS oving = new Oving3_Java_BGS(conferanceCenter);

        // Oppretter 4 rom-objekter og legger de i Arraylisten for Rom
        Room testRom1= new Room(1, 10);
        Room testRom2 = new Room(2,20);
        Room testRom3 = new Room(3, 30);
        Room testRom4 = new Room(3, 100);

        // Oppretter 3 Kunde-objekter og legger de i ArrayListen for kunder
        Customer testCustomer1 = new Customer("Jonny", "123");
        Customer testCustomer2 = new Customer("Britt", "456");
        Customer testCustomer3 = new Customer("Ole", "789");

        // Oppretter 2 reservasjons-objekter på rom 1
        LocalDateTime tidFra1 = LocalDateTime.of(2000, 1,1,10,00);
        LocalDateTime tidTil1 = LocalDateTime.of(2000, 1, 1, 12, 00);
        LocalDateTime tidFra2 = LocalDateTime.of(2000, 1,2,8,00);
        LocalDateTime tidTil2 = LocalDateTime.of(2000, 1, 10, 16, 00);
        LocalDateTime tidFra3 = LocalDateTime.of(2000, 1,3,6,00);
        LocalDateTime tidTil3 = LocalDateTime.of(2000, 1, 5, 16, 00);
        Reservation testReservasjon1 = new Reservation(tidFra1, tidTil1, testCustomer1);
        Reservation testReservasjon2 = new Reservation(tidFra2, tidTil2, testCustomer2);
        Reservation testReservasjon3 = new Reservation(tidFra3, tidTil3, testCustomer3);



        conferanceCenter.addRoom(testRom1);
        conferanceCenter.addRoom(testRom2);
        conferanceCenter.addRoom(testRom3);
        conferanceCenter.addRoom(testRom4);


        conferanceCenter.addCustomer(testCustomer1);
        conferanceCenter.addCustomer(testCustomer2);
        conferanceCenter.addCustomer(testCustomer3);


        testRom2.addReservation(testReservasjon1);
        testRom1.addReservation(testReservasjon2);
        testRom1.addReservation(testReservasjon3);


        System.out.println("Printer reservasjon 1 - objektet" + testReservasjon1.toString());
        System.out.println("Printer reservasjon 2 - objektet" + testReservasjon2.toString());
        System.out.println("Printer reservasjon 3 - objektet" + testReservasjon3.toString());
        System.out.println();


        System.out.println(testRom1.toStringWithReservations());
        System.out.println(testRom2.toStringWithReservations());
        System.out.println(testRom3.toStringWithReservations());

        createAndShowGUI(conferanceCenter);

        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {



            }
        });*/
    }


}
