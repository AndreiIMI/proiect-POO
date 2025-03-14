package org.poo.cb;

import java.util.LinkedList;
import java.util.List;

public class Actiuni {
    private final String numeCompanie;
    private int numarActiuni;
    private final List<Double> ultimeleValori = new LinkedList<>();

    public Actiuni(String numeCompanie) {
        this.numeCompanie = numeCompanie;
        this.numarActiuni = 0;
    }

    public String getNumeCompanie() {
        return numeCompanie;
    }

    public int getNumarActiuni() {
        return numarActiuni;
    }

    public void adaugaActiuni(int numarActiuni) {
        this.numarActiuni += numarActiuni;
    }

    public List<Double> getUltimeleValori() {
        return ultimeleValori;
    }
}
