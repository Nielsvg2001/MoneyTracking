package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.ColorScheme;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Controller.PersonController;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ViewFrame extends JFrame {

    private static ViewFrame single_instance = null;
    public UUID currentUser = PersonController.getInstance().personArray()[0].getId();
    public JPanel panelCont = new JPanel();
    public HomeScreen homeScreen = new HomeScreen(currentUser);
    public addTicketScreen addTicketScreen = new addTicketScreen();
    public PersonList personList = new PersonList();
    public addPersonScreen addPersonScreen = new addPersonScreen();
    CardLayout cardLayout = new CardLayout();

    private ViewFrame() {
        panelCont.setLayout(cardLayout);
        panelCont.add(homeScreen, "homeScreen");
        panelCont.add(addTicketScreen, "addTicketScreen");
        panelCont.add(personList, "PersonList");
        panelCont.add(addPersonScreen, "addPersonScreen");
        panelCont.add(editScreen,"EditScreen");
        cardLayout.show(panelCont, "homeScreen");
        this.add(panelCont);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setVisible(true);
        this.setSize(375, 667);
    }

    public static ViewFrame getInstance() {
        if (single_instance == null)
            single_instance = new ViewFrame() {
            };
        return single_instance;
    }

    public void showScreen(String name) {
        this.cardLayout.show(this.panelCont, name);
    }

    public void update_homescreen() {
        homeScreen.update_screen();
        panelCont.remove(addTicketScreen);
        addTicketScreen = new addTicketScreen();
        panelCont.add(addTicketScreen, "addTicketScreen");
    }

    public void update_personscreen() {
        personList.update_screen();
        addPersonScreen.reset();
    }

    public void update_Mode(){
        Color colorLight = Color.WHITE;
        Color colorDark = Color.DARK_GRAY;
        addPersonScreen.updateMode();
        addTicketScreen.updateMode();
        editScreen.updateMode();
        homeScreen.updateMode();
        switch (ColorScheme.getInstance().getMode()){
            case Light :{
                this.addPersonScreen.setBackground(colorLight);
                this.addTicketScreen.setBackground(colorLight);
                this.editScreen.setBackground(colorLight);
                this.homeScreen.setBackground(colorLight);
                this.personList.setBackground(colorLight);
                break;
            }
            case Color: {
                this.addPersonScreen.setBackground(Color.ORANGE);
                this.addTicketScreen.setBackground(Color.PINK);
                this.editScreen.setBackground(Color.RED);
                this.homeScreen.setBackground(Color.GREEN);
                this.homeScreen.setBackground(Color.cyan);
                break;
            }
            case Dark: {
                this.addPersonScreen.setBackground(colorDark);
                this.addTicketScreen.setBackground(colorDark);
                this.editScreen.setBackground(colorDark);
                this.homeScreen.setBackground(colorDark);
                this.personList.setBackground(colorDark);
                break;
            }
        }
    }
/*
    public static void main(String[] args) {
        ViewFrame viewFrame = ViewFrame.getInstance();
    }

 */
}
