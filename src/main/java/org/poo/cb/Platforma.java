package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Platforma {
    private static Platforma platformaInstanta;
    private final List<Utilizator> utilizatoriPlatforma = new LinkedList<>();
    static double[][] rateSchimb = new double[5][5];

    private Platforma() {}

    public void stergePlatforma() {
        platformaInstanta = null;
    }

    public void setRateSchimb(String fisierRate) throws IOException {
        double[][] rate = new double[5][5];
        BufferedReader br = new BufferedReader(new FileReader((fisierRate)));
        br.readLine();
        String line;
        int valutaCount = 0;
        while ((line = br.readLine()) != null) {
            String[] argumente = line.split(",");
            for (int i = 1; i < 6; i++) {
                rate[valutaCount][i - 1] = Double.parseDouble(argumente[i]);
            }
            valutaCount++;
        }
        rateSchimb = rate;
    }

    public static Platforma primesteInstanta() {
        if (platformaInstanta == null)
            platformaInstanta = new Platforma();

        return platformaInstanta;
    }

    public boolean existaUtilizator(String email) {
        for (Utilizator utilizator : utilizatoriPlatforma) {
            if (utilizator.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Utilizator cautaUtilizator(String email) {
        for (Utilizator utilizator : utilizatoriPlatforma) {
            if (utilizator.getEmail().equals(email)) {
                return utilizator;
            }
        }
        return null;
    }

    public void creareUtilizator(String email, String prenume, String nume, String adresa) {
        if (existaUtilizator(email)) {
            System.out.println("User with " + email + " already exists");
        } else {
            Utilizator utilizator = new UtilizatorBuilder().cuEmail(email).cuNume(nume).cuPrenume(prenume).
                    cuAdresa(adresa).build();
            utilizatoriPlatforma.add(utilizator);
        }
    }

    public void adaugaPrieten(String emailUtilizator, String emailPrieten) {
        Utilizator utilizatorCautat = null, prietenCautat = null;
        for (Utilizator utilizator : utilizatoriPlatforma) {
            if (utilizator.getEmail().equals(emailUtilizator))
                utilizatorCautat = utilizator;
            if (utilizator.getEmail().equals(emailPrieten))
                prietenCautat = utilizator;
        }

        if (prietenCautat == null) {
            System.out.println("User with " + emailPrieten + " doesn't exist");
            return;
        }

        if (utilizatorCautat == null) {
            System.out.println("User with " + emailUtilizator + " doesn't exist");
            return;
        }

        utilizatorCautat.adaugaPrieten(prietenCautat);
        prietenCautat.adaugaPrieten(utilizatorCautat);

    }

    public void adaugaCont(String email, ContFactory.TipValuta valuta) {
        Utilizator utilizator = cautaUtilizator(email);

        if (utilizator.getPortofoliu().existaCont(String.valueOf(valuta))) {
            System.out.println("Account in currency " + valuta + " already exists for user");
            return;
        }

        ContFactory factory = new ContFactory();
        Cont cont = factory.creareCont(valuta);

        utilizator.getPortofoliu().getConturi().add(cont);
    }

    public void alimentareCont(String email, String valuta, double suma) {
        Utilizator utilizator = cautaUtilizator(email);
        utilizator.getPortofoliu().cautareCont(valuta).alimentareCont(suma);
    }

    public void schimbValutar(String email, String contSursa, String contDestinatie, double sumaSchimb) {
        Utilizator utilizator = cautaUtilizator(email);
        Cont contUtilizator = utilizator.getPortofoliu().cautareCont(contSursa);
        Cont contUtilizatorDestinatie = utilizator.getPortofoliu().cautareCont(contDestinatie);

        double valoareSchimb = contUtilizatorDestinatie.getSchimbStrategy().getSumaSchimb(contSursa, sumaSchimb);

        if (contUtilizator.getSuma() < valoareSchimb) {
            System.out.println("Insufficient amount in account " + contSursa + " for exchange");
            return;
        }

        if (contUtilizator.getSuma() < valoareSchimb * 2) {
            contUtilizator.setSuma(contUtilizator.getSuma() - valoareSchimb * 1.01);
        } else {
            contUtilizator.setSuma(contUtilizator.getSuma() - valoareSchimb);
        }

        contUtilizatorDestinatie.setSuma(contUtilizatorDestinatie.getSuma() + sumaSchimb);
    }

    public void transferBani(String email, String emailPrieten, String valuta, double sumaTransfer) {
        Utilizator utilizator = cautaUtilizator(email);
        Utilizator prieten = utilizator.cautaPrieten(emailPrieten);

        if (prieten != null) {
            if (utilizator.getPortofoliu().cautareCont(valuta).getSuma() < sumaTransfer) {
                System.out.println("Insufficient amount in account " + valuta + " for transfer");
                return;
            }

            Cont contUtilizator = utilizator.getPortofoliu().cautareCont(valuta);
            Cont contPrieten = prieten.getPortofoliu().cautareCont(valuta);

            contUtilizator.setSuma(contUtilizator.getSuma() - sumaTransfer);
            contPrieten.setSuma(contPrieten.getSuma() + sumaTransfer);
        } else {
            System.out.println("You are not allowed to transfer money to " + emailPrieten);
        }

    }

    public void listeazaUser(String email) {
        Utilizator utilizator = cautaUtilizator(email);
        if (utilizator != null) {
            System.out.print("{\"email\":\"" + utilizator.getEmail() + "\",\"firstname\":\"" +
                    utilizator.getPrenume() + "\",\"lastname\":\"" + utilizator.getNume() + "\",\"address\":\"" +
                    utilizator.getAdresa() + "\",\"friends\":[");
            for (int i = 0; i < utilizator.getPrieteni().size(); i++) {
                System.out.print("\"" + utilizator.getPrieteni().get(i).getEmail() + "\"");
                if (i < utilizator.getPrieteni().size() - 1)
                    System.out.print(",");
            }
            System.out.println("]}");
        } else {
            System.out.println("User with " + email + " doesn't exist");
        }
    }

    public void listeazaPortofoliu(String email) {
        Utilizator utilizator = cautaUtilizator(email);
        if (utilizator != null) {
            System.out.print("{\"stocks\":[");
            for (int i = 0; i < utilizator.getPortofoliu().getActiuni().size(); i++) {
                System.out.print("{\"stockName\":\"" +
                        utilizator.getPortofoliu().getActiuni().get(i).getNumeCompanie() + "\",\"amount\":" +
                        utilizator.getPortofoliu().getActiuni().get(i).getNumarActiuni() + "}");
                if (i < utilizator.getPortofoliu().getActiuni().size() - 1)
                    System.out.print(",");
            }
            System.out.print("],\"accounts\":[");
            for (int i = 0; i < utilizator.getPortofoliu().getConturi().size(); i++) {
                String sumaFormatata = String.format("%.2f", utilizator.getPortofoliu().getConturi().get(i).getSuma());
                System.out.print("{\"currencyName\":\"" +
                        utilizator.getPortofoliu().getConturi().get(i).getNumeValuta() + "\",\"amount\":\"" +
                        sumaFormatata + "\"}");
                if (i < utilizator.getPortofoliu().getConturi().size() - 1)
                    System.out.print(",");
            }
            System.out.println("]}");
        } else {
            System.out.println("User with " + email + " doesn't exist");
        }
    }

    public void recomandaActiuni(String fisierActiuni) throws IOException {
        System.out.print("{\"stocksToBuy\":[");
        BufferedReader br = new BufferedReader(new FileReader((fisierActiuni)));
        br.readLine();
        String line;
        int primaActiune = 0;
        while ((line = br.readLine()) != null) {
            String[] argumente = line.split(",");
            String numeCompanie = argumente[0];
            double medieTermenLung = 0;
            double medieTermenScurt = 0;
            for (int i = 1; i < argumente.length; i++) {
                medieTermenLung += Double.parseDouble(argumente[i]);
                if (i > 5)
                    medieTermenScurt += Double.parseDouble(argumente[i]);
            }
            medieTermenLung /= 10;
            medieTermenScurt /= 5;
            if (medieTermenScurt > medieTermenLung) {
                if (primaActiune == 1)
                    System.out.print(",");
                System.out.print("\"" + numeCompanie + "\"");
                primaActiune = 1;
            }

        }
        System.out.println("]}");
    }

    public void cumparareActiuni(String email, String companie, int numarActiuni, String fisierActiuni) throws IOException {
        Utilizator utilizator = cautaUtilizator(email);
        BufferedReader br = new BufferedReader(new FileReader((fisierActiuni)));
        br.readLine();
        String line;
        int primaActiune = 0;
        while ((line = br.readLine()) != null) {
            String[] argumente = line.split(",");
            String numeCompanie = argumente[0];
            if (numeCompanie.equals(companie)) {
                Actiuni actiuneCautata = utilizator.getPortofoliu().cautareActiune(numeCompanie);
                if (actiuneCautata == null) {
                    actiuneCautata = new Actiuni(companie);
                    for (int i = 1; i < argumente.length; i++)
                        actiuneCautata.getUltimeleValori().add(Double.parseDouble(argumente[i]));
                    primaActiune = 1;
                }
                if (utilizator.getPortofoliu().cautareCont("USD").getSuma() >=
                        actiuneCautata.getUltimeleValori().getLast() * numarActiuni) {
                    if (primaActiune == 1)
                        utilizator.getPortofoliu().getActiuni().add(actiuneCautata);
                    actiuneCautata.adaugaActiuni(numarActiuni);
                    utilizator.getPortofoliu().cautareCont("USD").
                            extragereCont(actiuneCautata.getUltimeleValori().getLast() * numarActiuni);
                } else {
                    System.out.println("Insufficient amount in account for buying stock");
                }
            }
        }
    }
}
