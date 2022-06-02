package com.example.cc2tdi202;

import java.io.Serializable;

public class Entreprise implements Serializable {
    private int id;
    private String RaisonSociale;
    private String Adresse;
    private Double Capitale;

    public Entreprise(){

    }
    public Entreprise(int i, String RS, String A, Double C){
        this.id=i;
        this.RaisonSociale=RS;
        this.Adresse=A;
        this.Capitale=C;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return RaisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        RaisonSociale = raisonSociale;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public Double getCapitale() {
        return Capitale;
    }

    public void setCapitale(Double capitale) {
        Capitale = capitale;
    }
}
