//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

import java.awt.Point;
import java.awt.Container;

abstract class ScreenElement extends ATMElement{ 
    Point pos;
    
    ScreenElement(Point pos, String name){
        super(name);
        this.pos = pos;
    }
    
    abstract void setContainer(Container myContainer);
}



