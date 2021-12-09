package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Calculate;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class HomeScreen extends JPanel {
    public UUID currentUser;

    JButton addTicketButton = new JButton("+");
    JButton calculateButton = new JButton("Calculate");
    JButton viewPersonList = new JButton("PersonList");

    JScrollPane scrollPane = new JScrollPane();
    JList<Ticket> ticketJList;

    public HomeScreen(UUID currentUser){
        this.currentUser = currentUser;
        this.add(addTicketButton);
        this.add(calculateButton);
        this.add(viewPersonList);
        this.setBackground(Color.green);
        addTicketButtonButtonActionListener();
        addCalculateButtonButtonActionListener();
        addviewPersonListButtonActionListener();
        update_screen();
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
            Calculate calculate = new Calculate();
            HashMap<UUID, Double> person_total =  calculate.person_total(currentUser);
        });
    }

    public void addviewPersonListButtonActionListener()
    {
        this.viewPersonList.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("PersonList");
        });
    }

    public void update_screen() {
        ticketJList = new JList<>(TicketDatabase.getInstance().ticketList().toArray(new Ticket[0]));
        this.scrollPane.setViewportView(ticketJList);
    }
}
