package com.example.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

public class Modifier extends AppCompatActivity {
    Button valider;
    EditText Num;
    EditText Design;
    Spinner Etat; // Changement de EditText à Spinner
    EditText Quantite;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        Num = findViewById(R.id.NMatInput);
        Design = findViewById(R.id.DesignInput);
        Etat = findViewById(R.id.spin_etat); // Mise à jour de la référence du champ Etat
        Quantite = findViewById(R.id.QuantiteInput);
        valider = findViewById(R.id.AcceptMod);
        Intent intent = getIntent();
        Gson gson = new Gson();
        ItemClass item = gson.fromJson(intent.getStringExtra("data"), ItemClass.class);
        System.out.println(item.getEtat());

        Num.setText(item.getNumMat());
        Design.setText(item.getDesign());
        Etat.setSelection(getIndex(Etat, item.getEtat())); // Pour sélectionner la valeur dans le Spinner
        Quantite.setText(item.getQuantite()+"");

        // Ajout des options au Spinner
        String[] options = {"mauvais", "abimé", "bon"}; // Remplacez ces options par celles que vous souhaitez afficher

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Etat.setAdapter(adapter);

        valider.setOnClickListener(v -> {
            if (Num.getText().toString().contentEquals("") || Design.getText().toString().contentEquals("") || Etat.getSelectedItem().toString().contentEquals("") || Quantite.getText().toString().contentEquals("")){ // Utilisation de getSelectedItem() pour obtenir la valeur sélectionnée dans le Spinner
                System.out.println("test");
            }
            else {
                classDAO db = new classDAO(this);
                String num = Num.getText().toString();
                String design = Design.getText().toString();
                String etat = Etat.getSelectedItem().toString(); // Pour obtenir la valeur sélectionnée dans le Spinner
                int nb = Integer.parseInt(Quantite.getText().toString());
                db.modifierItem(new ItemClass(num,design,etat,nb));
                Intent intents = new Intent();
                setResult(RESULT_OK, intents);
                finish();
            }
        });

    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
