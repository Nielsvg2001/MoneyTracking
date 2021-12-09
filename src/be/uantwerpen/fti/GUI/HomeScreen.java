package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Calculate;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class HomeScreen extends JPanel {
    public UUID currentUser;
    public DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    public JList<String> list = new JList<>(defaultListModel);
    JButton addTicketButton = new JButton("+");
    JButton calculateButton = new JButton("Calculate");
    JButton viewPersonList = new JButton("PersonList");
    JScrollPane scrollPane = new JScrollPane();
    JList<Ticket> ticketJList;

    public HomeScreen(UUID currentUser) {
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
        this.add(new JLabel(PersonController.getInstance().getPerson(currentUser).getName()));
        this.add(list);

    }

    public void addTicketButtonButtonActionListener() {
        this.addTicketButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("addTicketScreen");
        });
    }

    public void addCalculateButtonButtonActionListener() {
        this.calculateButton.addActionListener(listener ->
        {
            Calculate calculate = new Calculate();
            defaultListModel.clear();
            HashMap<UUID, Double> person_total = calculate.person_total(currentUser);
            for (UUID uuid : person_total.keySet()) {
                Person person = PersonController.getInstance().getPerson(uuid);
                defaultListModel.addElement(person.getName() + ": " + person_total.get(uuid));
            }
        });
    }

    public void addviewPersonListButtonActionListener() {
        this.viewPersonList.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("PersonList");
        });
    }

    public void update_screen() {
        ticketJList = new JList<>(TicketController.getInstance().ticketArray());
        this.scrollPane.setViewportView(ticketJList);
    }
}
