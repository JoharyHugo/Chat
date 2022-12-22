package client;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String ClientUsername;

    public void setSocket(Socket s) {
        this.socket = s;
    }

    public void setClientUsername(String clientUsername) {
        this.ClientUsername = clientUsername;
    }

    public static ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public BufferedWriter getBufferedWriter() {
        return this.bufferedWriter;
    }

    public ClientHandler(Socket s) {
        try {
            this.setSocket(s);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.getSocket().getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
            this.setClientUsername(bufferedReader.readLine());
            this.clientHandlers.add(this);
            broadcastMessage("SERVER:" + this.ClientUsername + " est entre dans le chat!");
        } catch (IOException e) {
            closeEverything(this.getSocket(), bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        while (this.getSocket().isConnected()) {
            try {
                messageFromClient = this.bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(this.getSocket(), bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.ClientUsername.equals(ClientUsername)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();

                }
            } catch (IOException e) {
                closeEverything(this.getSocket(), bufferedReader, bufferedWriter);
            }

        }
    }

    public void removeClientHandler() {
        getClientHandlers().remove(this);
        broadcastMessage("SERVER: " + ClientUsername + "has left the chat!");
    }

    public void closeEverything(Socket socket, BufferedReader buffr, BufferedWriter bffw) {

        try {
            if (buffr != null) {
                buffr.close();
            }
            if (bffw != null) {
                bffw.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}