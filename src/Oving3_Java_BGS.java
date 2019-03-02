import javax.swing.*;
import java.time.LocalDateTime;


import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.ListModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Oving3_Java_BGS extends JFrame{  // JList JFrame, SwingUtilities,

    private static ConferanceCenter conferanceCenter;

    public Oving3_Java_BGS(ConferanceCenter c){
        this.conferanceCenter = c;
    }

/*    listModel.addElement("Ankit Mishra");
    listModel.addElement("Madhuri Sanghvi");
    listModel.addElement("Alok Kumar");
    listModel.addElement("Rohit Bothra");
    listModel.addElement("Rahul Aggarwal");


//Create the list and put it in a scroll pane.
    list = new JList(listModel);
list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
list.setSelectedIndex(0);
list.addListSelectionListener(this);
list.setVisibleRowCount(5);
    JScrollPane listScrollPane = new JScrollPane(list);*/




    /*public static void getCustomerFeatures() {

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
                    conferanceCenter.registerNewCustomer();

                    break;





                case (1):// Vis alle registrerte kunder (i liste?)


                    //JOptionPane.showOptionDialog(null, panel2, "Viser alle kunder", JOptionPane.CANCEL_OPTION, conferanceCenter.allCustomersToString()), null, null;
                    //JOptionPane.showMessageDialog(null, conferanceCenter.toString()), "Dette";
                    //JOptionPane.showMessageDialog(null, conferanceCenter.allCustomersToString());
                    //JOptionPane.showInternalMessageDialog(null, conferanceCenter.allCustomersToString());
                    break;
                case (2): // Søk etter kunde gitt navn;
                    String customerName = JOptionPane.showInputDialog("Skriv inn kundenavn");
                    if (conferanceCenter.checkExistanceOfCustomer(customerName)){
                        Customer returnedCustomerObject = conferanceCenter.getDedicatedCustomerByName(customerName);
                        JOptionPane.showMessageDialog(null, returnedCustomerObject.toString());
                        break;
                    }

                    JOptionPane.showMessageDialog(null, "Kunde eksisterer ikke!");

                    break;
                case (3):
                    run1=false;

                default:
                    break;




            }


        }
        //break;
    }*/










    public static void main(String[] args) {

        // Oppretter konferansesenterobjekt
        ConferanceCenter conferanceCenter = new ConferanceCenter("Thon - Konferanseimperium");

        Oving3_Java_BGS oving = new Oving3_Java_BGS(conferanceCenter);

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


        Object[] options1 = {"Kunde" , "Rom", "Reservasjoner" ,"Quit" };

        Object[] roomOptions = {"Registrer nytt rom", "Vis rom gitt romnummer", "Vis rom gitt indeks", "Cancel"}; // Evt: Vis alle rom, Vis alle reservasjoner på gitt rom,
        Object[] reservationOptions = {"Registrer ny reservasjon", "Cancel"};

        JPanel panel = new JPanel();
        panel.add(new JLabel("Velg ønsket kategori"));
        //JTextField textField = new JTextField(20);
        // panel.add(textField);


        /*if (result == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, textField.getText());}*/





        boolean run = true;
        // NB IOSreverserer rekkefølgen i listene for JOptionPane
        while (run){
            int result = JOptionPane.showOptionDialog(null, panel, "Thon - Conferance Imperium",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options1, null);
            switch (result){
                case (0) :
                    //getCustomerFeatures();


                    break;



                case (1) :
                    int result2 =JOptionPane.showOptionDialog(null, panel, "Rom",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, roomOptions, null);
                    break;
                case (2) :
                    int result3 =JOptionPane.showOptionDialog(null, panel, "Reservasjon",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, reservationOptions, null);
                    break;

                case (3) :
                    run=false;
            }
        }








    }


}

// Kunde - Registrer ny kunde, Vis alle kunder, Søk kunde
// Rom - Registrer nytt rom, Søk rom gitt navn, Søk rom gitt index, )
// Reservasjon - Vis alle reservasjoner, Legg til ny reservasjon





        /*String[] choices = { "A", "B", "C", "D", "E", "F" };
        String input = (String) JOptionPane.showInputDialog(null, "Choose now...",
                "Thon ConferanceImperium", JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                choices, // Array of choices
                choices[0]); // Initial choice
        System.out.println(input);*/



// conferanceCenter.reserveRoom();
/*
private JList<String> countryList;
    public void Oving3_Java() {
        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("USA");
        listModel.addElement("India");
        listModel.addElement("Vietnam");
        listModel.addElement("Canada");
        listModel.addElement("Denmark");
        listModel.addElement("France");
        listModel.addElement("Great Britain");
        listModel.addElement("Japan");

        //create the list
        countryList = new JList<>(listModel);
        add(countryList);
        countryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //countryList.setSelectedIndex(0);

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                System.out.println(countryList.getSelectedIndex());
            }
        };
        countryList.addListSelectionListener(listSelectionListener);


        /*countryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(countryList.getSelectedIndex());

            }
        });
        countryList.setVisibleRowCount(5);*/
        /*countryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    int i = countryList.getSelectedIndex();

                    //List <String> selectedValuesList = countryList.getSelectedValuesList();
                    System.out.println(i);
                }
            }
        });*/


        /*

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setTitle("JList Example");
                this.setSize(200,200);
                this.setLocationRelativeTo(null);
                this.setVisible(true);
                }

public static void getRoomFeatures(){

        }


private DefaultListModel model = new DefaultListModel();
private int i = 01;

public Oving3_Java_BGS() {


        JFrame frame = new JFrame();


        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("USA");
        listModel.addElement("India");
        listModel.addElement("Vietnam");
        listModel.addElement("Canada");
        listModel.addElement("Denmark");
        listModel.addElement("France");
        listModel.addElement("Great Britain");
        listModel.addElement("Japan");

        JList<String> countryList;
        countryList = new JList<>(listModel);
        add(countryList);
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(50, 375, 10, 3);
        cancelBtn.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
        System.exit(0);
        }
        });
        add(cancelBtn, BorderLayout.SOUTH);



        //frame.add(countryList);

        /*model.addElement(("one" + i++));
        model.addElement(("two" + i++));
        model.addElement(("three" + i++));
        model.addElement(("four" + i++));
        JList list = new JList(model);
        add(new JScrollPane(list));
        JButton btn = new JButton("Remove All Rows :");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                model.removeAllElements();
            }
        });
        add(btn, BorderLayout.SOUTH);
        JButton btn1 = new JButton("Add New Rows:");
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                model.addElement(("one" + i++));
                model.addElement(("two" + i++));
                model.addElement(("three" + i++));
                model.addElement(("four" + i++));
            }
        });
        add(btn1, BorderLayout.NORTH);*/


        /*
        final String labels[] = { "A", "B", "C", "D", "E" };
        JFrame frame = new JFrame("Selecting JList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JList jlist = new JList(labels);
        JScrollPane scrollPane1 = new JScrollPane(jlist);
        frame.add(scrollPane1);

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                System.out.println(jlist.getSelectedIndex());
            }
        };
        jlist.addListSelectionListener(listSelectionListener);

        frame.setSize(350, 200);
        frame.setVisible(true);


        //Oving3_Java_BGS frame = new Oving3_Java_BGS();






        /*frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Alle kunder");
        frame.setSize(200,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
         */

