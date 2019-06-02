import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Client {
   private Socket socket = null;
   static PrintStream out = null;
   static Scanner in = null;
   static int id;
   public static void main(String[] args) {
      new Client("127.0.0.1", 8080);
      new Window();
   }

   Client(String host, int porta) {
      try {
         this.socket = new Socket(host, porta);
         out = new PrintStream(socket.getOutputStream(), true);  //para enviar ao servidor
         in = new Scanner(socket.getInputStream()); //para receber do servidor

         //PRIMEIRA LINHA QUE O O SERVIDOR envia é para informar o ID DO PLAYER
         id = Integer.parseInt(in.nextLine());
         System.out.println("Bem vindo! Seu personagem é o " + Const.personColors[Client.id]);

         new Receiver().start();
      } catch (UnknownHostException e) {
         System.out.println("UnknownHostException: " + e);
      } catch (IOException e) {
         System.out.println("IOException: " + e);
      }
   }
}

class Window extends JFrame {
   Window() {
      add(new Panel());
      setTitle("Bomber-Man");
      pack();
      setVisible(true);
      // setLocationRelativeTo(null);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      addKeyListener(new Sender());
   }
}