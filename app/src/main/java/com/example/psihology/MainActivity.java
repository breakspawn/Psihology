package com.example.psihology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class MainActivity extends AppCompatActivity {

    private Button addClientBt;
    private Button readClientBt;
    private Button methodsTerapy;

    private SlidrInterface slidr;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        addClientBt = (Button) findViewById(R.id.addAnketBt);
        readClientBt = (Button) findViewById(R.id.watchAnketBt);
        methodsTerapy = (Button) findViewById(R.id.metodsBt);

        slidr = Slidr.attach(this);

        addClientBt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.psihology.ClientEditForm");
                        startActivity(intent);
                    }
                }
        );

        readClientBt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.psihology.readClient");
                        startActivity(intent);
                    }
                }
        );

    }


}