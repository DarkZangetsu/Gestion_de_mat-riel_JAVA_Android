package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button ajout;
    private ListView mListView;
    private ItemAdpter mAdapter;
    private TextView total;
    private TextView totalMauvais;
    private TextView totalAbime;
    private TextView totalBon;

    ImageButton actu;
    private ArrayList<ItemClass> mData = new ArrayList<>();
    @SuppressLint({"SetTextI18n", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.listMode);
        total = findViewById(R.id.total);
        totalMauvais = findViewById(R.id.total_mauvais);
        totalAbime = findViewById(R.id.total_abime);
        totalBon = findViewById(R.id.total_bon);

        actu = findViewById(R.id.Actu);
        classDAO db = new classDAO(this);
        // Ajoutez des données à votre liste
        mData = db.getAllItems();
        // Ajoutez autant de données que vous voulez
        mAdapter = new ItemAdpter(this, mData);
        mListView.setAdapter(mAdapter);

        // Notifiez à l'adaptateur que les données ont changé
        mAdapter.notifyDataSetChanged();
        ajout = findViewById(R.id.Ajout);
        ajout.setOnClickListener(v -> {
            Intent intent = new Intent(this, Ajout.class);
            startActivity(intent);
        });
        actu.setOnClickListener(v -> {
            // Réactualisez toutes les valeurs
            total.setText("Total:" + db.getTotalQuantite());
            totalMauvais.setText("Mauvais: " + db.getTotalQuantiteByEtat("mauvais"));
            totalAbime.setText("Abimé: " + db.getTotalQuantiteByEtat("abimé"));
            totalBon.setText("Bon: " + db.getTotalQuantiteByEtat("bon"));
        });

        // Affichez les valeurs initiales
        afficherValeurs(db);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        classDAO db = new classDAO(this);
        // Ajout des données
        mData = db.getAllItems();
        // Ajout de données que possible
        mAdapter = new ItemAdpter(this, mData);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        // Réactualisez les valeurs lors de la reprise de l'activité
        afficherValeurs(db);
    }

    @SuppressLint("SetTextI18n")
    private void afficherValeurs(classDAO db) {
        total.setText("Total:" + db.getTotalQuantite());
        totalMauvais.setText("Mauvais: " + db.getTotalQuantiteByEtat("mauvais"));
        totalAbime.setText("Abimé: " + db.getTotalQuantiteByEtat("abimé"));
        totalBon.setText("Bon: " + db.getTotalQuantiteByEtat("bon"));
    }
}
