package be.uantwerpen.fti.GUI;

import be.uantwerpen.fti.ColorScheme;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Scheme;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public class addTicketScreen extends JPanel {
    private final JComboBox<Person> dropdownPersons;
    private final JComboBox<TicketType> dropdownType;
    private final JButton doneButton = new JButton("Done");
    private final JButton backButton = new JButton("Back");
    private final JTextField textBoxToEnterName;
    private final JTextField priceField;
    private final JCheckBox checkboxEqual = new JCheckBox("split equal");
    private final ArrayList<HashMap<JCheckBox, HashMap<UUID, Double>>> checklist = new ArrayList<>();
    private final HashMap<UUID, JTextField> toPayList = new HashMap<>();
    private Person payer;
    private ItemListener checkboxEqualListener;
    private ItemListener checkboxPersonListener;
    private final JLabel nameLabel = new JLabel("Naam");
    private final JLabel typeLabel = new JLabel("Type");
    private final JLabel payerLabel = new JLabel("Payer");
    private final JLabel toPayLabel = new JLabel("Bedrag");
    private final JLabel errorLabel = new JLabel("error");


    public addTicketScreen() { // add all button's, textfields, checkboxes, dropdown's and listeners
        setcheckboxPersonListener();
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 1;
        gbc.ipady = 0;


        gbc.gridx = 0;
        gbc.gridy = 0;

        this.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);

        // add textfield for name
        textBoxToEnterName = new JTextField(20);
        this.add(textBoxToEnterName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(typeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        // Add dropdown Type
        TicketType[] typeList = TicketType.values();
        dropdownType = new JComboBox<>(typeList);
        this.add(dropdownType, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(payerLabel, gbc);
        //Persons
        Person[] personList = PersonController.getInstance().personArray();

        // create dropdown
        dropdownPersons = new JComboBox<>(personList);
        dropdownPersons.setVisible(true);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(dropdownPersons, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(toPayLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        priceField = new JTextField(10);
        this.add(priceField, gbc);

        //this.updatePersons();
        // create HashMap for checkboxes for every Person

        for (Person p : personList) {

            double toPay = 0.0;
            HashMap<UUID, Double> hm = new HashMap<>();
            hm.put(p.getId(), toPay);
            HashMap<JCheckBox, HashMap<UUID, Double>> hmLong = new HashMap<>();
            hmLong.put(new JCheckBox(p.getName()), hm);
            checklist.add(hmLong);

        }

        // create Hashmap for all checkboxes for every Person
        int teller = 4;
        gbc.gridx = 0;

        for (HashMap<JCheckBox, HashMap<UUID, Double>> hmjcb : checklist) {
            for (JCheckBox checkboxPerson : hmjcb.keySet()) {
                gbc.gridy = teller;
                this.add(checkboxPerson, gbc);
                checkboxPerson.setSelected(true);
                UUID key2 = hmjcb.get(checkboxPerson).keySet().iterator().next();
                toPayList.put(key2, new JTextField(10));
                teller += 1;
            }
        }


        // add all the textboxes to Jpanel and set them to not visual
        teller = 4;
        gbc.gridx = 1;
        for (HashMap<JCheckBox, HashMap<UUID, Double>> hmjcb : checklist) {
            gbc.gridy = teller;
            JCheckBox checkboxPerson = hmjcb.keySet().iterator().next();
            checkboxPerson.addItemListener(checkboxPersonListener);
            UUID key2 = hmjcb.get(checkboxPerson).keySet().iterator().next();
            this.add(toPayList.get(key2), gbc);
            toPayList.get(key2).setVisible(false);
            teller += 1;
        }

        gbc.gridx = 1;
        gbc.gridy = teller + 1;
        setcheckboxEqualListener();
        checkboxEqual.addItemListener(checkboxEqualListener);
        this.add(checkboxEqual, gbc);

        gbc.gridx = 1;
        gbc.gridy = teller + 2;
        gbc.ipady = 10;
        this.add(new JLabel(""), gbc);
        gbc.ipady = 1;

        gbc.gridx = 0;
        gbc.gridy = teller + 3;
        this.add(backButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = teller + 3;
        this.add(doneButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = teller+4;
        this.add(errorLabel, gbc);
        errorLabel.setForeground(Color.black);
        errorLabel.setBackground(Color.PINK);
        errorLabel.setOpaque(true);
        errorLabel.setVisible(false);

        checkboxEqual.setSelected(true);
        addDoneButtonActionListener();
        addBackButtonActionListener();

        this.setBackground(Color.WHITE);

    }

    private void addBackButtonActionListener() {
        this.backButton.addActionListener(listener -> {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("homeScreen");
            viewFrame.update_homescreen();
        });
    }

    public void addDoneButtonActionListener() // add listener for Done button
    {
        this.doneButton.addActionListener(listener ->
        {
            boolean error = false;
            double totalAmountCheck= 0;
            if (Objects.equals(textBoxToEnterName.getText(), "")){
                errorLabel.setText("Naam cannot be empty");
                errorLabel.setVisible(true);
                error = true;
            }
            else if (Objects.equals(priceField.getText(), "")){
                errorLabel.setText("Bedrag mag niet leeg zijn");
                errorLabel.setVisible(true);
                error = true;
            }
            else if(!((priceField.getText().matches("[0-9]+(.|,)?[0-9]+")) | priceField.getText().matches("[0-9]+"))){
                errorLabel.setText("Bedrag moet een juist bedrag zijn");
                errorLabel.setVisible(true);
                error = true;
            }
            else{
                String name = textBoxToEnterName.getText();
                TicketFactory ticketFactory = new TicketFactory();
                Ticket newticket = ticketFactory.getTicket((TicketType) (Objects.requireNonNull(dropdownType.getSelectedItem())), name);
                payer = (Person) dropdownPersons.getSelectedItem();
                double totalAmount = -1000.0;
                try {
                    String priceString = priceField.getText();
                    priceString = priceString.replace(',', '.');
                    totalAmount = Double.parseDouble(priceString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (HashMap<JCheckBox, HashMap<UUID, Double>> hmjcb : checklist) {
                    JCheckBox key = hmjcb.keySet().iterator().next();
                    if (key.isSelected()) {
                        UUID key2 = hmjcb.get(key).keySet().iterator().next();
                        Person p = PersonController.getInstance().getPerson(key2);
                        if (!checkboxEqual.isSelected()) {
                            if(!((toPayList.get(key2).getText().matches("[0-9]+(.|,)?[0-9]+")) | toPayList.get(key2).getText().matches("[0-9]+"))){
                                errorLabel.setText("te betalen veld moet een juist bedrag zijn");
                                errorLabel.setVisible(true);
                                error = true;
                                System.out.println("error te betalen veld moet een juist bedrag zijn");
                                System.out.println("bij: " + p.getName());
                                break;
                            }
                            else {
                                double price = Double.parseDouble(toPayList.get(key2).getText().replace(',', '.'));
                                totalAmountCheck += price;
                                System.out.println(totalAmountCheck);
                                System.out.println(p + " moet " + price + " betalen");
                                newticket.addOws(p.getId(), price);
                            }
                        }
                        else{
                            newticket.addOws(p.getId());
                        }
                    }
                }


                if (totalAmountCheck != totalAmount && !checkboxEqual.isSelected() && !error){
                    System.out.println(totalAmount);
                    System.out.println(totalAmountCheck);
                    errorLabel.setText("Het totaal bedrag is niet juist");
                    errorLabel.setVisible(true);
                    error = true;
                    System.out.println("error :Het totaal bedrag komt niet overeen met de deelbedragen");
                }
                

                if (!error) {
                    System.out.println("geen error");
                    newticket.setPaid_amount(totalAmount);
                    newticket.setPayerid(payer.getId());
                    TicketController.getInstance().addTicket(newticket);
                    if (checkboxEqual.isSelected()) {
                        newticket.splitEqual();
                    }
                    ViewFrame viewFrame = ViewFrame.getInstance();
                    viewFrame.showScreen("homeScreen");
                    viewFrame.update_homescreen();
                }
            }
        });
    }

    public void updateMode(){
        if(ColorScheme.getInstance().getMode() == Scheme.Dark){
            nameLabel.setForeground(Color.WHITE);
            typeLabel.setForeground(Color.WHITE);
            payerLabel.setForeground(Color.WHITE);
            toPayLabel.setForeground(Color.WHITE);
        }
        else{
            nameLabel.setForeground(Color.BLACK);
            typeLabel.setForeground(Color.BLACK);
            payerLabel.setForeground(Color.BLACK);
            toPayLabel.setForeground(Color.BLACK);
            }
    }


    // add checboxEqualListener
    public void setcheckboxEqualListener() {
        checkboxEqualListener = e -> {
            for (HashMap<JCheckBox, HashMap<UUID, Double>> hmjcb : checklist) {
                JCheckBox checkboxPerson = hmjcb.keySet().iterator().next();
                UUID pId = hmjcb.get(checkboxPerson).keySet().iterator().next();
                if (!checkboxEqual.isSelected()) {
                    toPayList.get(pId).setVisible(checkboxPerson.isSelected());
                } else {
                    toPayList.get(pId).setVisible(false);
                }
            }
            this.revalidate();
            this.repaint();
        };
    }

    public void setcheckboxPersonListener() {
        checkboxPersonListener = e -> {
            for (HashMap<JCheckBox, HashMap<UUID, Double>> hmjcb : checklist) {
                JCheckBox checkboxPerson = hmjcb.keySet().iterator().next();
                UUID pId = hmjcb.get(checkboxPerson).keySet().iterator().next();
                if (!checkboxEqual.isSelected()) {
                    toPayList.get(pId).setVisible(checkboxPerson.isSelected());
                } else {
                    toPayList.get(pId).setVisible(false);
                }
            }
            this.revalidate();
            this.repaint();
        };
    }
}
