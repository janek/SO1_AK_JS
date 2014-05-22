/*
 *Zadanie pierwsze kursu Systemy Operacyjne, 2014 PWr
 * Aleksander Kolbuszewski, Janek Szynal  
 */

package so1_ak_js;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Janek
 */
public class SO1_AK_JS {
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Zasob zas = new Zasob();
        startMenu(zas.zPliku());
       
    }
    
    private static void startMenu(ArrayList<Proces> procesy) throws FileNotFoundException{
            System.out.println("* * *");
            System.out.println("Wybierz naciskajac klawisz cyfry, a nastepnie potwierdź enterem:");
            System.out.println("1 - Wyświetl listę procesów");
            System.out.println("2 - Wczytaj nową listę procesów");
            System.out.println("3 - Przedstaw statystyki");
            System.out.println("4 - Zakończ");

            //MENU
            Scanner scn = new Scanner(System.in);

            switch (scn.nextInt()) {
            case 1: 
                    int momentZgloszenia=0;
                    for (Proces p : procesy){
                        p.wypisz();                      
                    }
                    System.out.println("ilosc procesow = "+procesy.size());
                    startMenu(procesy);
                    break;
            case 2:
                   Zasob zas = new Zasob();
                   startMenu(zas.zPliku());
                    break;

            
            case 3:
                    Zasob zas2 = new Zasob();
                    zas2.FCFS(procesy);
                    startMenu(procesy);
                    break;
            
            case 4:
    
                    break;
            }

        }

    
}
