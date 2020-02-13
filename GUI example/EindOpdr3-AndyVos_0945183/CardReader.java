//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CardReader extends HardwareElement implements InputDevice{ 

    boolean inputAvailable = false;
    BufferedReader bReader;

    CardReader(String name){
        super(name);
        bReader = new BufferedReader(new InputStreamReader(System.in));
    }  
    
    @Override 
    public String getInput(){
        inputAvailable = true;
        //System.out.print("To simulate inserting card, enter card number");
        try {
            System.out.println("Insert Card Number");
            String input = bReader.readLine();
            return input;
            
          }
          catch(Exception e) {
            System.out.print("Error, card scan failed");
            return null;
        
          }
    }

}



