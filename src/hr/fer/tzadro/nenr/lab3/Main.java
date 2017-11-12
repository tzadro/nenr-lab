package hr.fer.tzadro.nenr.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
        String output;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int L = 0, D = 0, LK = 0, DK = 0, V = 0, S = 0, akcel, kormilo, positionOffset, trailOffset;
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

            // Preformuliranje ulaza
            positionOffset = (int) (-10 + (1. * L) / (L + D) * 20); // [-10, 10]
            trailOffset = (int) (-10 + (1. * LK) / (LK + DK) * 20); // [-10, 10]

            // Zadaj ulaze, generiraj neizraziti izlaz, dekodiraj i vrati ga:
            akcel = fsAkcel.infer(positionOffset, trailOffset, V, S, writer);
            kormilo = fsKormilo.infer(positionOffset, trailOffset, V, S, writer);

            output = akcel + " " + kormilo;
            writer.println(output);
            System.out.println(akcel + " " + kormilo);

            writer.flush();
            System.out.flush();
        }
        writer.println("Checkpoint");
        writer.flush();

        writer.close();
    }
}


