package Lab10;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;
/**
 * Client is the client side of a program that allows users to send percentage grades to a server and receive a letter grade in response
 * @author: Kevin Mah
 *  */
public class Client implements Configuration{
    /**
     * variables that will be used
     * */
    private Socket clientSocket;
    private String host;
    private int port;
    private DataInputStream inFromServer;
    private Scanner inFromKeyboard;
    private DataOutputStream outToServer;
    /**
     * Client sets the host and port objects to the input host and port
     * @param host,port: a host and a port
     * @return: nothing
     *  */
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        requestService();
    }
    /**
     * requestService attempts to connect the client to the server, and also takes in inputs from the server
     * @param: nothing
     * @return: nothing
     *  */
    private void requestService() {
        try {
            clientSocket = new Socket(host, port);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new DataInputStream(clientSocket.getInputStream());
            inFromKeyboard = new Scanner(System.in);
            System.out.printf("\t|Client connected to <<%s>> on port <<%d>>\n",host,port);
            System.out.printf("\t|To quit enter type -1\n\t|");
            int grade = 0;
            while (grade != Configuration.QUIT){
                grade = this.inFromKeyboard.nextInt();
                if (grade == Configuration.QUIT) {
                    throw new ClientDisconnectedException ("\t|Closing connection to server \n\t|Connection Handler...\n");
                }
                outToServer.writeInt(grade); // print line out on the socket's output stream
                outToServer.flush(); // flush the output stream so that the server gets message immediately
                System.out.printf("\t|Grade <<%s>> sent to server\n",grade);
                String letterGrade = inFromServer.readUTF();
                System.out.print("\t|Server response to your grade letter is <<"+ letterGrade +">>\n\t|");
            }
        }
        catch (ClientDisconnectedException cdex) {
            // exit cleanly for any Exception (including IOException, DisconnectedException)
            System.out.printf(cdex.getMessage());
            cleanup(); // execute cleanup method to close connections cleanly
            System.exit(0);
        }
        catch (Exception ex) {
            System.out.printf("\t|%s.\n", ex.getMessage());
            // exit cleanly for any Exception (including IOException, DisconnectedException)
            cleanup(); // execute cleanup method to close connections cleanly
            System.exit(1);
        }

    }
    /**
     * cleanUp closes the various sockets, and the scanner
     * @param: nothing
     * @return: nothing
     *  */
    private void cleanup() {
        try {
            clientSocket.close();
            if(outToServer != null) outToServer.close();
            if(inFromServer != null) inFromServer.close();
            if(inFromKeyboard != null) inFromKeyboard.close();

        } catch (IOException ioe) {
            System.out.printf("\t|%s.\n", ioe.getMessage());
        }       }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.printf("\t|Usage: java -cp networking.Client <<hostname>> <<port>>");
            System.exit(1);
        }
        int port = Integer.parseInt(args[1]);
        new Client(args[0], port);
    }
}
