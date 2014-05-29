/*
 *Zadanie pierwsze kursu Systemy Operacyjne, 2014 PWr
 * Aleksander Kolbuszewski, Janek Szynal  
 */

package so1_ak_js;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Janek
 */
public class Generator {
    ArrayList<Proces> procesy = new ArrayList<Proces>();
    
    /*metoda wczytuje plik o nazwie przekazanej parametrem
    i interpretuje kolejne linijki, 
    wywołujac odpowiednio metody dodajProces lub generujProcesy*/
    void scenariuszZPliku(String nazwa) throws FileNotFoundException{
        System.out.println("wczytywanie z "+nazwa);
        Scanner odczyt = new Scanner(new File(nazwa));
        while(odczyt.hasNextLine()){
            String linia = odczyt.nextLine();
            String[] wartosci = linia.split(",");
            switch (wartosci[0]) {
                case "ADD":
                    dodajProces(Integer.parseInt(wartosci[1]),Integer.parseInt(wartosci[2]));
                    break;
                case "GEN":
                    generujProcesy(Integer.parseInt(wartosci[1]),Integer.parseInt(wartosci[2]),Integer.parseInt(wartosci[3]));
                    break;
            }
         }
    }//scenariuszZPliku
    
    /* tworzy nowy proces i dodaje do listy*/
    void dodajProces(int dlugoscFazy, int deltaZgloszenia){
        procesy.add(new Proces(procesy.size()+1, dlugoscFazy, deltaZgloszenia));
    }
    
    /*generuje zadana ilosc procesow i dodaje do listy */
    void generujProcesy(int liczbaProcesow, int maxDlugoscFazy, int maxDeltaZgl) {//czy powinny byc inne parametry?
        for (int i = 0; i<liczbaProcesow; i++){
            //stworz jeden proces
            Random rand = new Random(); 
            int dlFazy = rand.nextInt(maxDlugoscFazy)+1;
            Random rand2 = new Random(); 
            int momZgl = rand2.nextInt(maxDeltaZgl);
            dodajProces(dlFazy, momZgl);
        }
     
    }
    
}
