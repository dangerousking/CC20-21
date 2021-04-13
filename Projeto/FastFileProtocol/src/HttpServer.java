import java.net.*;
import java.util.*;
import java.io.*;

public class HttpServer {
    public static void main(String args[]) throws IOException, ClassNotFoundException   
    {
        ServerSocket ss = new ServerSocket(8080);
        List<Integer> listaSockets = new ArrayList<Integer>();

        
        while(true){
            Socket socket = null;

            try {

                System.out.println("ServerSocket awaiting connections...");
                socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.    
                System.out.println("Connection from " + socket + "!");


                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ConnectionHandler(socket, dis, dos);
                t.start();

                listaSockets.add(socket.getPort());
                System.out.println("Tabela: ");

                for(Integer i:listaSockets){
                    System.out.println(i);
                }

                //TEST
                Socket sTest = new Socket("localhost", listaSockets.get(0));
                //DataInputStream disTest = new DataInputStream(sTest.getInputStream());
                //DataOutputStream dosTest = new DataOutputStream(sTest.getOutputStream());

                //dosTest.writeUTF("TEST!!!\n");


                //System.out.println("Closing sockets.");
                //ss.close();
                //socket.close();

            }//end of try
            catch (Exception e){
                socket.close();
                e.printStackTrace();
            }
        }
    }
}