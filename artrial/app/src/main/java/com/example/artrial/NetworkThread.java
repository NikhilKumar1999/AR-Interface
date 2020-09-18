package com.example.artrial;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import android.content.Context.*;
import java.net.*;

public class NetworkThread implements Runnable {
    public static String IP;
    public static int port;
    public static Context maincont;
    public static Socket soc;
    @Override
    public void run() {
        Looper.prepare();
        try {
            Log.d("debug","1");
            InetAddress address = InetAddress.getByName(IP);
            Log.d("debug","2");
            Log.d(IP+port,"2");
            soc=new Socket(address,port);
            Log.d("debug","3");
            ClientThread.soc=soc;
            Log.d(IP+port,"4");
            ClientThread.Message="5";
            //Log.d("Same hit","Same hit");
            new Thread(new ClientThread()).start();
        }catch (UnknownHostException e)
        {
            Log.d("debug","5");
            new Toast(maincont).makeText(maincont,"UnknownHostException",Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            Log.d("debug","6");
            Log.d("IOException:\n",e.getMessage());
            new Toast(maincont).makeText(maincont,"IOException",Toast.LENGTH_LONG).show();
        }

    }
}
