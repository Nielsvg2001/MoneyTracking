package be.uantwerpen.fti.GUI;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {
    JButton addTicketButton = new JButton("+");
    JButton calculateButton = new JButton("Calculate");

    public HomeScreen(){
        this.add(addTicketButton);
        this.add(calculateButton);
        this.setBackground(Color.green);
        addTicketButtonButtonActionListener();
        addCalculateButtonButtonActionListener();
    }

    public void addTicketButtonButtonActionListener()
    {
        this.addTicketButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("addTicketScreen");
        });
    }

    public void addCalculateButtonButtonActionListener()
    {
        this.calculateButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            // hier nog iets doen als je op calculate duwt   viewFrame.showScreen("");
            System.out.println("calculate");
        });
    }
}
