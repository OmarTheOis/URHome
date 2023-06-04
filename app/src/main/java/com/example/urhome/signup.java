package com.example.urhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ur-home-default-rtdb.firebaseio.com"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        final EditText username=findViewById(R.id.editTextTextPersonName4);
        final EditText password=findViewById(R.id.editTextTextPersonName5);
        final EditText confpassword=findViewById(R.id.editTextTextPersonName6);
        final Button signup =findViewById(R.id.button);
        final TextView login =findViewById(R.id.textView12);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=username.getText().toString();
                final String pass=password.getText().toString();
                final String conpass=confpassword.getText().toString();
                if(name.isEmpty() || pass.isEmpty()){
                    Toast.makeText(signup.this, "please fill your data",Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(conpass)){
                    Toast.makeText(signup.this, "password not matches",Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(name)){
                                Toast.makeText(signup.this, "user name is used before",Toast.LENGTH_SHORT).show();
                            }
                            else{databaseReference.child("users").child(name).child("username").setValue(name);
                                databaseReference.child("users").child(name).child("password").setValue(pass);
                                Toast.makeText(signup.this,"regersterd successufly",Toast.LENGTH_SHORT).show();}
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}