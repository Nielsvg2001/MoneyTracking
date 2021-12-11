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
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class HomeScreen extends JPanel {
    public UUID currentUser;

    public DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    public JList<String> list = new JList<>(defaultListModel);
    JButton addTicketButton = new JButton("Add Ticket");
    JButton calculateButton = new JButton("Calculate");
    JButton viewPersonList = new JButton("PersonList");
    private final JComboBox<Scheme> dropdownMode;
    private final JLabel personLabel;
    private final JButton clearButton = new JButton("Clear");

    JScrollPane scrollPane = new JScrollPane();
    JList<Ticket> ticketJList;

    public HomeScreen(UUID currentUser) {
        this.currentUser = currentUser;
        this.add(addTicketButton);
        this.add(calculateButton);
        this.add(viewPersonList);
        addTicketButtonButtonActionListener();
        addCalculateButtonButtonActionListener();
        addviewPersonListButtonActionListener();
        update_screen();
        this.add(scrollPane);
        personLabel = new JLabel(PersonController.getInstance().getPerson(currentUser).getName());
        this.add(personLabel);
        this.add(list);

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
                defaultListModel.clear();
                HashMap<UUID, Double> person_total = calculate.person_total(currentUser);
                for (UUID uuid : person_total.keySet()) {
                    Person person = PersonController.getInstance().getPerson(uuid);
                    defaultListModel.addElement(person.getName() + ": " + person_total.get(uuid));
                }
            }
            else{
                defaultListModel.clear();
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

    public void updateMode(){
        if(ColorScheme.getInstance().getMode() == Scheme.Dark){
            personLabel.setForeground(Color.WHITE);
            list.setForeground(Color.WHITE);
            list.setBackground(Color.DARK_GRAY);
        }
        else{
            personLabel.setForeground(Color.BLACK);
            list.setBackground(Color.white);
            list.setForeground(Color.BLACK);

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
            update_screen();
        });
    }
}
