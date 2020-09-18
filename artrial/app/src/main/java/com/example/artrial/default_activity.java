package com.example.artrial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;

public class default_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_activity);
    }

    public void setIPandPort(View view)
    {

        EditText IP=(EditText)findViewById(R.id.ip);

        EditText Port=(EditText)findViewById(R.id.port);

        NetworkThread.maincont=this;

        NetworkThread.IP=IP.getText().toString();

        NetworkThread.port=Integer.parseInt(Port.getText().toString());

        Thread net=new Thread(new NetworkThread());

        net.start();

        try {
            net.join();
        }catch (InterruptedException e)
        {
            new Toast(this).makeText(this, "InterruptedException", Toast.LENGTH_LONG);
        }
        startActivity(new Intent(default_activity.this,MainActivity.class));

    }


}
