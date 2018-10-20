import java.io.*;
import java.net.*;
import java.util.*;
public class SimulationUsers
{
	final static int MAX_ITERATIONS=300;
	public static void main(String args[])
	{
		
		int users=Integer.parseInt(args[0]);
		while(true)
		for(int i=1;i<=users;i++)
			new Client().start(""+i+"");
		/*ArrayList<Socket> socket=new ArrayList<Socket>();
		int port=6868;
		for(int i=0;i<users;i++)
		{
			try
			{
				socket.add(new Socket("localhost",port));
			}
			catch (UnknownHostException ex) {

		        System.out.println("Server not found: " + ex.getMessage());

		    } catch (IOException ex) {

		        System.out.println("I/O error: " + ex.getMessage());
		    }
			
		}
		for(int i=0;i<users*MAX_ITERATIONS;i++)
		{
			int user=(int)(Math.random()*users);
			new Client(socket.get(user)).start(""+user);
		}*/
	}
}