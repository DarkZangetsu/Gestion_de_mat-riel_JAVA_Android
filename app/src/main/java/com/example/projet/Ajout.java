package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Ajout extends AppCompatActivity {

    Button valider;
    EditText Num;
    EditText Design;
    Spinner Etat;
    EditText Quantite;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        Num = findViewById(R.id.NMatInput);
        Design = findViewById(R.id.DesignInput);
        Quantite = findViewById(R.id.QuantiteInput);
        valider = findViewById(R.id.Accept);

        valider.setOnClickListener(v -> {
            if (Num.getText().toString().isEmpty() || Design.getText().toString().isEmpty() || Quantite.getText().toString().isEmpty()){
                // Tu ne peux pas utiliser Etat.getText().toString().isEmpty() car Etat est un Spinner, pas un EditText.
                // Il faut vérifier si rien n'a été sélectionné dans le Spinner.
                System.out.println("Test ");
            }
            else {
                classDAO db = new classDAO(this);
                String num = Num.getText().toString();
                String design = Design.getText().toString();
                String etat = Etat.getSelectedItem().toString(); // Pour obtenir la valeur sélectionnée dans le Spinner
                int nb = Integer.parseInt(Quantite.getText().toString());
                db.ajouterItem(new ItemClass(num, design, etat, nb));
                finish();
            }
        });

        Spinner spinner = findViewById(R.id.spin_etat); // Enlève @SuppressLint("CutPasteId")

        String[] options = {"mauvais", "abimé", "bon"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        Etat = spinner; // Affecte le Spinner au champ Etat
    }
}
