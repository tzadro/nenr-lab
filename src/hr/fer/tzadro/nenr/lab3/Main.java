package hr.fer.tzadro.nenr.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int L = 0, D = 0, LK = 0, DK = 0, V = 0, S = 0, akcel, kormilo;
        String line = null;

        // Biramo način dekodiranja neizrazitosti:
        IDefuzzifier def = new COADefuzzifier();

        // Stvaranje oba sustava:
        // Grade se baze pravila i sve se inicijalizira
        IFuzzySystem fsAkcel = new AkcelFuzzySystemMin(def);
        IFuzzySystem fsKormilo = new KormiloFuzzySystemMin(def);

        // Glavna petlja:
        while (true) {
            if ((line = input.readLine()) != null) {
                if (line.charAt(0)=='K')
                    break;

                Scanner s = new Scanner(line);
                L = s.nextInt();
                D = s.nextInt();
                LK = s.nextInt();
                DK = s.nextInt();
                V = s.nextInt();
                S = s.nextInt();
            }

            // Zadaj ulaze, generiraj neizraziti izlaz, dekodiraj i vrati ga:
            akcel = fsAkcel.infer(L, D, LK, DK, V, S);
            kormilo = fsKormilo.infer(L, D, LK, DK, V, S);

            System.out.println(akcel + " " + kormilo);
            System.out.flush();
        }
    }

}


