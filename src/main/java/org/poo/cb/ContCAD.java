package org.poo.cb;

public class ContCAD extends Cont{
    public ContCAD() {
        super(new SchimbCAD());
        this.setNumeValuta("CAD");
    }

    public boolean tipValuta(String valuta) {
        return valuta.equals("CAD");
    }
}
