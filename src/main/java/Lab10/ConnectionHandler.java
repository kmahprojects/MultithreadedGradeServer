package Lab10;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.io.PrintWriter;
/**
 * ConnectionHandler handles the connection between client and server
 * @author: Kevin Mah
 *  */
public class ConnectionHandler extends Thread implements Configuration{
    /**
     * variables used to control input and output of data
     *  */
    private Socket socket;
    private DataInputStream inFromClient;
    private DataOutputStream outToClient;


    /**
     * ConnectionHandler ties the data streams to a socket
     * @param socket: a socket
     * @return: nothing
     *  */
    public ConnectionHandler(Socket socket)  {
        this.socket = socket;
        try {
            inFromClient = new DataInputStream(socket.getInputStream());
            outToClient = new DataOutputStream(socket.getOutputStream());

        } catch (IOException exception) {
            System.out.printf("\t|Client was disconnected!\n");
        }
    }
    /**
     * run will run a method to connect the client to the server, and throw an exception if needed
     * @param: nothing
     * @return: nothing
     *  */
    @Override
    public void run()  {
        try {
            handleClientConnection();
        }
        catch (Exception ex) {
            System.out.printf("\t|Client was disconnected!\n");
            cleanup();
        }
    }
    /**
     * handleClientConnection takes inputs from the client and sends back responses to the client
     * @param: nothing
     * @return: nothing
     *  */
    public void handleClientConnection() {
        try{
            int grade = 0;
            while (grade != Configuration.QUIT){
                grade = inFromClient.readInt();
                if (grade == Configuration.QUIT) {
                    throw new ClientDisconnectedException ("\t|ConnectionHandler.handleClientRequest: Connection Reset \n\t|Cleaning up and exiting\n");
                }
                String clientGrade = Configuration.calculateGrade(grade);
                System.out.printf("\t|Message received from client <<%s>>\n", grade);
                this.outToClient.writeUTF(clientGrade);
                this.outToClient.flush();
            }


        }catch (ClientDisconnectedException cdex) {
            System.out.printf("\t|%s.\n", cdex.getMessage());
        }
        catch(IOException ioex) {
            System.out.printf("\t|ConnectionHandler.handleClientRequest: Connection Reset \n\t|Cleaning up and exiting...\n");
        }
    }
    /**
     * cleanUp closes the sockets and data streams
     * @param: nothing
     * @return: nothing
     *  */
    private void cleanup() {
        System.out.println("\t|ConnectionHandler: ...\n\t|cleaning up and exiting ...\n ");
        try {
            inFromClient.close();
            outToClient.close();
            socket.close();
        } catch (IOException ioe) {
            System.out.println("\t|ConnectionHandler:cleanup went wrong\n");
        }
    }


}
