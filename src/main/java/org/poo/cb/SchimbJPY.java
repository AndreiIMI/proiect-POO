package org.poo.cb;

public class SchimbJPY implements SchimbStrategy{
    @Override
    public double getSumaSchimb(String valutaDestinatie, double sumaSchimb) {
        switch (valutaDestinatie) {
            case "EUR":
                return sumaSchimb * getRateSchimb()[0];
            case "GBP":
                return sumaSchimb * getRateSchimb()[1];
            case "JPY":
                return sumaSchimb;
            case "CAD":
                return sumaSchimb * getRateSchimb()[3];
            case "USD":
                return sumaSchimb * getRateSchimb()[4];
        }
        return 0;
    }

    @Override
    public double[] getRateSchimb() {
        return Platforma.rateSchimb[2];
    }
}
