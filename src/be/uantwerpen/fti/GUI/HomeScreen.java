package be.uantwerpen.fti.GUI;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {
    JButton addTicketButton = new JButton("+");

    public HomeScreen(){
        this.add(addTicketButton);
        this.setBackground(Color.green);
        addTicketButtonButtonActionListener();
    }

    public void addTicketButtonButtonActionListener()
    {
        this.addTicketButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("addTicketScreen");
        });
    }
}
