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
    int dlugoscFazy = 0;        //liczba jedn. czasu potrzebnych na przetworzenie procesu
    int deltaZgloszenia = 0;    //czas, ktory mija od zgloszenia sie poprzedniego procesu
    int momentZgloszenia = 0;   //moment, w ktotym proces pojawia sie na liscie
    int momentWejscia = 0;    //moment, w ktorym procesor przyjmuje proces
    int czasOczekiwania = 0;    //czas, jaki proces musi odczekac do rozpoczecia sie jego przetwarzania
    boolean czyWykonany = false; //do SJF
    
    Proces(int nrPorzadkowy, int dlugoscFazy, int deltaZgloszenia){
        this.nrPorzadkowy = nrPorzadkowy;
        this.dlugoscFazy = dlugoscFazy;
        this.deltaZgloszenia = deltaZgloszenia;
    }
    
    public void wypisz(){
        System.out.println("Proces nr "+nrPorzadkowy+", długość fazy: "+dlugoscFazy+", moment zgłoszenia: "+momentZgloszenia);
    }
    
}
