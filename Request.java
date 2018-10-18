

public class Request{
  static final String Hello="HELLO";
  int user_id;
  int portNo;
  String IP;
  public Request(int id,int port)
  {
    this.user_id=id;
    this.portNo=port;
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
