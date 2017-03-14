/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

/**
 *
 * @author crisfast95
 */
public class Zar extends JButton implements MouseListener {

    int fata;
    boolean pastreaza;

    public Zar(boolean p) {
        pastreaza = p;

        //setam marimea zarului
        setPreferredSize(new Dimension(100, 100));
        //facem butonul disponibil
        setEnabled(true);
        //adaugam interactiune cu mouseul
        addMouseListener(this);
        //update zar
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pastreaza = !pastreaza;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void da_cu_zaru() {
        if (!pastreaza) {
            //genereaza un numar random de la 1-6
            fata = (int) (Math.random() * 6 + 1);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (pastreaza) {
            setEnabled(false);
            setOpaque(true);
        } else {
            setEnabled(true);
            setOpaque(false);
        }
        
        //punem numerele pe zar
        int a = 20, b = 45, c = 70;
        
        if(fata == 1 || fata == 3 || fata == 5){
          g.fillOval(b, b, 10, 10);
        }
        if(fata > 1) {
          g.fillOval(a, a, 10, 10);
          g.fillOval(c, c, 10, 10);
        }
        if(fata == 4 || fata == 5 || fata == 6){
          g.fillOval(c, a, 10, 10);
          g.fillOval(a, c, 10, 10);
        }
        if(fata == 6){
          g.fillOval(a, b, 10, 10);
          g.fillOval(c, b, 10, 10);
        }
    }

}
