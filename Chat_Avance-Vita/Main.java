package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


//import affichage.FenetreClient;
import serv.Serveur;

public class Main {
    public static void main(String[] args) throws Exception, IOException {
        ServerSocket server = new ServerSocket(1234);
        Serveur ser = new Serveur(server);
        ser.startserver();

    }
}
