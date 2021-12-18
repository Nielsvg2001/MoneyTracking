package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.ColorScheme;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Login;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Scheme;

import javax.swing.*;
import java.awt.*;

public class PersonList extends JPanel {
    private final JButton addPersonButton = new JButton("Voeg nieuwe persoon toe");
    private final JButton homescreenButton = new JButton("homescreen");
    private final JButton editButton = new JButton("Edit");

    private final DefaultListModel<Person> personListModel = new DefaultListModel<>();
    private final JList<Person> personList = new JList<>(personListModel);

    private final JComboBox<Person> dropdownCurrentUser = new JComboBox<>();
    private final DefaultComboBoxModel<Person> comboBoxModel = new DefaultComboBoxModel<>();
    private final JLabel currentUserLabel = new JLabel("Current user");

    public PersonList() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(100, 300));

        personList.setSelectedIndex(0);
        scrollPane.setViewportView(personList);

        this.add(addPersonButton);
        this.add(homescreenButton);
        this.add(currentUserLabel);
        dropdownCurrentUser.setModel(comboBoxModel);
        this.add(dropdownCurrentUser);
        this.add(scrollPane);
        this.add(editButton);
        this.setBackground(Color.WHITE);

        addPersonButtonButtonActionListener();
        homescreenButtonnButtonActionListener();
        editButtonActionListener();
        editcurruserActionListener();
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
                ViewFrame.getInstance().showScreen("homeScreen"));
    }

    public void editButtonActionListener() {
        this.editButton.addActionListener(listener ->
        {
            if(!personList.isSelectionEmpty()) {
                Person p = personList.getModel().getElementAt(personList.getSelectedIndex());
                EditScreen editScreen = EditScreen.getInstance();
                editScreen.setPerson(p);
                editScreen.updatescreen();
                ViewFrame.getInstance().showScreen("EditScreen");
            }
        });
    }


    public void editcurruserActionListener() {
        this.dropdownCurrentUser.addActionListener(listener ->
        {
            if(!personList.isSelectionEmpty()) {
                Person person = (Person) dropdownCurrentUser.getSelectedItem();
                if (person != null) {
                    Login.getInstance().setCurrentUser(person.getId());
                    ViewFrame.getInstance().calculate();
                }
            }
        });
    }


    public void update_screen(boolean action, Person person) {
        if (action) {
            comboBoxModel.addElement(person);
            personListModel.addElement(person);
        }
        else {
            comboBoxModel.removeElement(person);
            personListModel.removeElement(person);
        }
        personList.setSelectedIndex(0);
        if(personListModel.getSize() == 1)
            Login.getInstance().setCurrentUser(person.getId());
    }

    public void updateMode(){
        switch (ColorScheme.getInstance().getMode()) {
            case Light: {
                currentUserLabel.setForeground(Color.BLACK);
                break;
            }
            case Color: {
                currentUserLabel.setForeground(Color.BLACK);
                break;
            }
            case Dark: {
                currentUserLabel.setForeground(Color.WHITE);
                break;
            }
        }
    }
}
