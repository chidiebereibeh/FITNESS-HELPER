package net.ingramintegrations.FitnessHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShoulderActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoulder);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference elbowRef = db.collection("Shoulder").document("Shoulder");
        elbowRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Shoulder");
                    int value= Integer.parseInt(documentSnapshot.getString("Shoulder"));
                    String value1= String.valueOf(value+1);
                    elbowRef.update("Shoulder",value1);
                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });




        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Shoulder!");

        Button previous = findViewById(R.id.previousButton);
        previous.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}