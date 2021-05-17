package net.ingramintegrations.FitnessHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import org.w3c.dom.Document;

import java.util.ArrayList;

public class ElbowActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elbow);



        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference elbowRef = db.collection("Elbow").document("Elbow");
        elbowRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                   documentSnapshot.getString("Elbow");
                   int value= Integer.parseInt(documentSnapshot.getString("Elbow"));
                   String value1= String.valueOf(value+1);
                    elbowRef.update("Elbow",value1);
                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });




        Button previous = findViewById(R.id.previousButton2);
        previous.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}