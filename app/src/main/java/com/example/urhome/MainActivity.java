package com.example.urhome;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
 DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://ur-home-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText email=findViewById(R.id.editTextTextPersonName);
        final EditText pw =findViewById(R.id.editTextTextPersonName2);
        final Button signin =findViewById(R.id.signin);
        final TextView signup =findViewById(R.id.textView4);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=email.getText().toString();
                final String pass = pw.getText().toString();


                if(name.isEmpty() || pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "please enter your user or password",Toast.LENGTH_SHORT).show();

                }
                else {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(name)){
                                final  String getpassword= snapshot.child(name).child("password").getValue(String.class);
                            if (getpassword.equals(pass)){
                                Toast.makeText(MainActivity.this, "logged in successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this,loading.class));
                                    finish();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "wrong password",Toast.LENGTH_SHORT).show();
                            }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (MainActivity.this, signup.class));

            }
        });
    }}