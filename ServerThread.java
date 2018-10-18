
import java.io.*;
import java.net.*;

/**
 * This thread is responsible to handle client connection.
 */
public class ServerThread extends Thread {
	private Socket socket;
	int requests = 0;
	final static int MAX_REQUESTS = 300;
	static int user_id;

	public ServerThread(Socket socket, int id) {
		this.socket = socket;
		this.user_id = id;
		this.run();
	}

	public void run() {
		try {
			// InputStream input = socket.getInputStream();
			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader(input));
			if (socket.isClosed())
				return;
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);

			Response re = new Response(user_id);
			this.requests = 0;
			// String text;

			do {

				System.out.println("Issuing Response Message to client with id" + this.user_id + " " + this.requests);
				this.requests++;
				re.payload = System.currentTimeMillis() - re.time_executed;
				writer.println(re.response + "<" + re.user_id + ">");
				writer.println(re.response);
				re.setResponse();
				// output stream to return response...
				/*
				 * text = reader.readLine(); String reverseText = new
				 * StringBuilder(text).reverse().toString(); writer.println(
				 * "Server: " + reverseText);
				 */

				// new while condition, variable to keep connection..
				// iterations(300).
			} while (this.requests <= MAX_REQUESTS);
			writer.close();
			output.close();
			socket.close();
			
		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
}
