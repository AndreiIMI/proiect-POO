package org.poo.cb;

public class ContEUR extends Cont{
    public ContEUR() {
        super(new SchimbEUR());
        this.setNumeValuta("EUR");
    }

    public boolean tipValuta(String valuta) {
        return valuta.equals("EUR");
    }
}
