
//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

public class ReceiptPrinter extends HardwareElement implements OutputDevice{ 

    ReceiptPrinter(String name){
        super(name);
    }    

    @Override 
    public String giveOutput(String outputString){
        try {
            System.out.println(outputString);
            return null;
   
        }
            catch(Exception e) {
            System.out.print("Error, input failed");
            return null;
        }
    }

}



