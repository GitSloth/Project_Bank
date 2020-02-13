/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

public class Client {

    private String pin;
    private String name;
    private int balance;
    private boolean accountblocked;
    private int tries;

    public Client(String promptPin, String promptName, int promptBalance, boolean blocked) {
        this.pin = promptPin;
        this.name = promptName;
        this.balance = promptBalance;
        this.accountblocked = blocked;
    }

    //getter voor de naam
    public String getName() {
        return name;
    }

    //checkt of de pin correct is.
    public Boolean checkPin(String inputPin) {

        if (inputPin.equals(pin)) {
            return true;
        } else {
            return false;
        }
    }

    //functie dat het huidige balans returnt.
    public int getBalance(String balancePin) {
        int MIN_VALUE = 1;
        if (checkPin(balancePin)) {
            return balance;
        } else {
            //for debugging doeleindes
            System.out.println("Error, pin is invalid");
            return MIN_VALUE;
        }
    }

    //functie/method dat het meegegeven argument van het balans optelt.
    public void deposit(int add) {
        balance += add;
        System.out.println("Your new balance is " + balance);
    }

    //functie/method die het meegegeven argument van het balans aftrekt.
    public boolean withdraw(int withdrawBalance, String withdrawPin) {
        if (checkPin(withdrawPin)) {
            if (balance > 0 && withdrawBalance <= balance) {
                balance -= withdrawBalance;
                return true;
            }
        }
        return false;
    }
    //getter voor de pin
    public String getPin() {
        return pin;
    }
    //getter van het huidige balans
    public int getBalance() {
        return balance;
    }
}
