import java.io.*;
import java.net.*;

class Request implements Serializable{
  static final String Hello="HELLO";
  int user_id;
  int portNo;
  String IP;
  public Request(int id,int port,String newIP)
  {
    this.user_id=id;
    this.portNo=port;
    this.IP=newIP;
  }
  public void setPort(int port)
  {
    this.portNo=port;
  }
  public void setIP(String ip)
  {
    this.IP=ip;
  }
}
