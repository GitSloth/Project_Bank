//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

public class Client { 
    private String name;
    private String pin;
    private int balance;

    Client(String name, String pin, int balance){
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName(){
        return name;
    }

    public boolean checkPin(String givenPin){
        if(givenPin.equals(pin)){
            System.out.println("true");
            return true;
        }else{
            System.out.println("false");
            return false;
        } 
    }

    public int getBalance(String givenPin){
        if(checkPin(givenPin)){
            return balance;
        }else{
            return Integer.MIN_VALUE;
        }   
        
    }

    public void deposit(int amount){
        balance = balance + amount;
    }

    public boolean withdraw(int amount, String PIN){
        if(checkPin(PIN) && balance > 0 ){
            return true;
        }
        else{
            return false;
        }

    }

}



