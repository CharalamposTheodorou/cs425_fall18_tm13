
import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * This program demonstrates a simple TCP/IP socket client that reads input
 * from the user and prints echoed message from the server.
 */
public class ReverseClient {
static int user_id;
    public static void main(String[] args) {
        //if (args.length < 2) return;

        String hostname = args[0];
        String ServerIP=args[1];
        int port = Integer.parseInt(args[2]);

        try (Socket socket = new Socket(hostname, port)) {

           // OutputStream output = socket.getOutputStream();
           // PrintWriter writer = new PrintWriter(output, true);

            Scanner scan = new Scanner(System.in);
            String text;

            //do {
            	/*System.out.print("Enter text: ");
                text = scan.nextLine();

                writer.println(text);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();

                System.out.println(time);

            } while (!text.equals("bye"));*/
          //  OutputStreamWriter out=new OutputStreamWriter(output);
         //   BufferedWriter bw=new BufferedWriter(out);
          //  InputStream input=socket.getInputStream();
          //  InputStreamReader inputR= new InputStreamReader(input);
          //  BufferedReader br=new BufferedReader(inputR);
          //  System.out.println(br.readLine());
            for(int i=0;i<300;i++)
            {
              Request request=new Request(user_id,port);
              request.setIP("192.168.10."+request.user_id);
             //bw.write(request.);
            //  bw.flush();
              //has to send and reveive objects...
            }
            scan.close();

            socket.close();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
