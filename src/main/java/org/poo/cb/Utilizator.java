package org.poo.cb;

import java.util.LinkedList;
import java.util.List;

public class Utilizator {
    private String email;
    private String nume;
    private String prenume;
    private String adresa;
    private final Portofoliu portofoliu = new Portofoliu();
    private final List<Utilizator> prieteni = new LinkedList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void adaugaPrieten(Utilizator prieten) {
        this.prieteni.add(prieten);
    }

    public Portofoliu getPortofoliu() {
        return portofoliu;
    }

    public Utilizator cautaPrieten(String emailPrieten) {
        for (Utilizator utilizator : prieteni) {
            if (utilizator.getEmail().equals(emailPrieten))
                return utilizator;
        }
        return null;
    }

    public List<Utilizator> getPrieteni() {
        return prieteni;
    }
}
