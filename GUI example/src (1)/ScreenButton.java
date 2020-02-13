/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

import java.awt.*;
import java.awt.event.ActionEvent;

public class ScreenButton extends ScreenElement implements InputDevice, java.awt.event.ActionListener {

    //variabelen
    Button button;
    public Boolean inputAvailable = false;

    //constructor
    public ScreenButton(String buttonName, Point buttonPos){
        super(buttonPos,buttonName);
        button = new Button(buttonName);
        button.setName(buttonName);
        button.setBounds(pos.x, pos.y, 10 + 15 * buttonName.length(), 25);
        button.addActionListener(this);

    }

    //functie/method dat zorgt, wanneer er op een button gedrukt wordt,de boolean inputAvailable op true wordt gezet.
    @Override
    public void actionPerformed(ActionEvent e) {
        inputAvailable = true;
    }

    //functie/method dat zorgt dat de naam van de button wordt teruggegeven als er op de button wordt gedrukt/
    @Override
    public String GetInput() {
            if (inputAvailable) {
                inputAvailable = false;
                return button.getName();
            } else {
                return null;
            }
    }

    @Override
    public void setContainer(Container container) {
        container.add(button);
    }


}
