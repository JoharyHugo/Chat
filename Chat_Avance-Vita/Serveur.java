package serv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import client.*;
public class Serveur {
    ServerSocket server;

    public ServerSocket getServer() {
        return this.server;
    }

    public void setServer(ServerSocket s) {
        this.server = s;
    }

    public Serveur(ServerSocket s) {
        this.setServer(s);
    }

    public void startserver() {
        try {
            while (!server.isClosed()) {
                Socket s = this.getServer().accept();
                System.out.println("A new Client");
                ClientHandler c = new ClientHandler(s);
                Thread thread = new Thread(c);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeServer() {
        try {
            if (this.getServer() != null) {

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}