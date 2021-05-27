package net.ingramintegrations.FitnessHelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpperBackActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upperback);


        //Updates value in database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference upperBack = db.collection("upperBack").document("upperBack");
        upperBack.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("upperBack");
                    int value= Integer.parseInt(documentSnapshot.getString("upperBack"));
                    String value1= String.valueOf(value+1);
                    upperBack.update("upperBack",value1);
                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });

        Button previous = findViewById(R.id.previousButton8);
        previous.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
