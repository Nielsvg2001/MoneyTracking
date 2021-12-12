package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.Calculate;
import be.uantwerpen.fti.ColorScheme;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Scheme;
import be.uantwerpen.fti.Ticket.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class HomeScreen extends JPanel {
    private final UUID currentUser;

    private final DefaultListModel<String> calculateListModel = new DefaultListModel<>();
    private final JList<String> calculatelist = new JList<>(calculateListModel);

    private final DefaultListModel<String> ticketListModel = new DefaultListModel<>();
    private final JButton addTicketButton = new JButton("Add Ticket");
    private final JButton calculateButton = new JButton("Calculate");
    private final JButton viewPersonList = new JButton("PersonList");
    private final JComboBox<Scheme> dropdownMode;
    private final JLabel personLabel;
    private final JButton clearButton = new JButton("Clear");

    public HomeScreen(UUID currentUser) {
        this.currentUser = currentUser;
        this.add(addTicketButton);
        this.add(calculateButton);
        this.add(viewPersonList);
        addTicketButtonButtonActionListener();
        addCalculateButtonButtonActionListener();
        addviewPersonListButtonActionListener();
        JScrollPane scrollPane = new JScrollPane();
        JList<String> ticketlist = new JList<>(ticketListModel);
        scrollPane.setViewportView(ticketlist);
        this.add(scrollPane);
        this.add(calculatelist);
        personLabel = new JLabel(PersonController.getInstance().getPerson(currentUser).getName());
        this.add(personLabel);
        dropdownMode = new JComboBox<>(Scheme.values());
        dropdownMode.setVisible(true);
        this.add(dropdownMode);
        dropdownMode.setSelectedItem(Scheme.Light);
        addModeActionListener();
        this.add(clearButton);
        addClearButtonListener();
        this.setBackground(Color.WHITE);
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
            if (TicketController.getInstance().ticketArray().length !=0) {
                Calculate calculate = new Calculate();
                calculateListModel.clear();
                HashMap<UUID, Double> person_total = calculate.person_total(currentUser);
                for (UUID uuid : person_total.keySet()) {
                    Person person = PersonController.getInstance().getPerson(uuid);
                    calculateListModel.addElement(person.getName() + ": " + person_total.get(uuid));
                }
            }
            else{
                calculateListModel.clear();
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

    public void update_screen(boolean action, Ticket ticket) {
        if(action)
            ticketListModel.addElement(String.valueOf(ticket));
        else
            ticketListModel.removeElement(ticket);
    }

    public void updateMode(){
        if(ColorScheme.getInstance().getMode() == Scheme.Dark){
            personLabel.setForeground(Color.WHITE);
            calculatelist.setForeground(Color.WHITE);
            calculatelist.setBackground(Color.DARK_GRAY);
        }
        else{
            personLabel.setForeground(Color.BLACK);
            calculatelist.setBackground(Color.white);
            calculatelist.setForeground(Color.BLACK);

        }
    }

    public void addModeActionListener(){
        dropdownMode.addActionListener(listener ->
        {
            ColorScheme.getInstance().setMode((Scheme) dropdownMode.getSelectedItem());
            ViewFrame.getInstance().update_Mode();
        });
    }

    public void addClearButtonListener(){
        clearButton.addActionListener(listener ->
        {
            TicketController ticketController = TicketController.getInstance();
            Ticket[] removeArray = ticketController.ticketArray();
            for (Ticket ticket : removeArray){
                ticketController.removeTicket(ticket);
            }
        });
    }
}
