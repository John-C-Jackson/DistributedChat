import java.io.*;
import java.net.*;
import java.util.*;

public class Receiver 
{
  int inImportNumber;
  ReceiverWorker runHolder;
  Thread threadHolder;
  Node parentNode;
  ServerSocket serverSocket;

  //
  public Receiver(Node parentNode){
	  this.parentNode = parentNode;
	  try
	    {
			serverSocket = new ServerSocket(parentNode.getPort());
			// does this loop need to be in a thread or something?
			// worried that the loop will stop the rest of the program from running
	        // while the server socket is active
	        while(true)
	        {
				// PRINT STATEMENT FOR INITIAL TESTING... REMOVE LATER
				System.out.println("running...");
	            // crreates a client socket that the server socket has connected to
	            Socket cSocket = serverSocket.accept();
	            // creates a run of the program with clientSocket (cSocket)
	            Runnable runHolder = new ReceiverWorker(cSocket, parentNode);
	            // creates a new thread with the runHolder
	            Thread threadHolder = new Thread(runHolder);
	            // starts the thread
	            threadHolder.start();
	        }
	    }
	catch (IOException e)
	{
		System.err.println(e);
	}


  }

  public ServerSocket getServerSocket()
  {
	  return serverSocket;
  }

}
