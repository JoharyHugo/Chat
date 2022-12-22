package affichage;

import java.awt.Color;
import selector.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientEnter extends JFrame {
    public ClientEnter() {
        JPanel panel = new JPanel();
        this.setTitle("Nom");
        panel.setBackground(Color.cyan);
        panel.setLayout(null);
        this.setLayout(null);
        JLabel label = new JLabel("Votre Nom");
        label.setBounds(50, 5, 100, 50);
        JTextField text = new JTextField();
        text.setBounds(50, 40, 400, 50);
        JButton button = new JButton("Ok");
        button.setBounds(500, 40, 200, 50);
        panel.setSize(800, 150);
        this.setSize(panel.getSize());
        panel.add(label);
        panel.add(text);
        panel.add(button);
        button.addMouseListener(new ListenerName(text));
     //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
        this.setContentPane(panel);
    }
}
