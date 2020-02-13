/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

import java.awt.*;
public class ATMScreen extends java.awt.Container {


    //constructor
    public ATMScreen(){
        super();
        setLayout(null);
    }

    public void add(ScreenElement sElements)
    {
        sElements.setContainer(this);
    }

    //functie/method die, als aangeroepen wordt, alle elementen van het scherm verwijderd.
    public void clear(){
        removeAll();
    }

    //zorgt voor een mooie logo :)
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillRoundRect(447, 330, 35, 35, 10, 10);
        g.fillRect(477, 360, 5, 5);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("HR", 450, 350);
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.drawString("bank", 451, 360);
        g.setFont(new Font("TimesNewRoman", Font.PLAIN, 12));
    }
}
