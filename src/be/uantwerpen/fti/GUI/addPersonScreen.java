package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Person;

import javax.swing.*;
import java.awt.*;

public class addPersonScreen extends JPanel {
    PersonController personController = PersonController.getInstance(PersonDatabase.getInstance());

    JButton doneButton = new JButton("Done");

    JTextField textBoxToEnterName = new JTextField(10);
    JTextField textBoxToEnterEmail = new JTextField(10);
    JTextField textBoxToEnterPhone = new JTextField(10);

    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();


    public addPersonScreen(){
        this.setLayout(gridBagLayout);
        gbc.ipadx = 1; gbc.ipady = 1;

        gbc.gridwidth = 3; gbc.gridx = 1; gbc.gridy = 0;
        this.add(new JLabel("Nieuwe Persoon"), gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        this.add(new JLabel("Name:"), gbc);
        gbc.gridx = 2; gbc.gridy = 1;
        this.add(textBoxToEnterName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        this.add(new JLabel("Email:"), gbc);
        gbc.gridx = 2; gbc.gridy = 2;
        this.add(textBoxToEnterEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        this.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 2; gbc.gridy = 3;
        this.add(textBoxToEnterPhone, gbc);

        gbc.gridwidth = 3; gbc.gridx = 2; gbc.gridy = 4;
        this.add(doneButton, gbc);

        this.setBackground(Color.ORANGE);
        doneButtonButtonActionListener();
    }

    public void reset(){
        textBoxToEnterName.setText("");
        textBoxToEnterEmail.setText("");
        textBoxToEnterPhone.setText("");
    }


    public void doneButtonButtonActionListener()
    {
        this.doneButton.addActionListener(listener ->
        {
            if(!textBoxToEnterEmail.getText().isEmpty() & !textBoxToEnterPhone.getText().isEmpty())
                personController.addPerson(new Person(textBoxToEnterName.getText(), textBoxToEnterEmail.getText(), textBoxToEnterPhone.getText()));
            else if (!textBoxToEnterName.getText().isEmpty())
                personController.addPerson(new Person(textBoxToEnterName.getText()));
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("PersonList");
            viewFrame.update_personscreen();
        });
    }
}
