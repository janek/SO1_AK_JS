/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so1_ak_js;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Janek
 */
public class Zasob {
    Generator genr = new Generator();
    
    
    public ArrayList zPliku() throws FileNotFoundException {
        System.out.println("Podaj nazwę pliku tekstowego (razem z formatem), z którego chcesz wczytać listę procesów:");
        Scanner scn = new Scanner(System.in);
        genr.scenariuszZPliku(scn.nextLine());
        nadajMomentZgloszenia(genr.procesy);
        return genr.procesy;
    }
    
    public void nadajMomentZgloszenia(ArrayList<Proces> procesy){
        int momentZgloszenia = 0;
        for (Proces p:procesy){
            momentZgloszenia+=p.deltaZgloszenia;
            p.momentZgloszenia = momentZgloszenia;
        }
    }
    
    public double wyliczSredniCzas(ArrayList<Proces> procesy){
        double suma = 0;
        double srednia = 0;
        for (Proces p : procesy){
            suma += p.czasOczekiwania;
            System.out.println("czasOczekiwania = "+p.czasOczekiwania);
        }
        srednia = suma/procesy.size();
        return srednia;
    }
    
    
    public void FCFS (ArrayList<Proces> procesy){
        int zsumowaneFazy = 0;
        procesy.get(0).momentWejscia=procesy.get(0).momentZgloszenia;  //ponieważ  w pętli odnosimy się do poprzedniego elementu dla każdego elementu,
         procesy.get(0).czasOczekiwania = 0;                           //musimy najpierw ustawić ręcznie wartosc dla zerowego    
        for (int i=1; i<procesy.size(); i++){                          //i zacząć pętlę dla pierwszego
            procesy.get(i).momentWejscia=Math.max(((procesy.get(i-1).momentWejscia)+(procesy.get(i-1).dlugoscFazy)),procesy.get(i).momentZgloszenia);
            procesy.get(i).czasOczekiwania=procesy.get(i).momentWejscia-procesy.get(i).momentZgloszenia;
        }
        //zsumowania wyników
        System.out.println(wyliczSredniCzas(procesy)); 
    }
    
    public void SJF (ArrayList<Proces> procesy){
        int obecnyCzas = 0;
        ArrayList<Proces> gotoweProcesy = new ArrayList<Proces>;    
        
    }
    
    
    
}
