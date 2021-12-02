package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {
    JButton addTicketButton = new JButton("+");
    JButton calculateButton = new JButton("Calculate");

    JScrollPane scrollPane = new JScrollPane();
    JList<Ticket> ticketJList;

    public HomeScreen(){
        this.add(addTicketButton);
        this.add(calculateButton);
        this.setBackground(Color.green);
        addTicketButtonButtonActionListener();
        addCalculateButtonButtonActionListener();
        update_screen();
        scrollPane.setViewportView(ticketJList);
        this.add(scrollPane);

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
            // hier nog iets doen als je op calculate duwt viewFrame.showScreen("");
            System.out.println("calculate");
        });
    }

    public void update_screen() {
        ticketJList = new JList<>(TicketDatabase.getInstance().ticketList().toArray(new Ticket[0]));
        this.scrollPane.setViewportView(ticketJList);
    }
}
