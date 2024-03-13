package timer;

/**
 * @version 1.02 2017-12-14
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

public class TimerTest {
    public static void main(String[] args) {
        var listener = new TimePrinter();

        // construct a timer that calls the listener once every second
        var timer = new Timer(1000, listener);

        timer.start();

        // keep program running until the user selects "OK"
        JOptionPane.showMessageDialog(null, "Quite Program?");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        // TODO Auto-generated method stub
        System.out.println("At the tone, the time is " + Instant.ofEpochMilli(event.getWhen()));
        return;
    }
}