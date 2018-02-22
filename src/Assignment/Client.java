package Assignment;

import java.io.*;
import java.net.*;
public class Client {
   public static void main(String args[]) {
      try {
         Socket skt = new Socket("127.0.0.1", 5000);
         System.out.println("Connected to server");

         InputStream is = skt.getInputStream();
         ObjectInputStream ois = new ObjectInputStream(is);
         OutputStream os = skt.getOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(os);

         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

         String rcv_msg = "", send_msg;
         while(!rcv_msg.equals("Over")) {
            /* Send a message to Server */
            System.out.print("To Server : ");
            send_msg = br.readLine();
            oos.writeObject(send_msg);

            /* Recieve a message from Server */
            System.out.println("Waiting for server to respond ... ");
            if((rcv_msg = (String)ois.readObject()) != null) {
               System.out.println("\nFrom Server : " + rcv_msg);
            }
         }
      } catch(Exception e) {
         System.out.println(e);
      }
   }
}