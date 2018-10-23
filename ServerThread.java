import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.*;
import java.util.*;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
/**
 * This thread is responsible to handle client connection.
 */
public class ServerThread extends Thread {
    private Socket socket;
    public double throughput;
    public double cpuUtilization;
	final static int MAX_ITERATIONS=300;
	public ServerThread(Socket socket) {
	        this.socket = socket;
    }
    
    public double getThroughput() {
        return this.throughput;
    }
    
    public double returnCPU(){
    	return this.cpuUtilization;
    }
    
    public static double getMemoryUtilization()
    {
    	long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		double persantage = (100.0 * afterUsedMem) / (Runtime.getRuntime().totalMemory() * 1.0);
		return ((int) (persantage * 10) / 10.0);
    }
    public static double getCPU() throws Exception
    {
    	MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
		AttributeList list = mbs.getAttributes(name, new String[] { "ProcessCpuLoad" });

		if (list.isEmpty())
			return Double.NaN;

		Attribute att = (Attribute) list.get(0);
		Double value = (Double) att.getValue();

		if (value == -1.0)
			return Double.NaN;
		return ((int) (value * 1000) / 10.0);
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
            double cpuUtilization=getMemoryUtilization();

            
            try {
            	double cpu=getCPU();
				writer.println(cpu+"");
				System.out.println(cpu);
				writer.println(cpuUtilization+"");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("CPU Exception");
				e.printStackTrace();
			}
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }  
    }
}
