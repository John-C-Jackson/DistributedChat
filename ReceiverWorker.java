import java.io.*;
import java.net.*;
import java.util.*;


public class ReceiverWorker implements MessageTypes, Runnable
{
  int inImportNumber;
  Message message;
  Socket clientSocket;
  ObjectInputStream inputStream;
  ObjectOutputStream outputStream;
  InputStream io;
  OutputStream os;
  Node parentNode;

  ReceiverWorker(Socket clientSocket, Node parentNode)
  {
    this.clientSocket = clientSocket;
	  this.parentNode = parentNode;
  }

    @Override
    public void run ()
    {

		try
        {
          // assigns io to the input of client
          io = clientSocket.getInputStream();
          // assigns os to the output to server
          os = clientSocket.getOutputStream();
        }
        catch (IOException e) {System.err.println(e);}

		// open a data input stream from the client.
        try
        {
    			inputStream = new ObjectInputStream(io);
    			// create a data output stream to the client
          outputStream = new ObjectOutputStream(os);

    			// read input and convert it to a message
    			this.message = (Message) inputStream.readObject();

    			// get sender of the message
    			Node sendingNode = message.getSendingNode();

          // get the type of message
    			int msgType = message.getType();

          // checks the type
    			switch (msgType)
    			{
    				case JOIN:
    				  // update list with new node
    				  parentNode.addToList(sendingNode);
              
    				  // send parent node, so the its list can be obtained
    				  outputStream.writeObject(parentNode);
    				  break;

    				case JOINED:
    				  // update list with new node
    				  parentNode.addToList(sendingNode);
    				  // display joined message
    				  System.out.println(message.constructMessage());
    				  break;

    				case NOTE:
    				  // display note message
    				  System.out.println(message.constructMessage());
    				  break;

    				case LEAVE:
    				  // remove sending node from parent node list
    				  parentNode.removeFromList(sendingNode);
    				  // display leave message
    				  System.out.println(message.constructMessage());
    				  break;
			}
			// close the socket
	      	clientSocket.close();

        }
		catch (IOException | ClassNotFoundException e)
		{
			System.err.println(e);
		}
    }
}
