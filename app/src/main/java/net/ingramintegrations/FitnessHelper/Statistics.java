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

    ArrayList<Integer> hello = new ArrayList<>();
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
//this is how I want to get the value from database and then put it in (loadPieData)
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference elbowRef = db.collection("Elbow").document("Elbow");
        elbowRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    System.out.println(documentSnapshot);
                    documentSnapshot.getString("Elbow");
                    int elbow= Integer.parseInt(documentSnapshot.getString("Elbow"));

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
        ArrayList<PieEntry> entries = new ArrayList<>();
        int elbow=50;

        entries.add(new PieEntry(elbow, "elbow"));
        entries.add(new PieEntry(10, "foot"));
        entries.add(new PieEntry(80, "knee"));
        entries.add(new PieEntry(12, "neck"));
        entries.add(new PieEntry(5, "shoulder"));
        entries.add(new PieEntry(99, "thigh"));
        entries.add(new PieEntry(43, "wrist"));



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


