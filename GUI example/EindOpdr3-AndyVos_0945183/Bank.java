//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

import java.util.Map;
import java.util.HashMap;

public class Bank {
    Map<String,Client> accounts;

    Bank(){
        this.accounts = new HashMap<>();

        accounts.put("NL02BANK0123456789",new Client("Bob", "2134", 10));
        accounts.put("NL02BANK0123456788",new Client("Rob", "3134", 100));
        accounts.put("NL02BANK0123456787",new Client("Tom", "4134", 160));
        accounts.put("NL02BANK0123456786",new Client("Pom", "5134", 109));
        
    }

    public Client get(String bankNr){
        try{
        if(accounts.containsKey(bankNr)){
            return accounts.get(bankNr);
        }else{
            return null;
        }
    }
        catch(Exception e){
            return null;
        }
    }

}



