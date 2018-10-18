package reverse_server_multi_threaded;

import java.io.*;
import java.net.*;

/**
 * This thread is responsible to handle client connection.
 */
public class ServerThread extends Thread {
    private Socket socket;
    int requests=0;
    final static int MAX_REQUESTS=300;
    final static int user_id;
    public ServerThread(Socket socket,int i,int id) {
        this.socket = socket;
        this.user_id=id;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = new DataOutputStream(socket.getOutputStream());
            PrintWriter writer = new PrintWriter(output, true);

            Response re=new Response(user_id);
            this.requests=0;
            //String text;

            do
            {

              System.out.println("Issuing Response Message to client with id"+this.user_id);
              this.reuests++;
              re.payload=System.currentTimeMillis()-re.time_executed;
              writer.prinln(re.response+"<"+re.user_id+">");
              output.writeDouble(re.response);
              re.setResponse();
              //output stream to return response...
                /*text = reader.readLine();
                String reverseText = new StringBuilder(text).reverse().toString();
                writer.println("Server: " + reverseText);*/

//new while condition, variable to keep connection.. iterations(300).
            } while (this.requests<MAX_REQUESTS);

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
