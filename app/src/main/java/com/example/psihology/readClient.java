package com.example.psihology;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class readClient extends AppCompatActivity {

    private ListView listView;
    private ListView infoClient;


    private Map<Integer, Integer> posToId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_client);
        posToId = new HashMap<Integer, Integer>();
        listView = (ListView)findViewById(R.id.listActiv);
        SlidrInterface slidr = Slidr.attach(this);



        ArrayList<String> clientNames = new ArrayList<String>();


        final PsyhoKeeper keeper = new PsyhoKeeper(readClient.this);
        ArrayList<Pair<Integer, Client>> clients = keeper.getAllClients();
        clientNames.clear();
        int i = 0;
        for (Pair<Integer, Client> c : clients) {
            clientNames.add(c.second.name);
            posToId.put(i++,c.first);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, R.layout.activ, clientNames); //назначение массива для тектса
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                        try {
                            int sizeMap = posToId.size();
                            int idClient = posToId.get(position).intValue();
                            Client selectedClient = keeper.getClientById(idClient);

                            Intent intent = new Intent("com.example.psihology.ClientEditForm"); //это код из фрагмента
                            intent.putExtra("json", selectedClient.toJson());
                            startActivity(intent);



                        }catch (NullPointerException npe)
                        {
                            System.out.println(npe.getStackTrace());
                        }
                    }
                }
        );


    }
}