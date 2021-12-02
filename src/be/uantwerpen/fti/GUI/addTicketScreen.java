package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class addTicketScreen extends JPanel implements ItemListener {
    JComboBox<Person> dropdownPersons = new JComboBox<>();
    JButton doneButton = new JButton("Done");
    Person[] choices;
    String[] choices2;

    public addTicketScreen(){
        this.updatePersons();
        this.add(doneButton);
        this.setBackground(Color.pink);
        addDoneButtonActionListener();


    }

    public void addDoneButtonActionListener()
    {
        this.doneButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("homeScreen");
        });
    }

    public void updatePersons(){
        Person p1 = new Person("jos1");
        Person p2 = new Person("jos","jos@ua.com","01236");
        Person p3 = new Person("jos3");
        this.choices = new Person []{ p1,p2,p3};
        this.choices2= new String[]{p1.getName(),p2.getName(),p3.getName()};


        //String[] choices = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};

        dropdownPersons = new JComboBox<>(choices);


        dropdownPersons.setVisible(true);
        this.add(dropdownPersons);

        dropdownPersons.addItemListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e1)
    {
        if(e1.getStateChange() == ItemEvent.SELECTED)
            System.out.println("selected");
        else
            System.out.println(" notselected");
        Person p = (Person) e1.getItem();
        System.out.println(p);
    }

}
