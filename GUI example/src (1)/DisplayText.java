/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

import java.awt.*;

public class DisplayText extends ScreenElement implements OutputDevice {

    //variabelen
    Label label;

    //constructor
    public DisplayText(String displayName, Point displayPos){
        super(displayPos,displayName);
        label = new Label();
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(pos.x, pos.y, 400, 25);
    }

    //geeft een container mee aan de label
    @Override
    public void setContainer(Container container) {
        container.add(label);
    }

    //zorgt dat je tekst kan geven aan het object.
    @Override
    public String GiveOutput(String output) {
        label.setText(output);
        return output;
    }
}
