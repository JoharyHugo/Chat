package selector;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Client;

public class ListenerEnvoie implements MouseListener {
    Client client;
    JTextField textField;
    JTextArea r;

    public ListenerEnvoie(Client c, JTextField t, JTextArea rd) {
        this.client = c;
        this.textField = t;
        this.r = rd;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //System.out.println(this.textField.getText());
        this.client.sendMessage(this.textField.getText());
        //this.r.repaint();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
