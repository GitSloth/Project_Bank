/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//zorgt dat, wanneer op het kruisje gedrukt wordt, het programma afsluit.
public class MyWindowAdapter extends WindowAdapter {
    Frame f;
    MyWindowAdapter(Frame f) {
        this.f = f;
    }
    public void windowClosing(WindowEvent e) {
        f.dispose();
        System.exit(0);
    }
}

