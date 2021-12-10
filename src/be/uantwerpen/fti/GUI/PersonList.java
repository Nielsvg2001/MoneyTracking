package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.ColorScheme;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Person;

import javax.swing.*;
import java.awt.*;

public class PersonList extends JPanel {
    JButton addPersonButton = new JButton("Voeg nieuwe persoon toe");
    JButton homescreenButton = new JButton("homescreen");
    JButton editButton = new JButton("Edit");
    JScrollPane scrollPane = new JScrollPane();
    JList<Person> PersonJlist;

    public PersonList() {
        this.add(addPersonButton);
        this.add(homescreenButton);
        addPersonButtonButtonActionListener();
        homescreenButtonnButtonActionListener();
        editButtonActionListener();
        update_screen();
        scrollPane.setPreferredSize(new Dimension(100, 300));
        this.add(scrollPane);
        this.add(editButton);
        this.setBackground(Color.WHITE);

    }

    public void addPersonButtonButtonActionListener() {
        this.addPersonButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("addPersonScreen");
        });
    }

    public void homescreenButtonnButtonActionListener() {
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
            editScreen.updatescreen();
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("EditScreen");
        });
    }


    public void update_screen() {
        PersonJlist = new JList<>(PersonController.getInstance().personArray());
        PersonJlist.setSelectedIndex(0);
        this.scrollPane.setViewportView(PersonJlist);
    }
}
