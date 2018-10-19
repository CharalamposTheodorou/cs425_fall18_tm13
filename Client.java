import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * This program demonstrates a simple TCP/IP socket client that reads input
 * from the user and prints echoed message from the server.
 */
public class Client extends Thread {
   final String hostname="localhost";
  public  final int port="6868";
final int MAX_ITERATIONS=300;
 public void start(int userid)
  {
    try
    {
      Socket socket = new Scoket(hostname, port);
      double latency;
      for(int i=0;i<MAX_ITERATIONS;i++)
        {
          latency=System.currentTimeMillis();
          BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintWriter writer= new PrintWriter(socket.getOutputStream(),true);

          writer.print("HELLO:\n");
          writer.println(socket.getLocalAddress()+"\nPort:"+socket.getLocalPort());
          writer.println(userid);

          String welcome=reader.readLine();
          String payload=reader.readLine();
          latency-=System.currentTimeMillis();
          System.out.println("User("+userid+"), request: "+i+"\n");
        }
      /*
      System.out.println(welcome);
      System.out.println("payload:" +payload);
      */
      socket.close();
    }
    catch(InterruptedException ie)
    {
      System.out.println(ie.getMessage());
    }
    catch (UnknownHostException ex) {

        System.out.println("Server not found: " + ex.getMessage());

    } catch (IOException ex) {

        System.out.println("I/O error: " + ex.getMessage());
    }
  }
