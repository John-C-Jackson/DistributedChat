import java.io.*;
import java.net.*;
import java.util.*;

public class Sender implements MessageTypes, Runnable
{
	ObjectOutputStream outputStream;
	ObjectInputStream inputStream;
	Node parentNode;
	int connectionPort;
	String connectionIp;
	Socket socket;
	InputStream io;
	OutputStream os;
	Message currentMsg;
	boolean firstNode;



	// Sender constructor method
	public Sender(Node parentNode, int port, String host, Message initialMsg, boolean firstNode)
	{
		this.connectionPort = port;
		this.connectionIp = host;
		this.currentMsg = initialMsg;
		this.parentNode = parentNode;
		this.firstNode = firstNode;
	}

	@Override
	public void run()
	{
		// if this isn't the first node, send out a join message.
		if (!firstNode)
		{
			sendMessage();
		}

		// initializes reader to take in user input for note messages.
		BufferedReader reader =
			   new BufferedReader(new InputStreamReader(System.in));
		String nodeInput = "";

		Boolean isInChat = true;

		// now start loop while we await user input for note messages
		while(isInChat)
		{
			try
			{
				// read in user input
				nodeInput = reader.readLine();
			}
			catch (IOException e)
			{
				System.err.println(e);
			}

			// if user typed "leave", construct a leave message.
			if (nodeInput.toLowerCase().equals("leave"))
			{
				currentMsg = new LeftMessage(parentNode);
			}

			// otherwise, user inputted a note message
			else
			{
				currentMsg = new NoteMessage(parentNode, nodeInput);
			}

			// loop through all of parent node's connections
			for (Node receivingNode: parentNode.getConnections())
			{
				// if the connection != parent node
				if (!parentNode.isEqual(receivingNode))
				{
					// set port and ip of receiving node
					this.connectionPort = receivingNode.getPort();
					this.connectionIp = receivingNode.getIp();
					// send message
					sendMessage();
				}
			}

			// if we sent a leave message, update loop variable to exit
			if (currentMsg.getType() == LEAVE)
			{
				isInChat = false;
				parentNode.clearConnections();
			}

		}
	}


	public void sendMessage()
	{
		// establish socket connection to receiving node
		try
		{
			this.socket = new Socket(connectionIp, connectionPort);
		}
		catch (IOException e) {System.err.println(e);}


		try
		{
		  // assigns io to the input of client
		  io = socket.getInputStream();
		  // assigns os to the output to server
		  os = socket.getOutputStream();
		}
		catch (IOException e) {System.err.println(e);}

		try
		{
			outputStream = new ObjectOutputStream(os);
			inputStream = new ObjectInputStream(io);

			if (currentMsg.getType() == JOIN)
			{
				// send out message
				outputStream.writeObject(currentMsg);

				// wait for response with new list
				ArrayList<Node> connections = ((Node) inputStream.readObject()).getConnections();

				//update parent list with new list.
				parentNode.cloneList(connections);

				// send out the JOINED message to all participating nodes.
				// letting them know a new node has joined the chat.
				currentMsg = new JoinedMessage(parentNode);
				for (Node receivingNode: parentNode.getConnections())
				{
					// only send if receiving != parent node
					if (!parentNode.isEqual(receivingNode))
					{
						// set up connection
						this.connectionPort = receivingNode.getPort();
						this.connectionIp = receivingNode.getIp();
						// send JOINED message
						sendMessage();
					}
				}
				// close client socket 
				socket.close();

			}
			// else if sending a leave message
			else if (currentMsg.getType() == LEAVE)
			{
				// send out message
				outputStream.writeObject(currentMsg);


			}
			// otherwise, sending a note message
			else
			{
				// send out message
				outputStream.writeObject(currentMsg);
			}
		}
		catch (IOException | ClassNotFoundException e)
		{
			System.err.println(e);
		}
	}


}
