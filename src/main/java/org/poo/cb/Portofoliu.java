package org.poo.cb;

import java.util.LinkedList;
import java.util.List;

public class Portofoliu {
    private final List<Cont> conturi = new LinkedList<>();
    private final List<Actiuni> actiuni = new LinkedList<>();

    public boolean existaCont(String valuta) {
        for (Cont cont : conturi) {
            if (cont.tipValuta(valuta))
                return true;
        }
        return false;
    }

    public Cont cautareCont(String valuta) {
        for (Cont cont : conturi) {
            if (cont.tipValuta(valuta))
                return cont;
        }
        return null;
    }

    public List<Cont> getConturi() {
        return conturi;
    }

    public List<Actiuni> getActiuni() {
        return actiuni;
    }

    public Actiuni cautareActiune(String numeCompanie) {
        for (Actiuni actiune : actiuni) {
            if (actiune.getNumeCompanie().equals(numeCompanie))
                return actiune;
        }
        return null;
    }
}
