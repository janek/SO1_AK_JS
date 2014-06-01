/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package so1_ak_js;

import java.io.FileNotFoundException;
import static java.lang.Integer.MAX_VALUE;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/**
 *
 * @author Janek
 */
public class Zasob {
    Generator genr = new Generator();
    ArrayList<Proces> procesy1 = new ArrayList<Proces>();
    ArrayList<Proces> procesy2 = new ArrayList<Proces>();
    
    
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
           // System.out.println("czasOczekiwania = "+p.czasOczekiwania);
        }
        srednia = suma/procesy.size();
        return srednia;
    }
    
    public void runAlgs(ArrayList<Proces> procesy, int kwantCzasu){
        for (Proces p : procesy){
            procesy1.add(p);
            System.out.print("Przelano");
            p.wypisz();
        }
       
       // procesy2 = procesy;
        this.RR(procesy1, kwantCzasu);
        this.FCFS(procesy);
        
    }
    
    public void FCFS (ArrayList<Proces> procesy){
        ArrayList<Proces> backupProcesy = procesy;
        procesy.get(0).momentWejscia=procesy.get(0).momentZgloszenia;  //ponieważ  w pętli odnosimy się do poprzedniego elementu dla każdego elementu,
        procesy.get(0).czasOczekiwania = 0;                           //musimy najpierw ustawić ręcznie wartosc dla zerowego    
        for (int i=1; i<procesy.size(); i++){                          //i zacząć pętlę dla pierwszego
            procesy.get(i).momentWejscia=Math.max(((procesy.get(i-1).momentWejscia)+(procesy.get(i-1).dlugoscFazy)),procesy.get(i).momentZgloszenia);
            procesy.get(i).czasOczekiwania=procesy.get(i).momentWejscia-procesy.get(i).momentZgloszenia;
        }
        //zsumowanie wyników
        System.out.println("Dla algorytmu FIFO: "+wyliczSredniCzas(procesy)); 
        procesy = backupProcesy;
        for (Proces pro : procesy){
            pro.odswiez();
        }
    }
    
    public void SJF (ArrayList<Proces> procesy){
        int obecnyCzas = 0;
        boolean pozostalyNiewykonaneProcesy=true;
        
        while (pozostalyNiewykonaneProcesy){  //dopóki pozostały niewykonane procesy
            
            ArrayList<Proces> gotoweProcesy = new ArrayList<Proces>();

            for (Proces p : procesy){
                //jesli proces zdazyl sie zglosic ORAZ nie zostal jeszcze wykonany
                if (p.momentZgloszenia <= obecnyCzas && p.czyWykonany == false){
                    gotoweProcesy.add(p);
                  //  System.out.println("jest czas"+obecnyCzas+"Dodano proces");
                  //  p.wypisz();
                }
            }
            //jesli dodano proces
            if (!gotoweProcesy.isEmpty()){
                //znajdz najkrotszy z dodanych procesow
                //inicjalizacje
                int najkrotszyProces = MAX_VALUE; //zamienić na int_max
                int indeksNajkrotszego = 0;
                //wyszukiwanie
                for (Proces proc : gotoweProcesy){
                    if (proc.dlugoscFazy<najkrotszyProces){
                         najkrotszyProces = proc.dlugoscFazy;
                         indeksNajkrotszego = proc.nrPorzadkowy-1;
                     }   
                }
                //mamy najkrotszy proces, wykonujemy go
                //nadajemy czas oczekiwania
                procesy.get(indeksNajkrotszego).czasOczekiwania = obecnyCzas-(procesy.get(indeksNajkrotszego).momentZgloszenia);
                procesy.get(indeksNajkrotszego).czyWykonany = true;
                //zwiekszamy obecny czas o czas potrzebny do wykonania procesu
                obecnyCzas+=procesy.get(indeksNajkrotszego).dlugoscFazy;

            }//zamkniecie "jesli dodano proces"
            else {
            //jesli nie dodano procesu, bo zaden nie byl gotowy
            //zwieksz czas o 1
            obecnyCzas++;
            }
            //sprawdz, czy pozostaly niewykonane procesy
            pozostalyNiewykonaneProcesy=false;
            for (Proces p : procesy){
                    if (p.czyWykonany == false){
                       pozostalyNiewykonaneProcesy = true;
                    }    
            }   
        }//koniec while
         System.out.println("Dla algorytmu SJF: "+wyliczSredniCzas(procesy)); 
        for (Proces pro : procesy){
            pro.odswiez();
        }
    }//koniec SJF
     
    public void SJFW (ArrayList<Proces> procesy){
        
        int obecnyCzas = 0;
        boolean pozostalyNiewykonaneProcesy=true;
        
        while (pozostalyNiewykonaneProcesy){ //dopóki pozostały niewykonane procesy
            
            ArrayList<Proces> gotoweProcesy = new ArrayList<Proces>();
            
            for (Proces p : procesy){
                //jesli proces zdazyl sie zglosic ORAZ nie zostal jeszcze wykonany
                if (p.momentZgloszenia <= obecnyCzas && p.czyWykonany == false){
                    gotoweProcesy.add(p);
                    //System.out.println("Dodano proces numer "+p.nrPorzadkowy);
                }
            }
            
            
            if (!gotoweProcesy.isEmpty()){
                //znajdz najkrotszy z dodanych procesow
                //inicjalizacje
                int najkrotszyProces = MAX_VALUE; //zamienić na int_max
                int indeksNajkrotszego = 0;
                //wyszukiwanie najkrótszego
                for (Proces proc : gotoweProcesy){
                    if (proc.dlugoscFazy<najkrotszyProces){
                         najkrotszyProces = proc.dlugoscFazy;
                         indeksNajkrotszego = proc.nrPorzadkowy-1;
                     }   
                }
                
                //wszystkie inne gotowe czekają, więc zwiększyć czas oczekiwania
                for (Proces proc : gotoweProcesy){
                    if (proc.nrPorzadkowy != indeksNajkrotszego+1)
                    {
                        proc.czasOczekiwania+=1;
                    }
                }
                
                //"wykonujemy" proces - zmniejszamy dl fazy o 1
                procesy.get(indeksNajkrotszego).dlugoscFazy-=1;
                //kontrola, czy wszystko dobrze działa
                //System.out.println("Wykonano 1s procesu numer "+procesy.get(indeksNajkrotszego).nrPorzadkowy);
                
                
                //jeśli dł fazy spadła do 0, to znaczy, że proces został wykonany
                if (procesy.get(indeksNajkrotszego).dlugoscFazy==0){
                   procesy.get(indeksNajkrotszego).czyWykonany=true; 
                }

            }//zamkniecie "jesli dodano proces"
            
            
            

            pozostalyNiewykonaneProcesy=false;
            for (Proces p : procesy){
                    if (p.czyWykonany == false){
                       pozostalyNiewykonaneProcesy = true;
                    }    
            }   
            
            obecnyCzas++; //zawsze
        }//koniec while
        System.out.println("Dla algorytmu SJFW: "+wyliczSredniCzas(procesy)); 
        for (Proces pro : procesy){
            pro.odswiez();
        }
    }//koniec SJFW/SRTF
    
    public void RR(ArrayList<Proces> procesy, int kwantCzasu){

        int obecnyCzas = 0;
        boolean pozostalyNiewykonaneProcesy=true;
        int nrOstatnioWykonywanego = 0;
        LinkedList<Proces> gotoweProcesy = new LinkedList<>();
        
        while (pozostalyNiewykonaneProcesy){ //dopóki pozostały niewykonane procesy

            //dodaj do gotowychProcesów wszystkie gotowe procesy
            
            for (Proces p : procesy){
                //jesli proces zdazyl sie zglosic ORAZ nie zostal jeszcze wykonany ORAZ nie jest jeszcze w kolejce
                if (p.momentZgloszenia <= obecnyCzas && p.czyWykonany == false && !gotoweProcesy.contains(p)){
                    gotoweProcesy.offer(p);
                   // System.out.println("Dodano do gotowych proces numer "+p.nrPorzadkowy);
                }
            }

            //wykonanie procesu
            if (!gotoweProcesy.isEmpty()){
                //pobieramy pierwszy proces z gotowych
                Proces p = gotoweProcesy.poll();         
                int trwanieFazy=0;
                if(p.dlugoscFazy <= kwantCzasu){
                    //jeśli dlFazy jest krótsza niż kwant, kończymy proces i 
                    trwanieFazy=p.dlugoscFazy;
                    p.dlugoscFazy = 0;
                    p.czyWykonany = true;
                    //System.out.println("Wykonano "+trwanieFazy+" procesu "+p.nrPorzadkowy);
                } else {

                    trwanieFazy=kwantCzasu;
                    p.dlugoscFazy -=kwantCzasu;
                    //System.out.println("Wykonano "+trwanieFazy+" procesu "+p.nrPorzadkowy);
                    //gotoweProcesy.offer(p);
                    //System.out.println("Dodano do gotowych proces numer "+p.nrPorzadkowy);
                }
                
                
                //nadajemy czas oczekiwania innym procesom
                for (Proces proc : gotoweProcesy){
                    if (proc.nrPorzadkowy != p.nrPorzadkowy)
                    {
                        proc.czasOczekiwania+=trwanieFazy;
                    }
                }
                
                //zwiekszamy obecny czas
                obecnyCzas+=trwanieFazy;
                

            }//zamkniecie "jesli dodano proces"
            else {
            //jesli nie dodano procesu, bo zaden nie byl gotowy
            //zwieksz czas o 1
            obecnyCzas++;
            }
            
            pozostalyNiewykonaneProcesy=false;
            for (Proces p : procesy){
                    if (p.czyWykonany == false){
                       pozostalyNiewykonaneProcesy = true;
                    }    
            }   
            
        }//koniec while
        System.out.println("Dla algorytmu RR: "+wyliczSredniCzas(procesy)); 
        for (Proces pro : procesy){
            pro.odswiez();
        }
        
        
    }//koniec RR
    
}
