package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;

public class ViewFrame extends JFrame {
    public JPanel panelCont = new JPanel();
    public JPanel homeScreen = new HomeScreen();
    public JPanel addTicketScreen = new addTicketScreen();
    CardLayout cardLayout = new CardLayout();
    private static ViewFrame single_instance = null;

    private ViewFrame() {
        panelCont.setLayout(cardLayout);
        panelCont.add(homeScreen, "homeScreen");
        panelCont.add(addTicketScreen, "addTicketScreen");
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

    public static void main(String[] args) {
        ViewFrame viewFrame = ViewFrame.getInstance();
    }
}
