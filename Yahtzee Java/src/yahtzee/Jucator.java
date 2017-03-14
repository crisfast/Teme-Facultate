/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee;

/**
 *
 * @author crisfast95
 */
public class Jucator {

    String nume;
    int tureram, aruncari;
    String[] numeCat;
    Scor categorii[];
    int subtotalsus = 0, subtotaljos = 0, total = 0, bonus = 0;
    boolean catfol[];

    public Jucator(String numeJuc, String[] numeCategorii) {
        nume = numeJuc;
        numeCat = numeCategorii;
        tureram = 13;
        aruncari = 3;

        categorii = new Scor[numeCat.length];
        for (int i = 0; i < categorii.length; i++) {
            categorii[i] = new Scor(numeCat[i]);
        }

        //initializare sume + bonusuri
        categorii[13].valoare = 0;
        categorii[14].valoare = 0;
        categorii[15].valoare = 0;
        categorii[16].valoare = 0;
    }

    public int calcSubtot(int scor, int poz) {
        categorii[poz].valoare = categorii[poz].valoare + scor;
        return categorii[poz].valoare;
    }

    public int totalFin() {
        int scor = 0;
        for (int i = 13; i < 16; i++) {
            scor = scor + categorii[i].valoare;
        }
        categorii[16].valoare = scor;
        return categorii[16].valoare; 
    }
}
