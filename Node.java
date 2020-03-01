import java.io.*;
import java.net.*;
import java.util.*;

public class Node
{
	Sender sender = new Sender();
	Receiver receiver = new Receiver();
	ArrayList<Node> nodeList;
	static String hostName;
	static int portNumber;
	static String name;

    public static void main(String[] args) throws IOException
    {
    	if (args.length != 3) {
            System.err.println(
                "Usage: java Node <host name> <port number> <your name>");
            System.exit(1);
        }
    
    	hostName = args[0];
    	portNumber = Integer.parseInt(args[1]);
    	name = args[2];    
    }
    
	/*
		When this node joins the network, updateList()
		clones the current list of nodes from another node
	*/
	public ArrayList<Node> cloneList( ArrayList<Node> chatList )
	{
		nodeList = new ArrayList<Node>(chatList.size());
		for( Node item : chatList ) nodeList.add( item );
	}
	
    public int getPort()
    {
    	return portNumber;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public ArrayList<Node> getNodeList()
    {
    	return nodeList;
    }

	public void addNode( Node joiningNode )
	{
		nodeList.add( joiningNode );
	}
	
	public void deleteNode( Node leavingNode )
	{
		nodeList.remove( leavingNode );
	}
	
}
