import java.io.*;
import java.net.*;
import java.util.*;

public class Sender implements MessageTypes
{
	ObjectOutputStream outputStream;
	ObjectInputStream inputStream;
	Node parentNode;
	Node receivingNode;
	Socket socket;
	InputStream io;
	OutputStream os;

	public Sender(Node parentNode, Node receivingNode, Message msg)
	{
		try
		{
			this.socket = new Socket(receivingNode.getIp(), receivingNode.getPort());
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

			// if we are sending a join message, we await a response of new list
			if (msg.getType() == JOIN)
			{
				// send join message
				outputStream.writeObject(msg);

				// wait for response with new list
				ArrayList<Node> connections = ((Node) inputStream.readObject()).getConnections();

				//update parent list with new list.
				parentNode.cloneList(connections);

			}
			else
			{
				outputStream.writeObject(msg);
			}

		}
		catch (IOException | ClassNotFoundException e)
		{
			System.err.println(e);
		}

	}
}
