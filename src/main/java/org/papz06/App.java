package org.papz06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.papz06.Controllers.CinemaController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

import static org.papz06.JavaHTTPServer.PORT;

/**
 * Hello world!
 */
public class App {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    public static void main(String[] args) {
        try {
            ServerSocket serverConnect = new ServerSocket(PORT);
            System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");

            // we listen until user halts server execution
            while (true) {
                JavaHTTPServer myServer = new JavaHTTPServer(serverConnect.accept());

                System.out.println("Connecton opened. (" + new Date() + ")");


                // create dedicated thread to manage the client connection
                Thread thread = new Thread(myServer);
                thread.start();
            }

        } catch (IOException e) {
            System.err.println("Server Connection error : " + e.getMessage());
        }
    }
}
