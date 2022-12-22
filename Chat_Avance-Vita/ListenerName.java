package selector;

import java.awt.*;
import java.awt.event.*;
import affichage.*;
import javax.swing.JTextField;

public class ListenerName implements MouseListener {
    JTextField textField;

    public ListenerName(JTextField f) {
        this.textField = f;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            new FenetreClient(this.textField.getText());
        } catch (Exception es) {
            es.printStackTrace();
        }
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
