//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.*;

public class ScreenButton extends ScreenElement implements InputDevice,java.awt.event.ActionListener { 

    boolean inputAvailable = false;
    Button button;

    ScreenButton(Point pos, String name){
        super(pos,name);

        button = new Button(name);
        button.setName(name);
        button.setBounds(pos.x, pos.y, 10 + 15 * name.length(), 25);
        button.addActionListener(this);
    }    

    @Override 
    public String getInput(){        
        if(inputAvailable)
        {
            inputAvailable = false;
            return this.name;
        }
        else
        {
            return null;
        }
    }

    @Override 
    public void actionPerformed(ActionEvent e){
        inputAvailable = true;
    }
    @Override
    void setContainer(Container container){
        container.add(button);
    }
}



