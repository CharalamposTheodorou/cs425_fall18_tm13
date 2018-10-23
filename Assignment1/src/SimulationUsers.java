import java.io.*;
import java.net.*;
import java.util.*;

public class SimulationUsers {
	final static int MAX_ITERATIONS = 300;
	public static ArrayList<Double> latencies = new ArrayList<Double>();
	public static double Through;
	public static int countUsers=0;
	public static int maxUsers;
	public static double memory=0;
	public static double cpu=0;
	public static void main(String args[]) {
		double sumThrough=0;
		
		Client client[];
		int users = Integer.parseInt(args[0]);
		maxUsers=users;
		for (int i = 1; i <= users; i++)
		{
			new Client(i+"").start();
		}
		double totalLatency = 0;
		for (int i  =0; i<latencies.size(); i++){
			totalLatency += latencies.get(i);
		}
		System.out.println("Total Latency: "+totalLatency+" nanoseconds");
		System.out.println("Total Throughput: "+Math.pow(10,9)*sumThrough);
	}
}