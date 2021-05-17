package net.ingramintegrations.FitnessHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference elbowRef = db.collection("Foot").document("Foot");
        elbowRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Foot");
                    int value= Integer.parseInt(documentSnapshot.getString("Foot"));
                    String value1= String.valueOf(value+1);
                    elbowRef.update("Foot",value1);
                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });

        Button previous = findViewById(R.id.previousButton3);
        previous.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}