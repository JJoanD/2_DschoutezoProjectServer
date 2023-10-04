package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server servente = new Server();
        for(;;){
            servente.attendi();
        try{
            servente.comunica();
        }catch(Exception exception){}
        }
    }
    }

