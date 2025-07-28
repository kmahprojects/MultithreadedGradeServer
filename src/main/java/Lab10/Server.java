package Lab10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ServerSocket ss;

    public Server (int port){
        try {
            this.ss = new ServerSocket(port);
            System.out.printf("\t|Server with IP address <<%s>> started ...\n\t|listening on port <<%d>> for client requests\n", this.ss.getInetAddress(), port);

            while (true){
                Socket clientSocket = ss.accept();
                System.out.printf("\t|Server got new connection request from <<%s>>\n" ,clientSocket.getInetAddress());
                // create new handler for the connection
                ConnectionHandler handler = new ConnectionHandler(clientSocket);
                handler.start(); // handle the client request
            }
        } catch (IOException ioe) {
            System.out.printf("\t|Something went wrong\n\t|%s",ioe.getMessage());
        }  catch (Exception ex) {
            System.out.printf("\t|%s\n",ex.getMessage());
        }
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.printf("\t|Usage: java -cp networking.Server <<port>>");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        new Server(port);
    }
}
