package reverse_server_multi_threaded;

import java.io.*;
import java.net.*;

/**
 * This program demonstrates a simple TCP/IP socket server that echoes every
 *
 * This server is multi-threaded.
 */
public class ReverseServer {

    public static void main(String[] args) {
        if (args.length < 1) return;

        int port = Integer.parseInt(args[0]);
        int random_users=12;//given
        try (ServerSocket serverSocket = new ServerSocket(port,random_users)) {

            System.out.println("Server is listening on port " + port);


            for(int i=0;i<10;i++)
            {
                Socket socket = serverSocket.accept();
                new ServerThread(socket,i).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
