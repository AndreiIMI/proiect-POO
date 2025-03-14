package org.poo.cb;

public class ContUSD extends Cont{
    public ContUSD() {
        super(new SchimbUSD());
        this.setNumeValuta("USD");
    }

    @Override
    public boolean tipValuta(String valuta) {
        return valuta.equals("USD");
    }
}
