import java.io.*;
import java.net.*;
import java.util.*;

public class Node
{
	Sender sender = new Sender;
	Receiver receiver = new Receiver;
	ArrayList<Node> nodeList = new ArrayList<Node>;

    public static void main(String[] args) throws IOException
    {
    	if (args.length != 2) {
            System.err.println(
                "Usage: java Node <host name> <port number> <your name>");
            System.exit(1);
        }
    
    	String hostName = args[0];
    	int portNumber = Integer.parseInt(args[1]);
    	String name = args[2];    
    }

}
