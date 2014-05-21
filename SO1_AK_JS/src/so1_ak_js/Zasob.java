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
    
    
    ArrayList zPliku() throws FileNotFoundException {
        System.out.println("Podaj nazwę pliku tekstowego (razem z formatem), z którego chcesz wczytać listę procesów:");
        Scanner scn = new Scanner(System.in);
        genr.scenariuszZPliku(scn.nextLine());
        return genr.procesy;
    }
    
}
