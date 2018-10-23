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
    double retThrough=0;
    double retLatency=0;
    String userid;
    public Client(String userid)
    {
    	this.userid=userid;
    }
    public void run()
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
                writer.println(socket.getLocalAddress());
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
            String t=reader.readLine();
            System.out.println(t);
            double cpuload=Double.parseDouble(t);
            System.out.println(cpuload);
            double memory=Double.parseDouble(reader.readLine());
            
            SimulationUsers.countUsers++;
            SimulationUsers.memory+=memory;
            SimulationUsers.cpu+=cpuload;
            if(SimulationUsers.countUsers==SimulationUsers.maxUsers)
            {
    			double totalLatency = 0;
    			for (int i  =0; i<SimulationUsers.latencies.size()-1; i++){
    				totalLatency += SimulationUsers.latencies.get(i);
    			}
    			System.out.println("Total Latency: "+totalLatency+" nanoseconds");
    			System.out.println("Total Throughput: "+Math.pow(10,9)*sumThrough);

    			System.out.println("Average CpuLoad per User: "+SimulationUsers.cpu/SimulationUsers.maxUsers+"%");
    			System.out.println("Average Memory Utilization per User: "+SimulationUsers.memory/SimulationUsers.maxUsers+"%");
            }
            socket.close();
        }
        catch (UnknownHostException ex) {
            
            System.out.println("Server not found: " + ex.getMessage());
            
        } catch (IOException ex) {
            
            System.out.println("I/O error: " + ex.getMessage());
        }
        this.retLatency=sumLatency;
        this.retThrough=sumThrough;
        SimulationUsers.latencies.add(this.retLatency);
        SimulationUsers.Through=sumThrough;
    }
}

