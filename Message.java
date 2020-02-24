import java.io.Serializable;
//package messagetypes; 

public class Message implements MessageTypes, Serializable
{
  private static final long serialVersionUID = 9185401210109757710L;
  int type;
  Object content;

  public int message(int type)
  {
    this.type= type;
  }

  public int getType ()
    {
      return type;

    }

  public Object getContent ()
  {
    return content;
  }


  public void setContent (Object content)
  {
    this.content = content;
  }

}
