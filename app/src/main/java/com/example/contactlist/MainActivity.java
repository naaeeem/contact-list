package com.example.contactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactlist.adapter.RecyclerViewAdapter;
import com.example.contactlist.data.MyDbHandler;
import com.example.contactlist.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "ANN";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;
    private MyDbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recyclerview initialization
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new MyDbHandler(MainActivity.this);

        // CRUD

        CREATE();
        // UPDATE();
        // DELETE();
        READ();
    }

    private void CREATE() {
        // Creating a contact object for the db
        Contact contact = new Contact();
        contact.setName("Abu Naeem");
        contact.setPhoneNumber("01521437627");

        // Adding a contact to the db
        db.addContact(contact);
    }
    private void READ() {
        contactArrayList = new ArrayList<>();

        // Get all contacts
        List<Contact> contactList = db.getAllContacts();
        for(Contact contact: contactList){
            Log.d(TAG, "Id: " + contact.getId() + "\n" +
                            "Name: " + contact.getName() + "\n"+
                            "Phone Number: " + contact.getPhoneNumber() + "\n" );
            contactArrayList.add(contact);
        }

        // Use your recyclerView
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        Log.d(TAG, "Bro you have "+ db.getCount()+ " contacts in your database");
    }
    private void UPDATE() {
        Contact contact = new Contact();
        contact.setId(2);
        contact.setName("Changed Tehri");
        contact.setPhoneNumber("0000000000");
        int affectedRows = db.updateContact(contact);
    }
    private void DELETE() {

        db.deleteContactById(1);
    }
}