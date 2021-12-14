package assessment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.print.attribute.SupportedValuesAttribute;

public class HttpServer 

{
    public static void main(String[] args) throws IOException {
        
        Socket socket;
        ServerSocket serverSocket;
        String inputFile;
        Integer port = 0;
        Scanner scans = new Scanner(System.in);
        InputHandler handler = new InputHandler();

        String commands;
        String arguments;

        commands = scans.next();
        arguments = scans.nextLine();

        if (args!=null && args.length >=1){
            inputFile = args[0];
            if(commands.contains("port")){
                handler.getportNumber(commands, arguments, port);
            }
        }
        else{
            System.out.println("Hello");
        }

        System.out.println("Server listening at port" + port);
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        String line = in.readLine();

        while (!"close".equals(line) && null != line) {

            System.out.println("Client: " + line);
            
            try {
                
                if ("testserver".equals(line)) {
                    System.out.println("Getting some data");
                    out.flush();
                    line = in.readLine();
                } else {
                    out.println("Server: you said " + line);
                    out.flush();
                    line = in.readLine();
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            } 
        }



    }
}
