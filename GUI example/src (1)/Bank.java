/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

import java.util.*;
public class Bank {

    private Map<String, Client> accounts;

    //constructor, initialiseert de clients
    public Bank (){
    this.accounts = new HashMap<>();
    accounts.put("1",new Client("9999","Henk", 100,false));
    accounts.put("2",new Client("1235","Kjeld", 1,false));
    accounts.put("3",new Client("0999","Janny", 0,false));
    }

    //functie/method dat kijkt of de rekeninnummer bestaat aan de hand van het meegegeven argument.
    public Client get(String accountNumber) {
        try {
            if (accounts.containsKey(accountNumber)) {
                return accounts.get(accountNumber);
            } else {
                return null;
            }
        }
        catch(Exception e)
        {
        System.out.println(e);
        return null;
        }
    }
}
