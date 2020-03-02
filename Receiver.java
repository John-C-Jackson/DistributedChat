import java.io.*;
import java.net.*;
import java.util.*;

public class Receiver implements Runnable
{
	Node parentNode;
	ServerSocket serverSocket;

	// constructor sets the receiver's parent node
	public Receiver(Node parentNode)
	{
		this.parentNode = parentNode;

	}

	@Override
	public void run()
	{
		try
		{
			// create a server socket
			serverSocket = new ServerSocket(parentNode.getPort());
			System.out.println("Your port number is: " + parentNode.getPort() +
				". Other nodes can use this to join the chat.");

			boolean inChat = true;
			// while the server socket is active
		    while(inChat)
		    {
				// check for null server socket
				if(serverSocket == null)
				{
				    System.out.println("serverSocket null");
				}

		    	// creates a client socket that the server socket has connected to
		        Socket cSocket = serverSocket.accept();

		        // creates a new thread with the runHolder
		        Thread threadHolder = new Thread(new ReceiverWorker(cSocket,parentNode ));

		        // starts the thread
		        threadHolder.start();

				// update inChat variable
				inChat = parentNode.isInChat();
		    }
		}
		catch (IOException e)
		{
		  System.err.println(e);
		}
	}




  // public ServerSocket getServerSocket()
  // {
	//   return serverSocket;
  // }

}
