package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public class addTicketScreen extends JPanel {
    private JComboBox<Person> dropdownPersons = new JComboBox<>();
    private JComboBox<TicketType> dropdownType = new JComboBox<>();
    private final JButton doneButton = new JButton("Done");
    private Person payer;
    private JTextField textBoxToEnterName;
    private final JTextField priceField;
    private final JCheckBox checkboxEqual = new JCheckBox("split equal");
    private final ArrayList<HashMap<JCheckBox,HashMap<UUID, Double>>> checklist = new ArrayList<>();
    private ItemListener checkboxEqualListener;


    HashMap<UUID,JTextField> toPayList= new HashMap<>();



    public addTicketScreen(){ // add all button's, textfields, checkboxes, dropdown's and listeners

        this.addType();
        this.addNameField();
        this.updatePersons();
        this.add(doneButton);
        this.setBackground(Color.pink);
        priceField = new JTextField(10);
        this.add(priceField);
        setcheckboxEqualListener();
        checkboxEqual.addItemListener(checkboxEqualListener);
        this.add(checkboxEqual);
        checkboxEqual.setSelected(true);
        addDoneButtonActionListener();
    }

    public void addDoneButtonActionListener() // add listener for Done button
    {
        this.doneButton.addActionListener(listener ->
        {
            String name = textBoxToEnterName.getText();
            TicketFactory ticketFactory = new TicketFactory();
            Ticket newticket = ticketFactory.getTicket((TicketType) (Objects.requireNonNull(dropdownType.getSelectedItem())),name);
            System.out.println(newticket);
            payer = (Person) dropdownPersons.getSelectedItem();
            System.out.println("payer: " +payer);
            try {
                String priceString = priceField.getText();
                priceString = priceString.replace(',','.');
                Double price = Double.parseDouble(priceString);
                System.out.println(price);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            if (checkboxEqual.isSelected()) {
                System.out.println("equal");

            } else {
                System.out.println("not equal");
            }
            for (HashMap<JCheckBox, HashMap<UUID,Double>> hmjcb : checklist){
                JCheckBox key = hmjcb.keySet().iterator().next();
                if(key.isSelected()){
                    UUID key2 = hmjcb.get(key).keySet().iterator().next();
                    System.out.println("doet moee" + PersonDatabase.getInstance().getEntry(key2));
                }
            }
            TicketController.getInstance(TicketDatabase.getInstance()).addTicket(newticket);
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("homeScreen");
            viewFrame.update_homescreen();
        });
    }

    public void updatePersons(){ // updates all fields connected to Person and PersonDatabase
        PersonDatabase db = PersonDatabase.getInstance();
        Person[] personList = db.PersonList().toArray(new Person[0]);

        // create dropdown
        dropdownPersons = new JComboBox<>(personList);
        dropdownPersons.setVisible(true);
        this.add(dropdownPersons);

        // create HashMap for checkboxes for every Person
        for (Person p : personList){
            double toPay = 0.0;
            HashMap<UUID, Double> hm = new HashMap<>();
            hm.put(p.getId(),toPay);
            HashMap<JCheckBox, HashMap<UUID,Double>> hmLong = new HashMap<>();
            hmLong.put(new JCheckBox(p.getName()),hm);
            checklist.add(hmLong);
        }

        // create Hashmap for all textboxes for every Person
        for (HashMap<JCheckBox, HashMap<UUID,Double>> hmjcb : checklist){
            for ( JCheckBox key : hmjcb.keySet() ) {
                this.add(key);
                key.setSelected(true);
                UUID key2 = hmjcb.get(key).keySet().iterator().next();
                toPayList.put(key2,new JTextField(25));
            }
        }


        // add all the textboxes to Jpanel and set them to not visual
        for (HashMap<JCheckBox, HashMap<UUID,Double>> hmjcb : checklist) {
            JCheckBox key = hmjcb.keySet().iterator().next();
            UUID key2 = hmjcb.get(key).keySet().iterator().next();
            System.out.println("key2");
            System.out.println(key2);
            this.add(toPayList.get(key2));
            toPayList.get(key2).setVisible(false);
        }
    }

    // Add dropdown Type
    public void addType(){
        TicketType[] typeList = TicketType.values();
        dropdownType = new JComboBox<>(typeList);
        dropdownType.setVisible(true);
        this.add(dropdownType);
    }

    // add textfield for name
    public void addNameField(){
        textBoxToEnterName = new JTextField(21);
        this.add(textBoxToEnterName);
        System.out.println(textBoxToEnterName.getText());
    }

    // add checboxEqualListener
    public void setcheckboxEqualListener(){
        checkboxEqualListener = e -> {
           for (HashMap<JCheckBox, HashMap<UUID, Double>> hmjcb : checklist) {
                JCheckBox key = hmjcb.keySet().iterator().next();
                UUID key2 = hmjcb.get(key).keySet().iterator().next();
                System.out.println("key2");
                System.out.println(key2);
                toPayList.get(key2).setVisible(!checkboxEqual.isSelected());
            }
        };
    }
}
