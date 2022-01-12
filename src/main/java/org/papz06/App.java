package org.papz06;

import org.papz06.Controllers.ScheduleController;
import org.papz06.Models.Schedule;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

import static org.papz06.JavaHTTPServer.PORT;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        try {
            try (ServerSocket serverConnect = new ServerSocket(PORT)) {
                System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
                // we listen until user halts server execution
                while (true) {
                    JavaHTTPServer myServer = new JavaHTTPServer(serverConnect.accept());
                    System.out.println("Connecton opened. (" + new Date() + ")");
                    // create dedicated thread to manage the client connection
                    Thread thread = new Thread(myServer);
                    thread.start();
                }
            }

        } catch (IOException e) {
            System.err.println("Server Connection error : " + e.getMessage());
        }
    }
}
