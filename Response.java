package reverse_server_multi_threaded;

import java.io.*;
import java.net.*;

/**
 * This thread is responsible to handle client connection.
 */
public class Response{
  double time_executed;
  static final String response="WELCOME";
  int user_id;
  double payload;//calculated at ServerThread class (CurrentSystemTime-time_executed)
  public Response(int id)
  {
    this.user_id=id;
    this.time_executed=System.currentTimeMillis();
  }
  public void setResponse()
  {
    this.time_executed=System.currentTimeMillis();
  }

}
