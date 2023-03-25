package com.example.sirenatechnologies.smartroom;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Dell on 28-03-2018.
 */

public class socClient extends AsyncTask {
    int port = 5560;
    String ip;
    int data;
    PrintStream ps;
   socClient(String addr,int value ){
       ip = addr;
       data= value;
   }
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            Socket socket = new Socket(ip,port);
            ps = new PrintStream(socket.getOutputStream());
            ps.flush();
            ps.print(data);
            Log.e( "data ", String.valueOf(data));
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
  /*  public void send(String word){
       String w = word;
        ps.print(w);

    }*/
}
