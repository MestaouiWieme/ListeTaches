package com.example.listetaches;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<String>();
    ListView listeView;
    ArrayAdapter myAdapter;
    EditText inputText1;
    Integer index;
    String item;
    Button btnAdd,btnUpdate,btnDelete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        inputText1 = findViewById(R.id.editText);
        listeView = findViewById(R.id.listeItems);
        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listeView.setAdapter(myAdapter);

        // add items
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String val = inputText1.getText().toString();
                if (!val.isEmpty()) {
                    items.add(val);
                    myAdapter.notifyDataSetChanged();
                    inputText1.setText("");
                    Toast.makeText(MainActivity.this, "Tache ajoutée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez entrer une tache!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //select items and delete items
        listeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String value = adapterView.getItemAtPosition(i).toString();
                item = adapterView.getItemAtPosition(i).toString() + " est séléctionnée";
                index = i;
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
                inputText1.setText(value);

                //delete items

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        items.remove(i);
                        Toast.makeText(MainActivity.this, "Tache supprimée", Toast.LENGTH_SHORT).show();
                        myAdapter.notifyDataSetChanged();
                        inputText1.setText("");
                    }
                });
            }
        });


        // update items
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String val = inputText1.getText().toString();
                items.set(index, val);

                if (!val.isEmpty()) {
                    myAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Tache modifiée", Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(MainActivity.this, "Veuiller Choisir une tache!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}