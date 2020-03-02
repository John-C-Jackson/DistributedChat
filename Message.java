import java.io.Serializable;


public class Message implements MessageTypes, Serializable
{
    // initialize variables
    private static final long serialVersionUID = 9185401210109757710L;
    int type;
    String msgText;
    Node sender;

    // assign sender 
    public Message(Node sender)
    {
  	  this.sender = sender;
    }

    // gets the type of message
    public int getType ()
      {
        return type;

      }
    // constructs a message for sending out
    public String constructMessage ()
    {
  	String message = sender.getName() + ": " + msgText;
      return message;
    }

    // sets the content of the message
    public void setText (String content)
    {
      this.msgText = content;
    }

    // finds the type of message and sends corresponding string
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
