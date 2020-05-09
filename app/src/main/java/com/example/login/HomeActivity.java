package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    private EditText subjEditText;
    private EditText lessnEditText;
    private Button sbmtButton;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    String subjview, lessnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        subjEditText = (EditText)findViewById(R.id.subjectEditText);
        lessnEditText = (EditText)findViewById(R.id.lessonEditText);
        sbmtButton = (Button)findViewById(R.id.submitBtn);
        firebaseAuth = FirebaseAuth.getInstance();


        subjview = subjEditText.getText().toString();
        lessnview = lessnEditText.getText().toString();



        sbmtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subjview.isEmpty() || lessnview.isEmpty()) {
                    sendUserData();
                } else {
                    Toast.makeText(HomeActivity.this, "Inavalid Input", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }



    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myreference = firebaseDatabase.getReference(firebaseAuth.getUid());
        Subjectprofile subjectprofile = new Subjectprofile(subjview, lessnview);
        myreference.setValue(subjectprofile);


    }


}
