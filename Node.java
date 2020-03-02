import java.io.*;
import java.net.*;
import java.util.*;

public class Node implements Serializable
{
	public ArrayList<Node> connections;
	public String hostName;
	public int portNumber;
	public String name;
	public boolean leftChat;

	private static final long serialVersionUID = 9185401210109757711L;

    public static void main(String[] args) throws IOException
    {
    	// not the first node
    	if (args.length == 3)
    	{
			// construct new node
			Node chatNode = new Node(args[0], Integer.parseInt(args[1]), args[2]);

			// construct join message
			JoinMessage msg = new JoinMessage(chatNode);

			// start sender thread with join message
			chatNode.startSenderThread(Integer.parseInt(args[1]), args[0], msg, false);

        }

        // first node
        else if ( args.length == 2)
        {
			// construct new node
			Node chatNode = new Node(args[0], 3999, args[1]);

			// add yourself to list.
			chatNode.addToList(chatNode);

			// construct join message
			JoinMessage msg = new JoinMessage(chatNode);

			// start sender thread with join message
			chatNode.startSenderThread(4000, args[0], msg, true);
        }

        else
        {
            System.err.println(
                "Usage: (first node in network): \njava Node <host name> [<port name>] <your name>\n");
            System.exit(1);
        }

    	}

		// Node constructor method.
		// Initializes member variables, starts receiver thread
		public Node(String ip, int existingPort, String name)
		{
			this.hostName = ip;
			this.portNumber = existingPort + 1;
			this.name = name;
			this.connections = new ArrayList<Node>();
			this.leftChat = false;
		  new Thread(new Receiver( this )).start();

		}



		// clones the current list of nodes from another node
		// used to obtain nodelist upon sending join message
		public void cloneList( ArrayList<Node> chatList )
		{
			connections = new ArrayList<Node>();
			for( Node item : chatList ) connections.add( item );
		}


		// returns portNumber
    public int getPort()
    {
    	return portNumber;
    }

		// returns hostName
		public String getIp()
		{
			return hostName;
		}

	// returns node name
    public String getName()
    {
    	return name;
    }

	// returns connected nodes list
	public ArrayList<Node> getConnections()
	{
		return connections;
	}

	// adds a new node to the list if it isn't already in there
	// called upon receiving join / joined message
	public void addToList(Node nodeToAdd)
	{

		if (!nodeInList(nodeToAdd))
		{
			connections.add(nodeToAdd);
		}
	}

	// removes a node from the connections list
	// called upon receiving leave message
	public void removeFromList(Node nodeToRemove)
	{
		int index = 0;
		int removeIndex = 0;
		for( Node item : connections )
		{
			if (item.isEqual(nodeToRemove))
			{
				removeIndex = index;
			}
			index ++;
		}
		connections.remove(removeIndex);
	}

	// compares two nodes port numbers / ip to determine if they are equal
	public boolean isEqual(Node node)
	{
		return (getPort() == node.getPort());
	}

	// starts the sender thread
	public void startSenderThread(int port, String host, Message msg, boolean firstNode)
	{
		new Thread(new Sender(this, port, host, msg, firstNode)).start();
	}

	// returns true if 'node' is in connections list
	public boolean nodeInList(Node node)
	{
		for( Node item : connections )
		{
			if (item.isEqual(node))
			{
				return true;
			}
		}
		return false;
	}

	// clears connections list. called when a node sends a leave message
	public void clearConnections()
	{
		connections = new ArrayList<Node>();
		leftChat = true;
	}

	// returns true if the node is participating in a chat.
	// false if the node has left the chat.
	public boolean isInChat()
	{
		return !leftChat;
	}



}
