package affichage;

import java.awt.Color;
import other.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Client;

import java.net.Socket;
import selector.*;

public class FenetreClient extends JFrame {
    String Nom;
    JPanel conteneur = new JPanel();

    public String getNom() {
        return this.Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public JPanel getConteneur() {
        return this.conteneur;
    }

    public FenetreClient(String rs) throws Exception {

        this.Nom = rs;
        System.out.println(this.Nom);
        conteneur.setBackground(Color.CYAN);
        conteneur.setSize(667, 515);
        JLabel label = new JLabel(this.Nom);
        label.setBounds(50, 30, 100, 16);
        JTextArea message = new JTextArea();
        message.setBounds(55, 100, 550, 294);
        conteneur.setLayout(null);
       
       
        JButton button = new JButton("Envoyer");
        button.setBounds(505, 420, 100, 50);
        Socket s = new Socket("localhost", 1234);
        Client client = new Client(s, rs);
        JTextField write = new JTextField();
        write.setBounds(55, 420, 400, 50);
        Exterieur exterieur = new Exterieur(conteneur, message, client);
        Thread thread = new Thread(exterieur);
        thread.start();       
        conteneur.add(label);
        conteneur.add(message);
        conteneur.add(write);
        conteneur.add(button);
        button.addMouseListener(new ListenerEnvoie(client, write, message));
      //  client.listenForMessage(message);
        this.setSize(667, 515);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
        this.setContentPane(conteneur);
       
    }

}
