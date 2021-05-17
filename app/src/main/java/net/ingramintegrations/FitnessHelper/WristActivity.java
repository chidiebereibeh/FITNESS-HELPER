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

public class WristActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrist);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference elbowRef = db.collection("Wrist").document("Wrist");
        elbowRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Wrist");
                    int value= Integer.parseInt(documentSnapshot.getString("Wrist"));
                    String value1= String.valueOf(value+1);
                    elbowRef.update("Wrist",value1);
                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });

        Button previous = findViewById(R.id.previousButton4);
        previous.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}