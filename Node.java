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
    	// not the first node
    	if (args.length == 3)
    	{
    		hostName = args[0];
    		portNumber = Integer.parseInt(args[1]);
    		name = args[2];  
        } 
        
        // first node
        else if ( args.length == 2) 
        {
        	hostName = args[0];
        	name = args[1];
        	portNumber = 4000;
        }
        
        else
        {
            System.err.println(
                "Usage: (first node in network): \njava Node <host name> [<port name>] <your name>\n");
            System.exit(1);
        }
      
    }
    
	/*
		When this node joins the network, updateList()
		clones the current list of nodes from another node
	*/
	public ArrayList<Node> cloneList( ArrayList<Node> chatList )
	{
		nodeList = new ArrayList<Node>();
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
