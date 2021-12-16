package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.*;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class HomeScreen extends JPanel {
    private final PersonController personController = PersonController.getInstance();
    private final TicketController ticketController = TicketController.getInstance();

    private final DefaultListModel<String> calculateListModel = new DefaultListModel<>();
    private final JList<String> calculatelist = new JList<>(calculateListModel);
    private final DefaultListModel<Ticket> ticketListModel = new DefaultListModel<>();

    private final JButton addTicketButton = new JButton("Add Ticket");
    private final JButton PersonListButton = new JButton("PersonList");

    private final JComboBox<Scheme> dropdownMode = new JComboBox<>(Scheme.values());
    private final JButton clearButton = new JButton("Clear");

    public HomeScreen() {
        JScrollPane scrollPane = new JScrollPane();
        JList<Ticket> ticketlist = new JList<>(ticketListModel);
        scrollPane.setViewportView(ticketlist);

        this.add(addTicketButton);
        this.add(PersonListButton);
        this.add(scrollPane);
        this.add(calculatelist);
        this.add(dropdownMode);
        dropdownMode.setSelectedItem(Scheme.Light);
        addModeActionListener();
        this.add(clearButton);
        addClearButtonListener();
        this.setBackground(Color.WHITE);

        addTicketButtonButtonActionListener();
        addviewPersonListButtonActionListener();
    }

    public void addTicketButtonButtonActionListener() {
        this.addTicketButton.addActionListener(listener ->
        {
            ViewFrame.getInstance().showScreen("addTicketScreen");
        });
    }

    public void calculate() {
        if (ticketController.ticketArray().length != 0) {
            Calculate calculate = new Calculate();
            calculateListModel.clear();
            calculateListModel.addElement("Current user -> "  + PersonController.getInstance().getPerson(Login.getInstance().getCurrentUser()).getName());
            HashMap<UUID, Double> person_total = calculate.person_total(Login.getInstance().getCurrentUser());
            for (UUID uuid : person_total.keySet()) {
                Person person = personController.getPerson(uuid);
                calculateListModel.addElement(person.getName() + ": " + person_total.get(uuid));
            }
        } else {
            calculateListModel.clear();
        }
    }

    public void addviewPersonListButtonActionListener() {
        this.PersonListButton.addActionListener(listener ->
        {
            ViewFrame.getInstance().showScreen("PersonList");
        });
    }

    public void update_screen(boolean action, Ticket ticket) {
        if (action) {
            ticketListModel.addElement(ticket);
        }
        else
            ticketListModel.removeElement(ticket);
    }

    public void updateMode() {
        if (ColorScheme.getInstance().getMode() == Scheme.Dark) {
            // currentUserLabel.setForeground(Color.WHITE);
            calculatelist.setForeground(Color.WHITE);
            calculatelist.setBackground(Color.DARK_GRAY);
        } else {
            // currentUserLabel.setForeground(Color.BLACK);
            calculatelist.setBackground(Color.white);
            calculatelist.setForeground(Color.BLACK);

        }
    }

    public void addModeActionListener() {
        dropdownMode.addActionListener(listener ->
        {
            ColorScheme.getInstance().setMode((Scheme) dropdownMode.getSelectedItem());
            ViewFrame.getInstance().update_Mode();
        });
    }

    public void addClearButtonListener() {
        clearButton.addActionListener(listener ->
        {
            Ticket[] removeArray = ticketController.ticketArray();
            for (Ticket ticket : removeArray) {
                ticketController.removeTicket(ticket);
            }
            this.ticketListModel.clear();
        });
    }
}
