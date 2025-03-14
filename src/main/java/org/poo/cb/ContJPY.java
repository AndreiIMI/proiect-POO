package org.poo.cb;

public class ContJPY extends Cont{
    public ContJPY() {
        super(new SchimbJPY());
        this.setNumeValuta("JPY");
    }

    public boolean tipValuta(String valuta) {
        return valuta.equals("JPY");
    }
}
