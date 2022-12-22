package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String Username;
    // static String msgFromGroupChat;

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    /*
     * public static String getMsgFromGroupChat() {
     * return msgFromGroupChat;
     * }
     */

    public Client(Socket s, String user) {
        try {
            this.socket = s;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.Username = user;
        } catch (IOException e) {
            e.printStackTrace();
            // closeEverything(this.getSocket(), bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage(String msg) {
        try {
            // System.out.println(msg);
            this.bufferedWriter.write(this.Username);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();

            if (this.getSocket().isConnected()) {
                // System.out.println("tonga");
                this.bufferedWriter.write(this.Username + ": " + msg);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // closeEverything(this.getSocket(), bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage(JTextArea l) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        System.out.println("ok");
                        msgFromGroupChat = bufferedReader.readLine();
                        l.append(msgFromGroupChat);
                        // l.repaint();
                        System.out.println(msgFromGroupChat);
                    } catch (IOException e) {
                        e.printStackTrace();
                        // closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
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

    public static void main(String[] args) throws IOException {
        /*
         * Scanner scanner = new Scanner(System.in);
         * System.out.println("Enter Your Username ");
         * String name = scanner.nextLine();
         * Socket s = new Socket("localhost", 1234);
         * Client client = new Client(s, name);
         * //client.listenForMessage();
         */
        // client.sendMessage();
    }
}
