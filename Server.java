import java.io.*;
import java.net.*;

/**
 * This program demonstrates a simple TCP/IP socket server that echoes every
 *
 * This server is multi-threaded.
 */
public class Server  {
	public static final int port=6868;

	public static void main(String[] args) {
		//if (args.length < 1)
		//	return;

		//int port = Integer.parseInt(args[0]);
		int users=Integer.parseInt(args[0]);
		double throughput = 0;
		try (ServerSocket serverSocket = new ServerSocket(port)){//,users)) {

			System.out.println("Server is listening on port " + port);
			while(true)
			{
				Socket socket = serverSocket.accept();
				//maybe needs start();...
				ServerThread thread = new ServerThread(socket);
				thread.run();
				throughput += thread.getThroughput();
				System.out.println("Total throughput: " + throughput);
			}
		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
