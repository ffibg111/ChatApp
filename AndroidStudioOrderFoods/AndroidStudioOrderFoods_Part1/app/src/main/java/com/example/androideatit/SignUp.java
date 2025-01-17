package com.example.androideatit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androideatit.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText editTxtPhone, editTxtName, editTxtPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTxtPhone = (MaterialEditText) findViewById(R.id.editTxtPhone);
        editTxtName = (MaterialEditText) findViewById(R.id.editTxtName);
        editTxtPassword = (MaterialEditText) findViewById(R.id.editTxtPassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        // Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String phoneTxt = editTxtPhone.getText().toString();
                        if (TextUtils.isEmpty(phoneTxt)) {
                            Toast.makeText(SignUp.this, "Please enter a phone number!", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        } else {
                            // check if phone number exists
                            if (dataSnapshot.hasChild(editTxtPhone.getText().toString())) {
                                mDialog.dismiss();
                                Toast.makeText(SignUp.this, "Phone number already registered!", Toast.LENGTH_SHORT).show();
                            } else {
                                mDialog.dismiss();
                                User user = new User(editTxtName.getText().toString(), editTxtPassword.getText().toString());
                                table_user.child(editTxtPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
