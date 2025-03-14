package org.poo.cb;

public class ContGBP extends Cont{
    public ContGBP() {
        super(new SchimbGBP());
        this.setNumeValuta("GBP");
    }

    public boolean tipValuta(String valuta) {
        return valuta.equals("GBP");
    }
}
