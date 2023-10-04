package com.example;

import java.io.*;
import java.net.*;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringModifica = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi(){
       
        try{
            System.out.println("1 SERVER partito in esecuzione");
            //creo un server sulla porta 6789
            if(server == null){
                server = new ServerSocket(6789);
            }
            //rimane in attesa di un client
            client = server.accept();
            //non va chiuso il server cosi da poter inibire altri client

            //associo due oggetti al coket del client per effetuare la scrittura e la lettura , ossia gli stream
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica() throws Exception{
           
            for(;;){
                //problema  nella stringa ricevuta
                stringaRicevuta = inDalClient.readLine();

                //controllo della stringa rivceuta
                if(stringaRicevuta == null || stringaRicevuta.equals("BYE")){
                    outVersoClient.writeBytes(stringaRicevuta + " (=> server in chiusura.." + "\n");
                    System.out.println("Echo sul server in chiusura :" + stringaRicevuta);
                    break;
                }else{
                    
                    //invio rispostqa in maiuscolo
                    stringModifica = stringaRicevuta.toUpperCase();
                    outVersoClient.writeBytes(stringModifica + "(ricevuta e ritrasmessa)" + "\n");
                    System.out.println("Echo sul server in chiusura : " + stringModifica);
                }
                outVersoClient.writeBytes(stringModifica+"\n");
                System.out.println("9 SERVER : fine elaborazione");
                
            }

            System.out.println("9 chiusura socket " + client);
            client.close();
        }
    
}

