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
    public double[] start(String userid,String maxUsers)
    {
        double ret[]=new double[2];
        double sumLatency=0;
        String through="";
        double sumThrough=0;
        try(Socket socket=new Socket(hostname,port))
        {
            double latency=0;

            PrintWriter writer= new PrintWriter(socket.getOutputStream(),true);
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            for(int i=0;i<MAX_ITERATIONS;i++)
            {
               
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
                System.out.println("payload:" +payload+"KB");
                sumLatency+=latency;
                
            } 
            through=reader.readLine();
            sumThrough=Double.parseDouble(through);
           // if(userid.compareTo(maxUsers)==0)
        	//	System.out.println("Total throughput: " + through+" nanoseconds");
            	
            socket.close();
        }
        catch (UnknownHostException ex) {
            
            System.out.println("Server not found: " + ex.getMessage());
            
        } catch (IOException ex) {
            
            System.out.println("I/O error: " + ex.getMessage());
        }
        ret[0]=sumLatency;
        ret[1]=sumThrough;
        return ret;
    }
}

