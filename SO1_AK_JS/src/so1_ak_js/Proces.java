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
    
//       możliwa reprezentacja procesu: rekord 
//       (numer, dł.fazy procesora, moment zgłoszenia się, czas oczekiwania /początkowo równy 0/...) 
    int nrPorzadkowy;
    int dlugoscFazy;
    int momentZgloszenia;
    int czasOczekiwania = 0;
    
    Proces(int dlugoscFazy, int momentZgloszenia){
        this.dlugoscFazy = dlugoscFazy;
        this.momentZgloszenia = momentZgloszenia;
        this.czasOczekiwania = 0;
    }
    
}
