/*
 *Zadanie pierwsze kursu Systemy Operacyjne, 2014 PWr
 * Aleksander Kolbuszewski, Janek Szynal  
 */

package so1_ak_js;

/**
 *
 * @author Janek
 */
public class Proces {
    
    int nrPorzadkowy = 0;
    int dlugoscFazy = 0;
    int deltaZgloszenia = 0;   //UWAGA: Czy nie zamienić na opóźnienie od ostatniego?
    int czasOczekiwania = 0;
    
    Proces(int nrPorzadkowy, int dlugoscFazy, int deltaZgloszenia){
        this.nrPorzadkowy = nrPorzadkowy;
        this.dlugoscFazy = dlugoscFazy;
        this.deltaZgloszenia = deltaZgloszenia;
    }
    
    public void wypisz(){
        System.out.print("Proces nr "+nrPorzadkowy+", długość fazy: "+dlugoscFazy+", moment zgłoszenia: ");
    }
    
}
