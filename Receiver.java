import java.io.*;
import java.net.*;
import java.util.*;

public class Receiver implements Runnable
{
  int inImportNumber;
//  ReceiverWorker runHolder;
//  Thread threadHolder;
  Node parentNode;
  ServerSocket serverSocket;

  //
  public Receiver(Node parentNode){
	  this.parentNode = parentNode;

}

  @Override
  public void run()
  {
      try
        {
        serverSocket = new ServerSocket(parentNode.getPort());
System.out.println(parentNode.getPort());
        // does this loop need to be in a thread or something?
        // worried that the loop will stop the rest of the program from running
            // while the server socket is active
            while(true)
            {
// PRINT STATEMENT FOR INITIAL TESTING... REMOVE LATER
System.out.println("running...");
          
if(serverSocket == null)
{
    System.out.println("serverSocket null");
}
            // crreates a client socket that the server socket has connected to
                Socket cSocket = serverSocket.accept();
System.out.println("after accepting");

                // creates a new thread with the runHolder
                Thread threadHolder = new Thread(new ReceiverWorker(cSocket,parentNode ));
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
