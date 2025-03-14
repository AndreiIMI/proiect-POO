package org.poo.cb;

public abstract class Cont {
    private String numeValuta;
    private double suma;
    private final SchimbStrategy schimbStrategy;

    public Cont(SchimbStrategy schimbStrategy) {
        this.schimbStrategy = schimbStrategy;
        this.suma = 0;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public void alimentareCont(double suma) {
        this.suma += suma;
    }

    public void extragereCont(double suma) {
        this.suma -= suma;
    }

    public abstract boolean tipValuta(String valuta);

    public SchimbStrategy getSchimbStrategy() {
        return schimbStrategy;
    }

    public void setNumeValuta(String numeValuta) {
        this.numeValuta = numeValuta;
    }

    public String getNumeValuta() {
        return numeValuta;
    }
}
