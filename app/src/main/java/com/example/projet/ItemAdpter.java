package com.example.projet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ItemAdpter extends ArrayAdapter<ItemClass> {
    private final Context mContext;
    private final ArrayList<ItemClass> mDataSet;
    private static final int REQUEST_CODE_ACTIVITE2 = 123;

    public ItemAdpter(Context context, ArrayList<ItemClass> dataSet) {
        super(context, R.layout.item, dataSet);
        this.mContext = context;
        this.mDataSet = dataSet;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.item, parent, false);

        TextView NumMat = rowView.findViewById(R.id.NMAT);
        TextView Design = rowView.findViewById(R.id.Design);
        TextView Etat = rowView.findViewById(R.id.Etat);
        TextView Quantite = rowView.findViewById(R.id.Quantite);

        ImageButton btnSuppr = rowView.findViewById(R.id.Suppression);
        ImageButton btnModif = rowView.findViewById(R.id.Modification);

        NumMat.setText(NumMat.getText()+" "+mDataSet.get(position).getNumMat());
        Design.setText(Design.getText()+" "+mDataSet.get(position).getDesign());
        Etat.setText(Etat.getText()+" "+mDataSet.get(position).getEtat());
        Quantite.setText(Quantite.getText()+" "+mDataSet.get(position).getQuantite());


        btnSuppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Géstion des évenement de bouton
                if (mDataSet.size() > position) {
                    classDAO db = new classDAO(mContext);
                    db.supprimerItem(mDataSet.get(position).getNumMat());
                    // Supprimer l'élément de la liste à la position cliquée
                    mDataSet.remove(position);
                    notifyDataSetChanged(); // Mettre à jour l'affichage
                }}
        });
        btnModif.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, Modifier.class);
            Gson gson = new Gson();
            String json = gson.toJson(mDataSet.get(position));
            intent.putExtra("data",json);
            mContext.startActivity(intent);

        });

        return rowView;
    }
}
