package com.example.avneesh.speech_to_text;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
     Button b1;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button) this.findViewById(R.id.button);
        tv=(TextView)this.findViewById(R.id.textview);
        b1.setOnClickListener(this);
        ConnectivityManager cm =(ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info= cm.getActiveNetworkInfo();
        if(info.isAvailable()&& info.isConnected()){
            Toast.makeText(this, "INTERNET CONNECTED!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode ==RESULT_OK){
            ArrayList<String> arraylist =data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            tv.setText(arraylist.get(0));
        }
        if(resultCode== RESULT_CANCELED){
            Toast.makeText(this, "Cancelled by user", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
          Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.ENGLISH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"speak something!");
        this.startActivityForResult(intent,123);
    }
}
