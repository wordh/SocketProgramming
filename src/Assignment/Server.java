package Assignment;
import java.io.*;
import java.net.*;
public class Server {
   public static void main(String args[]) {
      try {
         // Create a new server socket that listens at port 5000
         ServerSocket ss = new ServerSocket(5000);
         System.out.println("Server is lisenting ... ");
         Socket skt = ss.accept();//accept a connection from client on port 5000

         OutputStream os = skt.getOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(os);
         InputStream is = skt.getInputStream();
         ObjectInputStream ois = new ObjectInputStream(is);

         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

         String rcv_msg = "", send_msg;
         while(!rcv_msg.equals("Over")) {
            /* Recieve a message from client */
            System.out.println("Waiting for client to respond ... ");
            if((rcv_msg = (String)ois.readObject()) != null) {
               System.out.println("From Client : " + rcv_msg);
            }

            /* Send a message to client */
            System.out.print("\nTo Client : ");
            send_msg = br.readLine();
            oos.writeObject(send_msg);
         }
      } catch(Exception e) {
         System.out.println(e);
      }
   }
}