package org.papz06;

import javafx.util.Pair;
import org.papz06.Request.UserServer;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

// The tutorial can be found just here on the SSaurel's Blog :
// https://www.ssaurel.com/blog/create-a-simple-http-web-server-in-java
// Each Client Connection will be managed in a dedicated Thread
public class JavaHTTPServer implements Runnable {

    static final File WEB_ROOT = new File(".");
    static final String DEFAULT_FILE = "index.html";
    static final String FILE_NOT_FOUND = "404.html";
    //    static final String METHOD_NOT_SUPPORTED = "not_supported.html";
    // port to listen connection
    static final int PORT = 8080;

    // verbose mode
//    static final boolean verbose = true;

    // Client Connection via Socket Class
    private final Socket connect;

    public JavaHTTPServer(Socket c) {
        connect = c;
    }

    @Override
    public void run() {
        // we manage our particular client connection
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;
        String fileRequested;

        try {
            // we read characters from the client via input stream on the socket
            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            // we get character output stream to client (for headers)
            out = new PrintWriter(connect.getOutputStream());
            // get binary output stream to client (for requested data)
            dataOut = new BufferedOutputStream(connect.getOutputStream());

            // get first line of the request from the client
            String input = in.readLine();
            // we parse the request with a string tokenizer
            StringTokenizer parse = new StringTokenizer(input);
            String method = parse.nextToken().toUpperCase(); // we get the HTTP method of the client
            // we get file requested
            fileRequested = parse.nextToken().toLowerCase();

            if (method.equals("POST")) {
                while (input.length() != 0) {
                    input = in.readLine();
                }
                String sb = "";
                while (in.ready()) {
                    sb += (char) in.read();
                }
                Pair<Integer, String> result = new Pair<Integer, String> (200, "");

                /**
                 * Divider cases for PORT: - login
                 * **/
                // Case 1:
                if (fileRequested.equals("/login") ) result= new UserServer().login(sb);
                // Case 2:
                // Case 3:
                // Case 4:
                // Case 5:
                // Case 6:
                // Template
                out.println("HTTP/1.1 " + result.getKey() + " OK ");
                out.println("Server: Java HTTP Server from SSaurel : 1.0");
                out.println("Date: " + new Date());
                out.println("Content-type: application/json");
                out.println("Content-length: " + result.getValue().length());
                out.println("Access-Control-Allow-Origin: *");
                out.println(); // blank line between headers and content, very important !
                out.flush(); // flush character output stream buffer
                // End Template
                out.println(result.getValue());
                out.flush();
                dataOut.flush();
            } else if (method.equals("GET")) {
                // GET or HEAD method
                while (input.length() != 0) {
                    input = in.readLine();
                }
                String sb = "";
                while (in.ready()) {
                    sb += (char) in.read();
                }
                /**
                 * Divider cases for GET: -
                 * **/
                // Case 1:
                if (fileRequested.equals("/cinema") ) {
                    // Implement here
                }



            } else {
                System.out.println("501 Not Implemented : " + method + " method.");

                // we return the not supported file to the client
//                File file = new File(WEB_ROOT, METHOD_NOT_SUPPORTED);
//                int fileLength = (int) file.length();
//                String contentMimeType = "text/html";
//                //read content to return to client
//                byte[] fileData = readFileData(file, fileLength);

                // we send HTTP Headers with data to client
                out.println("HTTP/1.1 501 Not Implemented");
                out.println("Server: Java HTTP Server from SSaurel : 1.0");
                out.println("Date: " + new Date());
//                out.println("Content-type: " + contentMimeType);
//                out.println("Content-length: " + fileLength);
                out.println(); // blank line between headers and content, very important !
                out.flush(); // flush character output stream buffer
                // file
//                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();

            }


        } catch (IOException ioe) {
            System.err.println("Server error : " + ioe);
        } finally {
            try {
                in.close();
                out.close();
                dataOut.close();
                connect.close(); // we close socket connection
            } catch (Exception e) {
                System.err.println("Error closing stream : " + e.getMessage());
            }

            System.out.println("Connection closed.\n");
        }


    }

    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];

        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }

        return fileData;
    }

}