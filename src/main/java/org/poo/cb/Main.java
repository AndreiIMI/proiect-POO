package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        if(args == null) {
            System.out.println("Running Main");
            return;
        }

        Platforma platforma = Platforma.primesteInstanta();

        try {
            platforma.setRateSchimb("src/main/resources/" + args[0]);
            BufferedReader br = new BufferedReader(new FileReader(("src/main/resources/" + args[2])));
            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] argumente = line.split(" ");
                String comenzi = argumente[0] + " " + argumente[1];
                switch (comenzi) {
                    case "CREATE USER":
                        StringBuilder address = new StringBuilder();
                        for (int i = 5; i < argumente.length - 1; i++)
                            address.append(argumente[i]).append(" ");
                        address.append(argumente[argumente.length - 1]);

                        platforma.creareUtilizator(argumente[2], argumente[3], argumente[4], String.valueOf(address));
                        break;
                    case "ADD FRIEND":
                        platforma.adaugaPrieten(argumente[2], argumente[3]);
                        break;
                    case "ADD ACCOUNT":
                        platforma.adaugaCont(argumente[2], ContFactory.TipValuta.valueOf(argumente[3]));
                        break;
                    case "ADD MONEY":
                        platforma.alimentareCont(argumente[2], argumente[3], Double.parseDouble(argumente[4]));
                        break;
                    case "EXCHANGE MONEY":
                        platforma.schimbValutar(argumente[2], argumente[3], argumente[4],
                                Double.parseDouble(argumente[5]));
                        break;
                    case "TRANSFER MONEY":
                        platforma.transferBani(argumente[2], argumente[3], argumente[4],
                                Double.parseDouble(argumente[5]));
                        break;
                    case "LIST USER":
                        platforma.listeazaUser(argumente[2]);
                        break;
                    case "LIST PORTFOLIO":
                        platforma.listeazaPortofoliu(argumente[2]);
                        break;
                    case "RECOMMEND STOCKS":
                        platforma.recomandaActiuni("src/main/resources/" + args[1]);
                        break;
                    case "BUY STOCKS":
                        platforma.cumparareActiuni(argumente[2], argumente[3], Integer.parseInt(argumente[4]),
                                "src/main/resources/" + args[1]);
                }
            }
        } catch (IOException ex) {
            System.out.println("Exceptie IO");
        }

        platforma.stergePlatforma();

    }
}