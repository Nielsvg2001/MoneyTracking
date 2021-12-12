package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.ColorScheme;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ViewFrame extends JFrame {

    private static ViewFrame single_instance = null;
    private final UUID currentUser = PersonController.getInstance().personArray()[0].getId();
    private final JPanel panelCont = new JPanel();
    private final HomeScreen homeScreen = new HomeScreen(currentUser);
    private addTicketScreen addTicketScreen = new addTicketScreen();
    private final PersonList personList = new PersonList();
    private final addPersonScreen addPersonScreen = new addPersonScreen();
    private final EditScreen editScreen = EditScreen.getInstance();
    private final CardLayout cardLayout = new CardLayout();
    private final Color colorLight = Color.WHITE;
    private final Color colorDark = Color.DARK_GRAY;

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

    public void update_homescreen(boolean action, Ticket ticket) {
        homeScreen.update_screen(action, ticket);
        update_addTicketScreen();
    }

    public void update_personscreen() {
        personList.update_screen();
        addPersonScreen.reset();
        update_addTicketScreen();
    }

    public void update_addTicketScreen(){
        panelCont.remove(addTicketScreen);
        addTicketScreen = new addTicketScreen();
        panelCont.add(addTicketScreen, "addTicketScreen");
        addTicketScreen.updateMode();
        switch (ColorScheme.getInstance().getMode()){
            case Light :{
                this.addTicketScreen.setBackground(colorLight);
                break;
            }
            case Color: {
                this.addTicketScreen.setBackground(Color.PINK);
                break;
            }
            case Dark: {
                this.addTicketScreen.setBackground(colorDark);
                break;
            }
        }
    }

    public void update_Mode(){
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
                this.personList.setBackground(Color.cyan);
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
}
