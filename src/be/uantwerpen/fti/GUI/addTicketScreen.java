package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Database.Database;
import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public class addTicketScreen extends JPanel {
    JComboBox<Person> dropdownPersons = new JComboBox<>();
    JComboBox<TicketType> dropdownType = new JComboBox<>();
    JButton doneButton = new JButton("Done");
    Person payer;
    JTextField textBoxToEnterName;
    JTextField priceField;
    JCheckBox checkbox = new JCheckBox("split equal");
    ArrayList<HashMap<JCheckBox,HashMap<UUID, Double>>> checklist = new ArrayList<>();

    private JTextField textField;

    public addTicketScreen(){
        this.addType();
        this.addNameField();
        this.updatePersons();
        this.add(doneButton);
        this.setBackground(Color.pink);
        priceField = new JTextField(10);
        this.add(priceField);
        this.add(checkbox);
        checkbox.setSelected(true);
        addDoneButtonActionListener();
    }

    public void addDoneButtonActionListener()
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
                System.out.println(e);
            }
            if (checkbox.isSelected()) {
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
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("homeScreen");

        });
    }

    public void updatePersons(){
        PersonDatabase db = PersonDatabase.getInstance();
        Person[] personList = db.PersonList().toArray(new Person[0]);
        dropdownPersons = new JComboBox<>(personList);
        dropdownPersons.setVisible(true);
        this.add(dropdownPersons);


        for (Person p : personList){
            double toPay = 0.0;
            HashMap<UUID, Double> hm = new HashMap<>();
            hm.put(p.getId(),toPay);
            HashMap<JCheckBox, HashMap<UUID,Double>> hmLong = new HashMap<>();
            hmLong.put(new JCheckBox(p.getName()),hm);
            checklist.add(hmLong);
        }
        for (HashMap<JCheckBox, HashMap<UUID,Double>> hmjcb : checklist){
            for ( JCheckBox key : hmjcb.keySet() ) {
                this.add(key);
                key.setSelected(true);
            }
        }

    }

    public void addType(){
        TicketType[] typeList = TicketType.values();
        dropdownType = new JComboBox<>(typeList);
        dropdownType.setVisible(true);
        this.add(dropdownType);
    }

    public void addNameField(){
        textBoxToEnterName = new JTextField(21);
        this.add(textBoxToEnterName);
        System.out.println(textBoxToEnterName.getText());
    }




}
