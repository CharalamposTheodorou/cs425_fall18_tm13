import java.io.*;
import java.net.*;

/**
 * This thread is responsible to handle client connection.
 */
public class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			if (socket.isClosed())
				return;
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer= new PrintWriter(socket.getOutputStream(),true);

        String hello=reader.readLine();
        String port= reader.readLine();
        int userid=reader.readLine();
        /*System.out.print(hello+" user:"+userid+" at port:"+port);*/
        Random rmd=new Random(System.currentTimeMillis());
//random.nextInt= [0-300K]->+300K= [300K-2000K]
        int payload=random.nextInt((2000*1024-300*1024))+300*1024;

        writer.println("WELCOME"+userid);
        writer.println(payload);

			socket.close();

		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			 //TODO Auto-generated catch block
			 e.printStackTrace();
		}
		catch(InterruptedException ie)
		{
			ie.printStackTrace();
		}

	}
}
