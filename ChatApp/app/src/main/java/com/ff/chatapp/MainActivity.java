package com.ff.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


// ## MainActivity Class
public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_main;
    FloatingActionButton fab;

    @Override
    // ## onOptionsItemSelected
    public boolean onOptionsItemSelected(MenuItem item) {
        // ## getItemId
        if(item.getItemId() == R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() { // ## start object signOut
                @Override
                // ## onComplete
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_main,"You have been signed out.", Snackbar.LENGTH_SHORT).show();
                    finish();
                } // end ## onComplete

            }); // end ## start object signOut
        } // end ## getItemId

        return true;
    } // end ## onOptionsItemSelected

    @Override
    // ## onCreateOptionsMenu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    } // end ## onCreateOptionsMenu

    @Override
    // ## onActivityResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // ## requestCode
        if(requestCode == SIGN_IN_REQUEST_CODE)
        {
            // ## requestCode == RESULT_OK
            if(resultCode == RESULT_OK) {
                Snackbar.make(activity_main,"Successfully signed in. Welcome!", Snackbar.LENGTH_SHORT).show();
                displayChatMessage();
            } else {
                Snackbar.make(activity_main,"We couldn't sign you in, please try again later!", Snackbar.LENGTH_SHORT).show();
                finish();
            } // end ## resultCode == RESULT_OK
        } // end ## requestCode

    } // end ## onActivityResult

    @Override
    // ## OnCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (RelativeLayout)findViewById(R.id.activity_main);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() { // ## start object View
            @Override
            // ## onClick View
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().child("ChatMessage").push().setValue(new ChatMessage(input.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
            } // end ## onClick View

        });
        // end ## start object View

        // Check if not sign-in then navigate to Sign-in page
        // ## Firebase Auth
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        } else {
            Snackbar.make(activity_main,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(), Snackbar.LENGTH_SHORT).show();
            displayChatMessage();
        } // end ## Firebase Auth

    } // end ## OnCreate

    // ## displayChatMessage
    private void displayChatMessage()
    {
        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);

        Query query = FirebaseDatabase.getInstance().getReference().child("ChatMessage");
        FirebaseListOptions<ChatMessage> options =
                new FirebaseListOptions.Builder<ChatMessage>()
                        .setQuery(query,ChatMessage.class)
                        .setLayout(R.layout.list_item)
                        .build();

        adapter = new FirebaseListAdapter<ChatMessage>(options) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {

                // Get references to the views of list_item.xml
                TextView messageText,messageUser,messageTime;
                messageText = (TextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",model.getMessageTime()));

            }
        };

        adapter.startListening();
        listOfMessage.setAdapter(adapter);

    } // end ## displayChatMessage

    @Override
    protected void onStop() {
        if(adapter !=null)
            adapter.stopListening();
        super.onStop();
    }

} // ## MainActivity Class