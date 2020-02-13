
//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

import java.awt.*;

public class DisplayText extends ScreenElement implements OutputDevice{ 
    
    Label label;

    DisplayText(Point pos, String name){
        super(pos,name);

        label = new Label();
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setBounds(pos.x, pos.y, 400, 35);
    }
        
    @Override 
    public String giveOutput(String outputString){
        label.setText(outputString);
        return null;
    }

    @Override
    void setContainer(Container container){
        container.add(label);
    }
}



