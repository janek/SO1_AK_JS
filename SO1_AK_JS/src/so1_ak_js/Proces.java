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
    int momentZgloszenia = 0;
    int czasOczekiwania = 0;
    
    Proces(int dlugoscFazy, int momentZgloszenia){
        this.dlugoscFazy = dlugoscFazy;
        this.momentZgloszenia = momentZgloszenia;
        this.czasOczekiwania = 0;
    }
    
}
