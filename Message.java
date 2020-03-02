import java.io.Serializable;


public class Message implements MessageTypes, Serializable
{
  private static final long serialVersionUID = 9185401210109757710L;
  int type;
  String msgText;
  Node sender;



  public Message(Node sender)
  {
	  this.sender = sender;
  }

  public int getType ()
    {
      return type;

    }

  public String constructMessage ()
  {
	String message = sender.name + ": " + msgText;
    return msgText;
  }


  public void setText (String content)
  {
    this.msgText = content;
  }

  public String typeToMsg(int type)
  {
	  String outString = "";
	  switch(type)
	  {
		  case JOIN:
		  	outString = "has requested to join the chat.";
			break;

		  case JOINED:
		   	outString = "has joined the chat.";
			break;

		  case LEAVE:
		  	outString = "has left the chat.";
	  }
	  return outString;
  }

  public Node getSendingNode()
  {
	  return sender;
  }

}
