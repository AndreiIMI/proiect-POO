package org.poo.cb;

public class UtilizatorBuilder {
    private final Utilizator utilizator = new Utilizator();

    public UtilizatorBuilder cuEmail(String email) {
        utilizator.setEmail(email);
        return this;
    }

    public UtilizatorBuilder cuNume(String nume) {
        utilizator.setNume(nume);
        return this;
    }

    public UtilizatorBuilder cuPrenume(String prenume) {
        utilizator.setPrenume(prenume);
        return this;
    }

    public UtilizatorBuilder cuAdresa(String adresa) {
        utilizator.setAdresa(adresa);
        return this;
    }

    public Utilizator build() {
        return utilizator;
    }
}
