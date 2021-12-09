package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.ColorScheme;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Scheme;

import javax.swing.*;
import java.awt.*;

public class EditScreen extends JPanel {

    private static EditScreen single_instance = null;
    private Person person;
    private final JButton removeButton = new JButton("remove");
    private final JButton backButton = new JButton("Back");
    private final JButton doneButton = new JButton("done");

    private final JTextField textBoxToEnterName = new JTextField(15);
    private final JTextField textBoxToEnterEmail = new JTextField(15);
    private final JTextField textBoxToEnterPhone = new JTextField(15);

    private final JLabel personLabel = new JLabel("Persoon");
    private final JLabel nameLabel = new JLabel("Name:");
    private final JLabel mailLabel = new JLabel("Email:");
    private final JLabel phoneLabel =new JLabel("Phone:");


    public static EditScreen getInstance() {
        if (single_instance == null)
            single_instance = new EditScreen() {
            };
        return single_instance;
    }

    private EditScreen() {
        System.out.println("personp::");
        System.out.println(person);

        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 1; gbc.ipady = 1;

        gbc.gridwidth = 3; gbc.gridx = 1; gbc.gridy = 0;
        this.add(personLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        this.add(nameLabel, gbc);
        gbc.gridx = 2; gbc.gridy = 1;
        this.add(textBoxToEnterName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        this.add(mailLabel, gbc);
        gbc.gridx = 2; gbc.gridy = 2;
        this.add(textBoxToEnterEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        this.add(phoneLabel, gbc);
        gbc.gridx = 2; gbc.gridy = 3;
        this.add(textBoxToEnterPhone, gbc);

        addremoveButtonListener();
        addbackButtonListener();
        adddoneButtonListener();

        gbc.gridx = 0; gbc.gridy = 5;
        this.add(backButton,gbc);


        gbc.gridx = 1; gbc.gridy = 5;
        this.add(removeButton,gbc);

        gbc.gridx = 2; gbc.gridy = 5;
        this.add(doneButton,gbc);
        PersonList.getInstance().update_screen();

        this.setBackground(Color.WHITE);

    }

    public void setPerson(Person p){

        this.person = p;

    }

    public void updatescreen(){
        if (person!=null) {
            if (person.getName() != null)
                textBoxToEnterName.setText(person.getName());
            if (person.getGSMNummer() != null) {
                textBoxToEnterPhone.setText(person.getGSMNummer());
            }
            if (person.getMail() != null) {
                textBoxToEnterEmail.setText(person.getMail());
            }
        }
    }

    public void updateMode(){
        System.out.println("updateModeAddpersonScreen");
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

    public void addbackButtonListener(){
        this.backButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("PersonList");
        });
    }

    public void adddoneButtonListener(){
        this.doneButton.addActionListener(listener ->
        {
            System.out.println("hier nog weg Database!!!!!!!!!!!!!");

            boolean edited = false;
            if(textBoxToEnterName.getText()!= null){
                person.setName(textBoxToEnterName.getText());
                edited = true;
            }
            if(textBoxToEnterEmail.getText()!= null){
                person.setMail(textBoxToEnterEmail.getText());
                edited = true;
            }
            if(textBoxToEnterPhone.getText()!= null){
                person.setGSMNummer(textBoxToEnterPhone.getText());
                edited = true;
            }
            if (edited){
            PersonController personController = PersonController.getInstance(PersonDatabase.getInstance());
            personController.removePerson(person);
            personController.addPerson(person);
            }
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("PersonList");
        });
    }



}
