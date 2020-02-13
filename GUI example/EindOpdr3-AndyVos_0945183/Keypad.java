//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Keypad extends HardwareElement implements InputDevice{ 

    boolean inputAvailable = false;
    BufferedReader bReader; // Keypad Reader

    Keypad(String name){
        super(name);
        bReader = new BufferedReader(new InputStreamReader(System.in));
    }    

    @Override 
    public String getInput(){
        inputAvailable = true;
        try {
            //System.out.println("Enter your pin: ");
            if(bReader.ready()){
                String line = bReader.readLine();
                if(line.length() > 1){
                    return line;
                }
            }    
        }
        catch(Exception e) {
            System.out.print("Error, input failed");
            return null;        
        }
        return null;
    }

}



