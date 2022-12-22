package other;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.*;
import client.Client;

public class Exterieur extends Thread {
    JPanel p;
    JTextArea t;
    Client client;

    public Exterieur(JPanel panel, JTextArea area, Client c) {
        this.p = panel;
        this.t = area;
        this.client = c;
    }

    @Override
    public void run() {
      //  this.client.listenForMessage();
       // this.t.repaint();
       String msgFromGroupChat = "";
        while (true) {
            try {
              
              
                msgFromGroupChat =this. client.getBufferedReader().readLine();
                System.out.println(msgFromGroupChat);
                this.t.append(msgFromGroupChat + " " + "\n");
               this.t.repaint();
            } catch (IOException e) {
                e.printStackTrace();
             //   client.closeEverything(client.getSocket(), client.getBufferedReader(), client.getBufferedWriter());
            }
        }

        // this.t.append("\n");

        //this.p.repaint();
    }
}
