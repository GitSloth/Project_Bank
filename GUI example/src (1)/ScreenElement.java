/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

import java.awt.Point;
import java.awt.Container;

public abstract class ScreenElement extends ATMElement {

    //point, puntje op het scherm.
    Point pos;

    //conainer, een doos om het object heen.
    private Container container = new Container();

    //constructor
    public ScreenElement(Point pos2, String name)
    {
        super(name);
        this.pos = pos2;
    }

    //zorgt dat de field container gezet wordt door het meegeven argument.
    public void setContainer(Container container) {
        this.container = container;
    }
}
