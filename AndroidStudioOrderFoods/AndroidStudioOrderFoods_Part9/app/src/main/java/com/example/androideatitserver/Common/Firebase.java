package com.example.androideatitserver.Common;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {

    FirebaseDatabase database;
    DatabaseReference category;

    public void initFirebase() {
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Categorxy");
    }
}
