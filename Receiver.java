import java.io.*;
import java.net.*;
import java.util.*;

public class Receiver
{
  int inImportNumber;
  String message;

  /*
  somewhere here do we unload the message to send as a string?
  and then send it to ReceiverWorker ??
  or do we send ReceiverWorker a object and then ReceiverWorker unpacks? 
  **/

  Runnable runHolder = new ReceiverWorker(inImportNumber, message);
  Thread threadHolder = new Thread(runHolder);
  threadHolder.start();
}
