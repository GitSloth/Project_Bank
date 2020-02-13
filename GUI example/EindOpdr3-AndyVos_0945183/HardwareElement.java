//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

import java.awt.Point;
import java.awt.Container;

abstract class HardwareElement extends ATMElement{ 
    private boolean isPowered = false;
    
    HardwareElement(String name){
        super(name);
    }

    void powerOn(){
        isPowered = true;
    }

    void powerOff(){
        isPowered = false;
    }
}



