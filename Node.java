import java.io.*;
import java.net.*;
import java.util.*;

public class Node
{
	public Sender sender;
	public Receiver receiver;
	public ArrayList<Node> connections;
	public String hostName;
	public int portNumber;
	public String name;

    public static void main(String[] args) throws IOException
    {


		//instantiate receiver / sender.
		// while loop that takes in input and pumps to sender

    	// not the first node
    	if (args.length == 3)
    	{
			Node chatNode = new Node(args[0], Integer.parseInt(args[1]), args[2]);

			// construct and send join message
			JoinMessage msg = new JoinMessage(chatNode);

System.out.println(chatNode.hostName);
System.out.println(chatNode.portNumber);
System.out.println(chatNode.name);

System.out.println("before chatNode.sendToConnections");

System.out.println(msg + "--------------------  the message contains -------------");

			chatNode.sendToConnections(msg);



System.out.println("after sendToConnectionss");

        }

        // first node
        else if ( args.length == 2)
        {
			Node chatNode = new Node(args[0], 4000, args[1]);


			System.out.println("Concurrency");
        }

        else
        {
            System.err.println(
                "Usage: (first node in network): \njava Node <host name> [<port name>] <your name>\n");
            System.exit(1);
        }

    }

	public Node(String ip, int port, String name)
	{
		this.hostName = ip;
		this.portNumber = port;
		this.name = name;
	  (new Thread(new Receiver( this ))).start();
//			addToList(this);

	}

	/*
		When this node joins the network, updateList()
		clones the current list of nodes from another node
	*/
	public void cloneList( ArrayList<Node> chatList )
	{
		connections = new ArrayList<Node>();
		for( Node item : chatList ) connections.add( item );
	}

	// first thing to run
	// calls Sender
	// public void join();

    public int getPort()
    {
    	return portNumber;
    }

	public String getIp()
	{
		return hostName;
	}

    public String getName()
    {
    	return name;
    }

	public ArrayList<Node> getConnections()
	{
		return connections;
	}

	public Receiver getReceiver()
	{
		return receiver;
	}

	public void addToList(Node nodeToAdd)
	{
		if (!connections.contains(nodeToAdd))
		{
			connections.add(nodeToAdd);
		}
	}

	public void removeFromList(Node nodeToRemove)
	{
		connections.remove(nodeToRemove);
	}

	public void sendToConnections(Message msg)
	{
		for (Node receivingNode: connections)
		{
			sender = new Sender(this, receivingNode, msg);
		}
	}

}
