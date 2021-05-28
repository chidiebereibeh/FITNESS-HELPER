package net.ingramintegrations.FitnessHelper;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {
    ArrayList<PieEntry> entries = new ArrayList<>();

    private PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        pieChart = findViewById(R.id.statistics_main_piechart);
        loadPieChartData();
        setupPieChart();


        Button exit=findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivate();
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //Gets elbow value from database and stores it in PieChart

        DocumentReference elbowRef = db.collection("Elbow").document("Elbow");
        elbowRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Elbow");
                    int elbow= Integer.parseInt(documentSnapshot.getString("Elbow"));
                    entries.add(new PieEntry(elbow, "elbow"));
                    loadPieChartData();



                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });
        //Gets foot value from database and stores it in PieChart
        DocumentReference footRef = db.collection("Foot").document("Foot");
        footRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Foot");
                    int foot= Integer.parseInt(documentSnapshot.getString("Foot"));
                    entries.add(new PieEntry(foot, "Foot"));
                    loadPieChartData();



                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });
        //Gets knee value from database and stores it in PieChart
        DocumentReference kneeRef = db.collection("Knee").document("Knee");
        kneeRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Knee");
                    int knee= Integer.parseInt(documentSnapshot.getString("Knee"));
                    entries.add(new PieEntry(knee, "Knee"));
                    loadPieChartData();



                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });

        DocumentReference neckRef = db.collection("Neck").document("Neck");
        neckRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Neck");
                    int neck= Integer.parseInt(documentSnapshot.getString("Neck"));
                    entries.add(new PieEntry(neck, "Neck"));
                    loadPieChartData();



                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });
        DocumentReference shoulderRef = db.collection("Shoulder").document("Shoulder");
        shoulderRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Shoulder");
                    int shoulder= Integer.parseInt(documentSnapshot.getString("Shoulder"));
                    entries.add(new PieEntry(shoulder, "Shoulder"));
                    loadPieChartData();



                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });
        //Gets thigh value from database and stores it in PieChart
        DocumentReference thighRef = db.collection("Thigh").document("Thigh");
        thighRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Thigh");
                    int thigh= Integer.parseInt(documentSnapshot.getString("Thigh"));
                    entries.add(new PieEntry(thigh, "Thigh"));
                    loadPieChartData();



                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });
        DocumentReference wristRef = db.collection("Wrist").document("Wrist");
        wristRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Wrist");
                    int wrist= Integer.parseInt(documentSnapshot.getString("Wrist"));
                    entries.add(new PieEntry(wrist, "Wrist"));
                    loadPieChartData();



                }
                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        });


    }





    private void setupPieChart() {

        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Injuries");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);



    }


    private void loadPieChartData() {


        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Injuries");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);


    }

    public void openActivate() {
        Intent intent = new Intent(Statistics.this, MainActivity.class);
        startActivity(intent);
    }
}


