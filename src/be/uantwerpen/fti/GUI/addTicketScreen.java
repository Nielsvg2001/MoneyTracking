package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class addTicketScreen extends JPanel {
    JComboBox<Person> dropdownPersons = new JComboBox<>();
    JComboBox<TicketType> dropdownType = new JComboBox<>();
    JButton doneButton = new JButton("Done");
    Person payer;

    public addTicketScreen(){
        this.addType();
        this.updatePersons();
        this.add(doneButton);
        this.setBackground(Color.pink);
        addDoneButtonActionListener();
    }

    public void addDoneButtonActionListener()
    {
        this.doneButton.addActionListener(listener ->
        {
            String name = "nog niks";
            TicketFactory ticketFactory = new TicketFactory();
            Ticket newticket = ticketFactory.getTicket((TicketType) (Objects.requireNonNull(dropdownType.getSelectedItem())),name);
            System.out.println(newticket);
            payer = (Person) dropdownPersons.getSelectedItem();
            System.out.println("payer: " +payer);

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
    }

    public void addType(){
        TicketType[] typeList = TicketType.values();
        dropdownType = new JComboBox<>(typeList);
        dropdownType.setVisible(true);
        this.add(dropdownType);
    }
}
