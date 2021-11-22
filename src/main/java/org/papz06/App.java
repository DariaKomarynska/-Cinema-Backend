package org.papz06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
