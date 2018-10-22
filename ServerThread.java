import java.io.*;
import java.net.*;
import java.util.*;
/**
 * This thread is responsible to handle client connection.
 */
public class ServerThread extends Thread {
    private Socket socket;
    public double throughput;
	final static int MAX_ITERATIONS=300;
	public ServerThread(Socket socket) {
	        this.socket = socket;
    }
    
    public double getThroughput() {
        return this.throughput;
    }

	public void run() {
        double throughput = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer= new PrintWriter(socket.getOutputStream(),true);
            String text;
            int requestCounter=0;
            double startTime=System.nanoTime();
            for(int i=0;i<MAX_ITERATIONS;i++)
            {
                String hello=reader.readLine();
                String address= reader.readLine();
                String port=reader.readLine();
                String userid = reader.readLine();
                String id=reader.readLine();
               System.out.println(hello+" user:"+id+" at address:"+address+" on port:"+port);
                Random rmd=new Random(System.currentTimeMillis());
                //random.nextInt= [0-300K]->+300K= [300K-2000K]
                int payload=(rmd.nextInt((2000*1024-300*1024))+300*1024)/1024;

                writer.println("WELCOME user:"+id);
                writer.println(payload);
                requestCounter++;
            }
            double estimatedTime=System.nanoTime()-startTime;
            throughput=(double)(requestCounter/estimatedTime);

            this.throughput = throughput;
            double through=this.throughput/estimatedTime;
            writer.println(through+"");
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }  
    }
}
