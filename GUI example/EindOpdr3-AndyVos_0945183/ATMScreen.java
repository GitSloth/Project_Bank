//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//
import java.awt.*;

public class ATMScreen extends java.awt.Container{ 

    private static final long serialVersionUID = 1L;

    ATMScreen() {
        super.setLayout(null);

    }

    public void add(ScreenElement ScrElement){
        ScrElement.setContainer(this);
    }

    public void clear(){
        removeAll();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillRoundRect(347, 230, 35, 35, 10, 10);
        g.fillRect(377, 260, 5, 5);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("HR", 350, 250);
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.drawString("bank", 351, 260);
        }
}



