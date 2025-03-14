package org.poo.cb;

public class ContFactory {
    public enum TipValuta {
        USD, EUR, GBP, JPY, CAD
    }

    public Cont creareCont(TipValuta valuta) {
        switch (valuta) {
            case USD:
                return new ContUSD();
            case EUR:
                return new ContEUR();
            case GBP:
                return new ContGBP();
            case JPY:
                return new ContJPY();
            case CAD:
                return new ContCAD();
        }
        return null;
    }
}
