import domain.KafkaResponseMessage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args){
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.err.println("Logs from your program will appear here!");

     ServerSocket serverSocket = null;
     Socket clientSocket = null;
     int port = 9092;
     try {
       serverSocket = new ServerSocket(port);
       // Since the tester restarts your program quite often, setting SO_REUSEADDR
       // ensures that we don't run into 'Address already in use' errors
       serverSocket.setReuseAddress(true);
       // This remains idle listening for any incoming requests into the socket
       // This is a blocking operation/method
       clientSocket = serverSocket.accept();

       System.out.println("Client connected!");

       BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());
       KafkaResponseMessage message = new KafkaResponseMessage(0, 7);

       BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());

       out.write(message.message_size());
       out.write(message.correlation_id());
       out.flush();

     } catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
     } finally {
       try {
         if (clientSocket != null) {
           clientSocket.close();
         }
       } catch (IOException e) {
         System.out.println("IOException: " + e.getMessage());
       }
     }
  }
}
