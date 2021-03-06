import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

public class ConnectionHandler extends Thread {

    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final Socket s;
    final DataInputStream dis;
    final DataOutputStream dos;
    
    // Constructor
    public ConnectionHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() 
    {
        String received;
        String toreturn;
        while (true) 
        {
            try {

                // Ask user what he wants
                dos.writeUTF("What do you want?[Date | Time]..\n"+
                            "Type Exit to terminate connection.");
                            
                // receive the answer from client
                received = dis.readUTF();

                if(received.equals("Exit"))
                { 
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // creating Date object
                Date date = new Date();

                // write on output stream based on the
                // answer from the client
                switch (received) {

                    case "Date" :
                        toreturn = fordate.format(date);
                        dos.writeUTF(toreturn);
                        break;

                    case "Time" :
                        toreturn = fortime.format(date);
                        dos.writeUTF(toreturn);
                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            // closing resources
            this.dis.close();
            this.dos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}