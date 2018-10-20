import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * This program demonstrates a simple TCP/IP socket client that reads input
 * from the user and prints echoed message from the server.
 */
public class Client extends Thread {
    final static String hostname="localhost";
    public  final static int port=6868;
    final static int MAX_ITERATIONS=300;
    //public static void main(String args[])
    public double start(String userid)
    {
        
        double sumLatency=0;
        try(Socket socket=new Socket(hostname,port))
        {
            double latency=0;

            PrintWriter writer= new PrintWriter(socket.getOutputStream(),true);
            for(int i=0;i<MAX_ITERATIONS;i++)
            {
                BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                latency=System.nanoTime();
                writer.println("HELLO:\n");
                writer.println(socket.getLocalAddress());//"\nPort:"+socket.getLocalPort());
                writer.println(socket.getLocalPort());
                writer.println(userid);
                String welcome=reader.readLine();
                String payload=reader.readLine();
                latency=System.nanoTime()-latency;
                System.out.println("User("+userid+"), request: "+i);
                System.out.println(welcome);
                System.out.println("payload:" +payload);
                sumLatency+=latency;
            } 
            socket.close();
        }
        catch (UnknownHostException ex) {
            
            System.out.println("Server not found: " + ex.getMessage());
            
        } catch (IOException ex) {
            
            System.out.println("I/O error: " + ex.getMessage());
        }
        return sumLatency;
    }
}

