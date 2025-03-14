package org.poo.cb;

public class SchimbEUR implements SchimbStrategy{
    @Override
    public double getSumaSchimb(String valutaDestinatie, double sumaSchimb) {
        switch (valutaDestinatie) {
            case "EUR":
                return sumaSchimb;
            case "GBP":
                return sumaSchimb * getRateSchimb()[1];
            case "JPY":
                return sumaSchimb * getRateSchimb()[2];
            case "CAD":
                return sumaSchimb * getRateSchimb()[3];
            case "USD":
                return sumaSchimb * getRateSchimb()[4];
        }
        return 0;
    }

    @Override
    public double[] getRateSchimb() {
        return Platforma.rateSchimb[0];
    }
}
