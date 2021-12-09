package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;

public class PersonList extends JPanel {
    JButton addPersonButton = new JButton("Voeg nieuwe persoon toe");
    JButton homescreenButton = new JButton("homescreen");
    JButton editButton = new JButton("Edit");
    JScrollPane scrollPane = new JScrollPane();
    JList<Person> PersonJlist;

    public PersonList() {
        this.setBackground(Color.cyan);
        this.add(addPersonButton);
        this.add(homescreenButton);
        addPersonButtonButtonActionListener();
        homescreenButtonnButtonActionListener();
        editButtonActionListener();
        update_screen();
        this.add(scrollPane);
        this.add(editButton);
    }

    public void addPersonButtonButtonActionListener()
    {
        this.addPersonButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("addPersonScreen");
        });
    }

    public void homescreenButtonnButtonActionListener()
    {
        this.homescreenButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("homeScreen");
        });
    }

    public void editButtonActionListener()
    {
        this.editButton.addActionListener(listener ->
        {
            Person p = PersonJlist.getModel().getElementAt(PersonJlist.getSelectedIndex());
            EditScreen editScreen = EditScreen.getInstance();
            editScreen.setPerson(p);
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("EditScreen");
        });
    }


    public void update_screen(){
        PersonJlist = new JList<>(PersonDatabase.getInstance().PersonList().toArray(new Person[0]));
        this.scrollPane.setViewportView(PersonJlist);
    }
}
