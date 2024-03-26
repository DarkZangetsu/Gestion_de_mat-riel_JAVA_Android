package com.example.projet;

import android.widget.Spinner;

public class ItemClass {
    private String NumMat;
    private String Design;
    private String Etat;
    private int Quantite;

    public ItemClass() {

    }
    public ItemClass(String numMat, String design, String etat, int quantite) {
        NumMat = numMat;
        Design = design;
        Etat = etat;
        Quantite= quantite;
    }

    public ItemClass(String num, String design, Spinner etat, int nb) {
    }

    public String getNumMat() {
        return NumMat;
    }

    public void setNumMat(String numMat) {
        NumMat = numMat;
    }

    public String getDesign() {
        return Design;
    }

    public void setNom(String design) {
        Design = design;
    }

    public String  getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int quantite) {
        Quantite = quantite;
    }

}
