package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Person;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ViewFrame extends JFrame {

    public UUID currentUser = PersonDatabase.getInstance().PersonList().toArray(new Person[0])[0].getId();

    public JPanel panelCont = new JPanel();
    public HomeScreen homeScreen = new HomeScreen(currentUser);
    public addTicketScreen addTicketScreen = new addTicketScreen();
    public PersonList personList = new PersonList();
    public addPersonScreen addPersonScreen = new addPersonScreen();
    CardLayout cardLayout = new CardLayout();
    private static ViewFrame single_instance = null;

    private ViewFrame() {
        panelCont.setLayout(cardLayout);
        panelCont.add(homeScreen, "homeScreen");
        panelCont.add(addTicketScreen, "addTicketScreen");
        panelCont.add(personList, "PersonList");
        panelCont.add(addPersonScreen, "addPersonScreen");
        cardLayout.show(panelCont, "homeScreen");
        this.add(panelCont);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,500);
        this.setVisible(true);
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

    public void update_homescreen(){
        homeScreen.update_screen();
        panelCont.remove(addTicketScreen);
        addTicketScreen = new addTicketScreen();
        panelCont.add(addTicketScreen, "addTicketScreen");
    }

    public void update_personscreen(){
        personList.update_screen();
        addPersonScreen.reset();
    }

    public static void main(String[] args) {
        ViewFrame viewFrame = ViewFrame.getInstance();
    }
}
