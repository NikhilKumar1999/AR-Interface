package com.example.artrial;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {



    public static String Message;
    public static Socket soc;
    public static Context con;
    @Override
    public void run()
    {

        try {
            Looper.prepare();
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(soc.getOutputStream())),
                    true);
            out.println(Message);
        } catch (IOException e) {
            Toast t = Toast.makeText(con, "Exception in client:UnknownHost", Toast.LENGTH_LONG);
            t.show();
        }
    }
}
