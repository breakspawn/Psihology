package com.example.psihology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;

public class ClientEditForm extends AppCompatActivity {


    private Button add_Client;
    private TextInputLayout nameCLient;
    private TextInputLayout age;
    private TextInputLayout phone;
    private TextInputLayout biography;
    private TextInputLayout question;
    private TextInputLayout note;
    private TextInputLayout anamnez;

    private SlidrInterface slidr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        Slidr.attach(this);

        initView();
        Intent intent = getIntent();
        String clientJson = intent.getStringExtra("json");

        // если нет переданного клиента - режим добавления нового клиента
        if(clientJson == null) {

            add_Client.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DataBaseWorker worker = new DataBaseWorker(ClientEditForm.this);
                            ArrayList<String> ClientsToDatabase = new ArrayList<String>();
                            ClientsToDatabase.add(makeClientFromFields().toJson());
                            if (worker.insert(worker.T_CLIENTS, worker.F_DATA_CLIENT, ClientsToDatabase)) {
                                setFields(null);
                                Toast.makeText(ClientEditForm.this, "Успешно", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(ClientEditForm.this, "Ошибка добавления", Toast.LENGTH_LONG).show();

                        }

                    }
            );
        }
        // если есть переданный клиент - режим редактирования
        else
        {
            Client selectedClient = new Client(clientJson);
            // заполнить поля выбранным клиентом
            setFields(selectedClient);
            // настроить кнопку на редактирование существующего клиента

        }
    }
    /// инициализация форм
    void initView()
    {
        add_Client = (Button) findViewById(R.id.addNewClient);
        nameCLient = (TextInputLayout) findViewById(R.id.editTextName);
        age = (TextInputLayout) findViewById(R.id.editTextAge);
        phone = (TextInputLayout) findViewById(R.id.editTextTelephone);
        biography = (TextInputLayout) findViewById(R.id.editTextBiography);
        question = (TextInputLayout) findViewById(R.id.editTextQuestion);
        note = (TextInputLayout) findViewById(R.id.editTextNote);
        anamnez = (TextInputLayout) findViewById(R.id.editTextAnamnez);
        slidr = Slidr.attach(this);
    }


    /// сброс полей в дефолтное состояние
    void setFields(Client c)
     {
         nameCLient.getEditText().setText(c == null? null : c.name);
         age.getEditText().setText(c == null? null : String.valueOf(c.age));
         phone.getEditText().setText(c == null? null : c.phone);
         biography.getEditText().setText(c == null? null : c.biography);
         question.getEditText().setText(c == null? null : c.question);
         note.getEditText().setText(c == null? null : c.note);
         anamnez.getEditText().setText(c == null? null : c.anamnez);
     }

     /// формирует клинта на оснве заплненых полей
    Client makeClientFromFields()
    {
        Client temp = new Client();
        temp.name = String.valueOf(nameCLient.getEditText().getText());
        temp.age = Integer.valueOf(age.getEditText().getText().toString());
        temp.phone = String.valueOf(phone.getEditText().getText());
        temp.biography = String.valueOf(biography.getEditText().getText());
        temp.question = String.valueOf(question.getEditText().getText());
        temp.note = String.valueOf(note.getEditText().getText());
        temp.anamnez = String.valueOf(anamnez.getEditText().getText());

        return temp;
    }
}