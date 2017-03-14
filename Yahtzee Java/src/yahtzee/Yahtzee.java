/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author crisfast95
 */
public class Yahtzee extends JFrame implements ActionListener {

    JPanel panouZar, panouDat, panouJoc;
    JPanel coloana1, coloana2;
    JButton aruncaZarB;
    JButton nrTure;
    JButton nrAruncari;
    JButton butonScor[];
    JTextField campScor[];
    JLabel gol;

    int nrZaruri = 5;
    Jucator[] jucatori;
    int randulCui;
    Zar[] zar;
    String numeCat[] = {"Asi", "Doiari", "Treiari", "Patrari", "Cinciari", "Sesari",
        "3 la fel", "4 la fel", "Full House", "1-5", "2-6",
        "Yahtzee!", "Sansa", "Subtotal", "Bonus", "Subtotal", "Scor final"};
    //aparitii pentru 3 de un fel full house etc
    int aparitii[] = {0, 0, 0, 0, 0, 0};
    int joacaIar;

    public static void main(String[] args) {
        new Yahtzee();
    }

    public Yahtzee() {
        pregatesteJoc();

        //cream butoane
        butonScor = new JButton[numeCat.length];
        campScor = new JTextField[numeCat.length];

        //construim tabla de joc
        setTitle("Yahtzee - Proiect PAO");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setSize(new Dimension(500, 500));
        setResizable(false);

        //configuram panoul cu zaruri
        panouZar = new JPanel();
        //forma panoului o linie 5 coloane
        panouZar.setLayout(new GridLayout(1, 5));
        panouZar.setBackground(getBackground());
        for (int i = 0; i < nrZaruri; i++) {
            zar[i] = new Zar(true);
            panouZar.add(zar[i]);
        }
        //adaugam panoul cu zaruri la tabla noastra
        add(panouZar, BorderLayout.NORTH);

        //cream si adaugam tabela cu scoruri
        panouJoc = new JPanel();
        panouJoc.setLayout(new GridLayout(1, 2));
        panouJoc.setBackground(getBackground());
        panouJoc.setBorder(new TitledBorder("Jucatorul curent: " + jucatori[randulCui].nume));
        add(panouJoc, BorderLayout.CENTER);

        //punem informatii in tabelul cu puncte
        consTabel();

        //adaugam buton de dat cu zarul,nr de aruncari disponibile si nr de ture
        panouDat = new JPanel();
        panouDat.setLayout(new GridLayout(1, 3));
        panouDat.setBackground(getBackground());
        panouDat.setPreferredSize(new Dimension(500, 50));

        aruncaZarB = new JButton("Da cu zarul!");
        aruncaZarB.addActionListener(this);
        panouDat.add(aruncaZarB);

        nrTure = new JButton("Ture ramase:" + jucatori[randulCui].tureram);
        nrTure.setEnabled(false);
        panouDat.add(nrTure);

        nrAruncari = new JButton("Aruncari ramase:" + jucatori[randulCui].aruncari);
        nrAruncari.setEnabled(false);
        panouDat.add(nrAruncari);

        add(panouDat, BorderLayout.SOUTH);
        //facem fereastra vizibila
        setVisible(true);
    }

    private void pregatesteJoc() {
        int nrJuc = Integer.parseInt(JOptionPane.showInputDialog(null, "Numar jucatori: "));
        jucatori = new Jucator[nrJuc];
        for (int i = 0; i < jucatori.length; i++) {
            String nume = JOptionPane.showInputDialog(null, "Numele Jucatorului " + (i + 1) + ":");
            jucatori[i] = new Jucator(nume, numeCat);

            zar = new Zar[nrZaruri];
            randulCui = 0;
            repaint();
        }
    }

    private void consTabel() {
        coloana1 = new JPanel();
        coloana1.setBackground(Color.white);
        coloana1.setLayout(new GridLayout(9, 3));
        panouJoc.add(coloana1);

        coloana2 = new JPanel();
        coloana2.setBackground(Color.white);
        coloana2.setLayout(new GridLayout(9, 3));
        panouJoc.add(coloana2);
        gol = new JLabel(" ");

        //aranjare tabel pe coloane
        for (int i = 0; i < butonScor.length; i++) {
            if (i < 6) {
                butonScor[i] = creareButon(numeCat[i], coloana1, true);
                campScor[i] = creareCamp(coloana1);
            } else if (i < 13) {
                butonScor[i] = creareButon(numeCat[i], coloana2, true);
                campScor[i] = creareCamp(coloana2);
            } else if (i < 15) {
                butonScor[i] = creareButon(numeCat[i], coloana1, false);
                campScor[i] = creareCamp(coloana1);
            } else {
                butonScor[i] = creareButon(numeCat[i], coloana2, false);
                campScor[i] = creareCamp(coloana2);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aruncaZarB && jucatori[randulCui].aruncari != 0 && jucatori[randulCui].tureram != 0) {
            if (aruncaZarB.getText().contains("!")) {
                for (int i = 0; i < nrZaruri; i++) {
                    if (jucatori[randulCui].aruncari == 3) {
                        zar[i].pastreaza = false;
                    }
                    zar[i].da_cu_zaru();
                }
                jucatori[randulCui].aruncari--;
                nrAruncari.setText("Aruncari ramase: " + jucatori[randulCui].aruncari);
                if (jucatori[randulCui].aruncari == 0) {
                    for (int i = 0; i < 13; i++) {
                        if (jucatori[randulCui].categorii[i].folosit != true) {
                            alegere(i, false);
                        }
                    }
                }
                repaint();
            } else {
                schimbaJucator();
            }
        }
        for (int i = 0; i < 13; i++) {
            if (e.getSource() == butonScor[i] && !jucatori[randulCui].categorii[i].folosit) {
                alegere(i, true);
                afisareScor();
                panouJoc.validate();
                resetareZaruri();
            }
        }
    }

    private JButton creareButon(String nume, JPanel col, boolean editare) {
        JButton jb = new JButton(nume);
        jb.setEnabled(editare);
        jb.addActionListener(this);
        col.add(jb);
        return jb;
    }

    private JTextField creareCamp(JPanel col) {
        JTextField jtf = new JTextField();
        jtf.setEditable(false);
        jtf.setBackground(Color.white);
        col.add(jtf);
        return jtf;
    }

    private void alegere(int i, boolean click) {
        if (i < 6) {
            sumaNum(i + 1, click);
        } else if (i == 6) {
            laFel(3, 6, click);
        } else if (i == 7) {
            laFel(4, 7, click);
        } else if (i == 8) {
            fullHouse(8, click);
        } else if (i == 9) {
            grup(9, click);
        } else if (i == 10) {
            grup(10, click);
        } else if (i == 11) {
            yahtzee(11, click);
        } else if (i == 12) {
            int scor = sumZar();
            afisareRasp(12, scor, click, false);
        }
    }

    private void schimbaJucator() {
        aruncaZarB.setText("Da cu zarul!");
        randulCui++;
        if (randulCui >= jucatori.length) {
            randulCui = 0;
        }
        panouJoc.setBorder(new TitledBorder(jucatori[randulCui].nume));
        afisareScor();
    }

    private void afisareScor() {
        for (int i = 0; i < campScor.length; i++) {
            int val = jucatori[randulCui].categorii[i].valoare;
            if ( i < 13) {
                //campScor[i].setText(" ");
        
                campScor[i].setText(" " + jucatori[randulCui].categorii[i].valoare);
                campScor[i].update(getGraphics());
            }
        }
    }

    private void resetareZaruri() {
        for (int i = 0; i < nrZaruri; i++) {
            zar[i].pastreaza = true;
            zar[i].fata = 0;
            jucatori[randulCui].aruncari = 3;
            nrAruncari.setText("Aruncari ramase: " + jucatori[randulCui].aruncari);
            zar[i].repaint();
        }
        jucatori[randulCui].tureram--;
        nrTure.setText("Ture ramase: " + jucatori[randulCui].tureram);

        for (int j = 0; j < aparitii.length; j++) {
            aparitii[j] = 0;
        }
        for (int i = 0; i < 13; i++) {
            campScor[i].setBackground(Color.WHITE);
        }
        //verificare final de joc
        int sfarsit = 0;
        for (int i = 0; i < jucatori.length; i++) {
            if (jucatori[i].tureram == 0) {
                sfarsit++;
            }
        }
        if (sfarsit == jucatori.length) {
            int castigator = cineCastiga();
            randulCui = castigator;
            JOptionPane.showMessageDialog(null, jucatori[randulCui].nume + " a castigat cu scorul de " + jucatori[randulCui].categorii[16].valoare);
            panouJoc.setBorder(new TitledBorder(jucatori[randulCui].nume));
            afisareScor();
            //cream alegeri pentru "Joaca iar"
            String[] alegeri = {"Da", "Nu"};
            campScor[campScor.length - 1].setBackground(getBackground());
            joacaIar = JOptionPane.showOptionDialog(null, "Doriti sa jucati dinou?", "Meci nou?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, alegeri, alegeri[1]);
            if (joacaIar == 0) {
                resetareJoc();
            } else {
                System.exit(0);
            }
        }
        int urmatorul = randulCui + 1;
        if (urmatorul >= jucatori.length) {
            urmatorul = 0;
        }
        aruncaZarB.setText("Urmatorul jucator: " + jucatori[urmatorul].nume);
    }

    private void sumaNum(int fata, boolean click) {
        int scor = 0;
        for (int i = 0; i < nrZaruri; i++) {
            if (zar[i].fata == fata) {
                scor = scor + fata;
            }
        }
        afisareRasp(fata - 1, scor, click, true);
    }

    private void laFel(int cate, int i, boolean click) {
        int scor = 0;
        int nrPeZar = 0;
        aparitiiZar();
        boolean ok = false;
        for (int x = 0; x < 6; x++) {
            if (aparitii[x] >= cate) {
                ok = true;
                nrPeZar = x+1;
            }
        }
        if (ok == true) {
            scor = nrPeZar * cate;
        }
        afisareRasp(i, scor, click, false);
    }

    private void fullHouse(int i, boolean click) {
        int scor = 0;
        aparitiiZar();
        boolean ok1 = false;
        boolean ok2 = false;

        for (int x = 0; x < 6; x++) {
            if (aparitii[x] == 3) {
                ok1 = true;
            }
            if (aparitii[x] == 2) {
                ok2 = true;
            }
        }

        if (ok1 == true && ok2 == true) {
            scor = 25;
        }
        afisareRasp(i, scor, click, false);
    }

    private void grup(int i, boolean click) {
        int scor = 0;
        boolean ok = false;

        aparitiiZar();
        if (i == 9) {

            if (aparitii[0] == 1 && aparitii[1] == 1 && aparitii[2] == 1 && aparitii[3] == 1 && aparitii[4] == 1) {
                ok = true;
            }

            if (ok == true) {
                scor = 30;
            }
        } else if (i == 10) {

            if (aparitii[1] == 1 && aparitii[2] == 1 && aparitii[3] == 1 && aparitii[4] == 1 && aparitii[5] == 1) {
                ok = true;
            }

            if (ok == true) {
                scor = 40;
            }
        }
        afisareRasp(i, scor, click, false);
    }

    private void yahtzee(int i, boolean click) {
        int scor = 0;
        boolean ok = false;

        for (int x = 0; x < 6; x++) {
            if (aparitii[x] == 5) {
                ok = true;
            }
        }
        if (ok == true) {
            scor = 50;
        }
        afisareRasp(i, scor, click, false);
    }

    private int sumZar() {
        int scor = 0;
        for (int i = 0; i < nrZaruri; i++) {
            scor = scor + zar[i].fata;
        }
        return scor;
    }

    private void afisareRasp(int i, int scor, boolean click, boolean b) {
        if (click) {
            jucatori[randulCui].categorii[i].valoare = scor;
            campScor[i].setBackground(Color.white);
            campScor[i].setText(" " + scor);
            jucatori[randulCui].categorii[i].folosit = true;
            peStanga(scor, b);
        } else if (scor >= 0) {
            campScor[i].setBackground(Color.GRAY);
            campScor[i].setText(" " + scor);
        }
        //resetam aparitiile
        for (int j = 0; j < aparitii.length; j++) {
            aparitii[j] = 0;
        }
    }

    private void aparitiiZar() {
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < nrZaruri; j++) {
                if (zar[j].fata == i) {
                    aparitii[i - 1]++;
                }
            }
        }
    }

    private void peStanga(int scor, boolean b) {
        if (b) {
            int lsubtot = jucatori[randulCui].calcSubtot(scor, 13);
            campScor[13].setText(" " + lsubtot);

            if (lsubtot > 63) {
                jucatori[randulCui].categorii[14].valoare = 35;
            }
            campScor[14].setText(" " + jucatori[randulCui].categorii[14].valoare);
        } else {
            int rsubtot = jucatori[randulCui].calcSubtot(scor, 15);
            campScor[15].setText(" " + rsubtot);
        }
        campScor[16].setText(" " + jucatori[randulCui].totalFin());
    }

    private int cineCastiga() {
        int maxV, maxJ;
        maxV = jucatori[0].categorii[16].valoare;
        maxJ = 0;
        for (int i = 1; i < jucatori.length; i++) {
            System.out.println(jucatori[i].nume + " are scorul " + jucatori[i].categorii[16].valoare);
            if (jucatori[i].categorii[16].valoare > maxV) {
                maxJ = i;
            }
        }
        return maxJ;
    }

    private void resetareJoc() {
        jucatori = null;
        new Yahtzee();
    }

}
