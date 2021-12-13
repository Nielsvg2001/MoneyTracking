package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.ColorScheme;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Scheme;

import javax.swing.*;
import java.awt.*;

public class addPersonScreen extends JPanel {
    private final PersonController personController = PersonController.getInstance();

    private final JButton doneButton = new JButton("Done");
    private final JButton backButton = new JButton("Back");

    private final JTextField textBoxToEnterName = new JTextField(10);
    private final JTextField textBoxToEnterEmail = new JTextField(10);
    private final JTextField textBoxToEnterPhone = new JTextField(10);

    private final JLabel personLabel = new JLabel("Nieuwe Persoon");
    private final JLabel nameLabel = new JLabel("Name:");
    private final JLabel mailLabel = new JLabel("Email:");
    private final JLabel phoneLabel =new JLabel("Phone:");
    private final JLabel errorLabel = new JLabel("error");


    public addPersonScreen() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 1;
        gbc.ipady = 1;

        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(personLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(nameLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(textBoxToEnterName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(mailLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        this.add(textBoxToEnterEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(phoneLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        this.add(textBoxToEnterPhone, gbc);

        gbc.gridwidth = 3;
        gbc.gridx = 2;
        gbc.gridy = 4;
        this.add(doneButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(backButton, gbc);

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(errorLabel, gbc);
        errorLabel.setForeground(Color.black);
        errorLabel.setBackground(Color.PINK);
        errorLabel.setOpaque(true);
        errorLabel.setVisible(false);

        this.setBackground(Color.WHITE);

        doneButtonButtonActionListener();
        backButtonButtonActionListener();

    }

    public void reset(){
        textBoxToEnterName.setText("");
        textBoxToEnterEmail.setText("");
        textBoxToEnterPhone.setText("");
    }


    public void doneButtonButtonActionListener() {
        this.doneButton.addActionListener(listener ->
        {
            if (!textBoxToEnterName.getText().isEmpty()) {
                Person p = new Person(textBoxToEnterName.getText());
                if (!textBoxToEnterEmail.getText().isEmpty())
                    p.setMail(textBoxToEnterEmail.getText());
                if (!textBoxToEnterPhone.getText().isEmpty())
                    p.setGSMNummer(textBoxToEnterPhone.getText());
                personController.addPerson(p);
                ViewFrame viewFrame = ViewFrame.getInstance();
                viewFrame.showScreen("PersonList");
                viewFrame.update_personscreen();
            }
            else{
                errorLabel.setText("naam mag niet leeg zijn");
                errorLabel.setVisible(true);
            }
        });
    }

    public void backButtonButtonActionListener()
    {
        this.backButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("PersonList");
            viewFrame.update_personscreen();
        });
    }

    public void updateMode(){
        if(ColorScheme.getInstance().getMode() == Scheme.Dark){
            personLabel.setForeground(Color.WHITE);
            nameLabel.setForeground(Color.WHITE);
            mailLabel.setForeground(Color.WHITE);
            phoneLabel.setForeground(Color.WHITE);
        }
        else{
            personLabel.setForeground(Color.BLACK);
            nameLabel.setForeground(Color.BLACK);
            mailLabel.setForeground(Color.BLACK);
            phoneLabel.setForeground(Color.BLACK);
        }
    }
}
