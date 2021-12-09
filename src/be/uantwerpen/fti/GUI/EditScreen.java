package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;

import javax.swing.*;
import java.awt.*;

public class EditScreen extends JPanel {

    private static EditScreen single_instance = null;
    private Person person;
    private JButton removeButton = new JButton("remove");

    public static EditScreen getInstance() {
        if (single_instance == null)
            single_instance = new EditScreen() {
            };
        return single_instance;
    }

    private EditScreen() {
        this.setBackground(Color.red);
        addremoveButtonListener();
        this.add(removeButton);

    }

    public void setPerson(Person p){
        this.person = p;
    }

    public void addremoveButtonListener(){
        this.removeButton.addActionListener(listener ->
        {
            System.out.println("hier dit nog wegdoen persondatabase!!!!!");
            PersonController.getInstance(PersonDatabase.getInstance()).removePerson(person);
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("PersonList");
            viewFrame.update_personscreen();
        });
    }

}
