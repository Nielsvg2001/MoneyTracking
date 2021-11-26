package be.uantwerpen.fti.GUI;

import javax.swing.*;
import java.awt.*;

public class addTicketScreen extends JPanel {
    JButton doneButton = new JButton("Done");

    public addTicketScreen(){
        this.add(doneButton);
        this.setBackground(Color.pink);
        addDoneButtonActionListener();
    }

    public void addDoneButtonActionListener()
    {
        this.doneButton.addActionListener(listener ->
        {
            ViewFrame viewFrame = ViewFrame.getInstance();
            viewFrame.showScreen("homeScreen");
        });
    }
}
