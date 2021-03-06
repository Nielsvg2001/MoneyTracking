package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Calculate;
import be.uantwerpen.fti.ColorScheme;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Scheme;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class EditScreen extends JPanel {

    private static EditScreen single_instance = null;
    private final JButton removeButton = new JButton("remove");
    private final JButton backButton = new JButton("Back");
    private final JButton doneButton = new JButton("done");
    private final JTextField textBoxToEnterName = new JTextField(15);
    private final JTextField textBoxToEnterEmail = new JTextField(15);
    private final JTextField textBoxToEnterPhone = new JTextField(15);
    private final JLabel personLabel = new JLabel("Persoon");
    private final JLabel nameLabel = new JLabel("Name:");
    private final JLabel mailLabel = new JLabel("Email:");
    private final JLabel phoneLabel = new JLabel("Phone:");
    private final JLabel errorLabel = new JLabel("error");
    private Person person;


    private EditScreen() {

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

        addremoveButtonListener();
        addbackButtonListener();
        adddoneButtonListener();

        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(backButton, gbc);


        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(removeButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        this.add(doneButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        this.add(errorLabel, gbc);
        errorLabel.setForeground(Color.black);
        errorLabel.setBackground(Color.PINK);
        errorLabel.setOpaque(true);
        errorLabel.setVisible(false);

        this.setBackground(Color.WHITE);

    }

    public static EditScreen getInstance() {
        if (single_instance == null)
            single_instance = new EditScreen() {
            };
        return single_instance;
    }

    public void setPerson(Person p) {
        this.person = p;
    }

    public void updatescreen() {
        if (person != null) {
            if (person.getName() != null)
                textBoxToEnterName.setText(person.getName());
            else
                textBoxToEnterName.setText("");

            if (person.getGSMNummer() != null)
                textBoxToEnterPhone.setText(person.getGSMNummer());
            else textBoxToEnterPhone.setText("");
            
            if (person.getMail() != null)
                textBoxToEnterEmail.setText(person.getMail());
            else
                textBoxToEnterEmail.setText("");
        }
        errorLabel.setVisible(false);
    }

    public void updateMode() {
        if (ColorScheme.getInstance().getMode() == Scheme.Dark) {
            personLabel.setForeground(Color.WHITE);
            nameLabel.setForeground(Color.WHITE);
            mailLabel.setForeground(Color.WHITE);
            phoneLabel.setForeground(Color.WHITE);
        } else {
            personLabel.setForeground(Color.BLACK);
            nameLabel.setForeground(Color.BLACK);
            mailLabel.setForeground(Color.BLACK);
            phoneLabel.setForeground(Color.BLACK);
        }
    }

    public void addremoveButtonListener() {
        this.removeButton.addActionListener(listener ->
        {
            boolean remove = true;
            HashMap<UUID, Double> total = new Calculate().person_total(person.getId());
            if (total != null) {
                for (UUID uuid1 : total.keySet())
                    if (total.get(uuid1) != 0) {
                        remove = false;
                        break;
                    }
            }

            if (remove) {
                PersonController.getInstance().removePerson(person);
                ViewFrame.getInstance().showScreen("PersonList");

            } else {
                errorLabel.setText("kan niet removed worden, er staat nog een bedrag open");
                errorLabel.setVisible(true);
            }
        });
    }

    public void addbackButtonListener() {
        this.backButton.addActionListener(listener ->
        {
            ViewFrame.getInstance().showScreen("PersonList");
        });
    }

    public void adddoneButtonListener() {
        this.doneButton.addActionListener(listener ->
        {
            boolean edited = false;
            if (!Objects.equals(textBoxToEnterName.getText(), "")) {
                person.setName(textBoxToEnterName.getText());

                if (textBoxToEnterEmail.getText() != null) {
                    person.setMail(textBoxToEnterEmail.getText());
                }
                if (textBoxToEnterPhone.getText() != null) {
                    person.setGSMNummer(textBoxToEnterPhone.getText());
                }
                ViewFrame.getInstance().showScreen("PersonList");
                ViewFrame.getInstance().calculate();
                ViewFrame.getInstance().update_addTicketScreen();
            } else {
                errorLabel.setText("name cannot be empty");
                errorLabel.setVisible(true);
            }
        });
    }


}
