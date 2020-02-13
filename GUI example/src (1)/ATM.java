/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class ATM {

    //fields
    Bank bk;
    ATMScreen as;

    //arrays
    ArrayList<InputDevice> lister;
    ArrayList<InputDevice> optionlister;
    ArrayList<InputDevice> amountlister;
    //knoppies voor het pinscherm
    private ScreenButton sb1 = new ScreenButton("1", new Point(150, 60));
    private ScreenButton sb2 = new ScreenButton("2", new Point(200, 60));
    private ScreenButton sb3 = new ScreenButton("3", new Point(250, 60));
    private ScreenButton sb4 = new ScreenButton("4", new Point(150, 90));
    private ScreenButton sb5 = new ScreenButton("5", new Point(200, 90));
    private ScreenButton sb6 = new ScreenButton("6", new Point(250, 90));
    private ScreenButton sb7 = new ScreenButton("7", new Point(150, 120));
    private ScreenButton sb8 = new ScreenButton("8", new Point(200, 120));
    private ScreenButton sb9 = new ScreenButton("9", new Point(250, 120));
    private ScreenButton sb0 = new ScreenButton("0", new Point(200, 150));
    private ScreenButton corrbutton = new ScreenButton("CORR", new Point(300, 60));
    private ScreenButton delbutton = new ScreenButton("DEL", new Point(300, 90));
    private ScreenButton oKbutton = new ScreenButton("OK", new Point(190, 180));
    private ScreenButton backbutton = new ScreenButton("Back", new Point(50, 300));
    private ScreenButton abortbutton = new ScreenButton("Abort", new Point(150, 300));

    //knoppies voor de snelkeuzemenu
    private ScreenButton withdraw = new ScreenButton("withdraw", new Point(50, 50));
    private ScreenButton deposit = new ScreenButton("deposit", new Point(200, 50));
    private ScreenButton getBalancebutton = new ScreenButton("get balance", new Point(50, 90));

    //knoppies voor de hoveelheden
    private ScreenButton twentybutton = new ScreenButton("20 dolla", new Point(200, 30));
    private ScreenButton fiftybutton = new ScreenButton("50 dolla", new Point(50, 30));
    private ScreenButton hundredbutton = new ScreenButton("100 dolla", new Point(200, 80));
    private ScreenButton twohundredbutton = new ScreenButton("200 dolla", new Point(50, 80));

    //knoppies voor de bonprinter
    private ScreenButton yesbutton = new ScreenButton("YES", new Point(50, 30));
    private ScreenButton nobutton = new ScreenButton("NO", new Point(150, 30));

    //text
    private DisplayText txt1 = new DisplayText("generated text ", new Point(5, 2));
    private DisplayText txt2 = new DisplayText("displayPin", new Point(190, 30));
    private DisplayText checkBbalancetxt = new DisplayText("check balancetxt",new Point(100,120));
    private DisplayText printtxt = new DisplayText("generated print text ", new Point(5, 27));

    //keypad
    private Keypad kp1 = new Keypad("input");

    //cardreader
    private CardReader cr1 = new CardReader("umm");

    //bonnetjesprinter
    ReceiptPrinter rp = new ReceiptPrinter("Bonnetjes printer");

    //variabelen die worden gebruikt in e transactie
    private Client getClient;
    private String pinInput = "";
    private String checkInput = "";
    private String checkButtonInput = "";
    //amount voor het printen van de withdraw bonnetje
    private int amount = 0;
    private Boolean pinCheck = false;
    private int optionChosen = 0;
    //amountChosen voor het printen van de deposit bonnetje
    private int amountChosen;
    private boolean isBusy = false;
    private String stringlol = "";
    private Boolean checkwithdraw = false;


    //==========================================================================================================================
    //constructor
    public ATM(Bank bank) {
        this.bk = bank;
        as = new ATMScreen();
        Frame f = new Frame("My ATM");
        f.setBounds(400, 200, 500, 400);
        f.setBackground(Color.BLUE);
        f.addWindowListener(new MyWindowAdapter(f));
        f.add(as);
        f.setVisible(true);

        //lijst voor de keypad op het loginscherm
        lister = new ArrayList<>();
        lister.add(sb1);
        lister.add(sb2);
        lister.add(sb3);
        lister.add(sb4);
        lister.add(sb5);
        lister.add(sb6);
        lister.add(sb7);
        lister.add(sb8);
        lister.add(sb9);
        lister.add(sb0);
        lister.add(kp1);
        lister.add(corrbutton);
        lister.add(delbutton);
        lister.add(oKbutton);
        lister.add(backbutton);
        lister.add(abortbutton);

        //lijst voor de snelkeuzemenu
        optionlister = new ArrayList<>();
        optionlister.add(deposit);
        optionlister.add(withdraw);
        optionlister.add(getBalancebutton);
        optionlister.add(yesbutton);
        optionlister.add(nobutton);
        optionlister.add(abortbutton);
        optionlister.add(backbutton);

        //lijst voor de schermen withdraw en deposit.
        amountlister = new ArrayList<>();
        amountlister.add(twentybutton);
        amountlister.add(fiftybutton);
        amountlister.add(hundredbutton);
        amountlister.add(twohundredbutton);
        amountlister.add(backbutton);
        amountlister.add(abortbutton);

        //de functie uitvoeren.
        while(true) {
            doTransaction();
        }
    }

    //==========================================================================================================================

    // de "master" functie, zorgt dat de onderdelen van de transactie wordt aangeroepen.
    public void doTransaction() {
        resetVariables();
        cardreaderScreen();
        loginScreen();
        optionScreen();
    }

    //scherm voor het vragen van de rekeningnummer
    private void cardreaderScreen(){
        as.clear();
        txt1.GiveOutput("Please insert your card");
        as.add(txt1);
        do {
            String accountNumber = cr1.GetInput();
            if (accountNumber == null)
            {
                accountNumber = "";
            }
            else
            {
                getClient = bk.get(accountNumber);
                if (getClient == null)
                {
                    warningScreen();
                    timedEvent();
                    doTransaction();
                }
            }
        }while (getClient == null);
    }

    //scherm om een melding te tonen dat de rekeningnummer niet klopt
    private void warningScreen(){
        as.clear();
        txt1.GiveOutput("Account not found, please try again");
        as.add(txt1);
        timedEvent();
    }

    //scherm voor het opvragen van de pincode
    private void loginScreen (){
        as.clear();
        txt1.GiveOutput("Enter your pin");
        as.add(txt1);

        as.add(sb1);
        as.add(sb2);
        as.add(sb3);
        as.add(sb4);
        as.add(sb5);
        as.add(sb6);
        as.add(sb7);
        as.add(sb8);
        as.add(sb9);
        as.add(sb0);
        as.add(corrbutton);
        as.add(delbutton);
        as.add(oKbutton);
        as.add(abortbutton);

            do {
                for (int i = 0; i < lister.size(); i++) {
                    checkButtonInput = lister.get(i).GetInput();
                    if (checkButtonInput == null) {
                        checkButtonInput = "";
                    }

                    else if (checkButtonInput.equals(corrbutton.name))
                    {
                        if (pinInput.length() > 0 && pinInput != null) {
                            pinInput = pinInput.substring(0, pinInput.length() - 1);
                            checkPinLength(pinInput);
                            as.add(txt2);
                        }
                    }
                    else if (checkButtonInput.equals(delbutton.name))
                    {
                        pinInput = "";
                        checkPinLength(pinInput);
                        as.add(txt2);
                    }
                    else if (checkButtonInput.equals(abortbutton.name))
                    {
                        goodbyeScreen();
                        doTransaction();
                    }
                    else if (checkButtonInput.equals(oKbutton.name)) {
                        if (!getClient.checkPin(pinInput)) {
                            pinInput = "";
                            stringlol = "";
                            checkPinLength(pinInput);
                            pinCheck = false;
                            txt2.GiveOutput("Pin incorrect");
                            as.add(txt2);
                        }
                        else if (getClient.checkPin(pinInput)){
                            pinCheck = true;
                            welcomeScreen();
                        }
                    }
                    else if (pinInput.length() < 4){
                        pinInput += checkButtonInput;
                        checkButtonInput = "";
                        checkPinLength(pinInput);

                    }
                }

            } while (!pinCheck);
    }

    //scherm voor de opties withdraw,deposit en check balance
    private void optionScreen(){
        as.clear();
        optionChosen = 0;
        txt1.GiveOutput("Please choose an option");
        as.add(txt1);
        as.add(withdraw);
        as.add(deposit);
        as.add(getBalancebutton);
        as.add(abortbutton);
        as.add(backbutton);
        isBusy = true;


        do {
            for (int i = 0; i < optionlister.size(); i++) {
                checkInput = optionlister.get(i).GetInput();

                if (checkInput == null) {
                    checkInput = "";
                } else if (checkInput.equals(withdraw.name)) {
                    checkInput = "";
                    optionChosen = 1;
                    System.out.println(withdraw.name);
                    withdrawScreen();
                    isBusy = false;
                } else if (checkInput.equals(getBalancebutton.name)) {
                    checkInput = "";
                    System.out.println(getBalancebutton.name);
                    balanceCheckScreen();
                    isBusy = false;

                } else if (checkInput.equals(deposit.name)) {
                    checkInput = "";
                    optionChosen = 2;
                    System.out.println(deposit.name);
                    depositScreen();
                    isBusy = false;
                }
                else if (checkInput.equals(abortbutton.name))
                {
                    checkInput = "";
                    goodbyeScreen();
                    doTransaction();
                    isBusy = false;
                }

                else if (checkInput.equals(backbutton.name))
                {
                    checkInput = "";
                    System.out.println("You're already at the option screen!");
                   isBusy = false;
                }
            }

        } while (optionChosen == 0);
        checkInput = "";
    }

    //scherm voor het vragen van het bonnetje
    private void receiptScreen(){
        as.clear();
        isBusy = true;
        as.add(yesbutton);
        as.add(nobutton);
        do {
            for (int i = 0; i < optionlister.size(); i ++) {
                checkInput = optionlister.get(i).GetInput();

                if (checkInput == null)
                {
                    checkInput = "";
                }
                else if (checkInput.equals(yesbutton.name))
                {
                    printReceipt();
                }
                else if (checkInput.equals(nobutton.name))
                {
                    goodbyeScreen();
                    doTransaction();
                }
            }
        } while (isBusy);
    }

    //functie/method die checkt hoelang de pincode is en zorgt dat de gepaste aantal sterretjes op het scherm worden vertoond.
    private void checkPinLength(String pinlength){
        if (pinlength.length() == 0){
            stringlol = "";
            txt2.GiveOutput(stringlol);
            System.out.println("lol");
            as.add(txt2);
        }
        else if (pinlength.length() ==1) {
            stringlol = "X";
            txt2.GiveOutput(stringlol);
            as.add(txt2);
        }
        else if (pinlength.length() ==2) {
            stringlol = "XX";
            txt2.GiveOutput(stringlol);
            as.add(txt2);
        }
        else if (pinlength.length() ==3) {
            stringlol = "XXX";
            txt2.GiveOutput(stringlol);
            as.add(txt2);
        }
        else if (pinlength.length() ==4) {
            stringlol = "XXXX";
            txt2.GiveOutput(stringlol);
            as.add(txt2);
        }

    }

    //scherm om op te nemen
    private void withdrawScreen() {
        as.clear();
        txt1.GiveOutput("Withdraw screen");
        as.add(txt1);
        isBusy = true;
        as.add(twentybutton);
        as.add(fiftybutton);
        as.add(hundredbutton);
        as.add(twohundredbutton);
        as.add(abortbutton);
        as.add(backbutton);
        do {
            for (int i = 0; i < amountlister.size(); i++) {
                checkInput = amountlister.get(i).GetInput();
                if (checkInput == null) {
                    checkInput = "";
                } else if (checkInput.equals(twentybutton.name)) {
                    amountChosen = 20;
                    checkwithdraw =  getClient.withdraw(amountChosen, pinInput);
                    amount = 20;
                    isBusy = false;
                } else if (checkInput.equals(fiftybutton.name)) {
                    amountChosen = 50;
                    checkwithdraw = getClient.withdraw(amountChosen, pinInput);
                    amount = 50;
                    isBusy = false;
                } else if (checkInput.equals(hundredbutton.name)) {
                    amountChosen = 100;
                    checkwithdraw = getClient.withdraw(amountChosen, pinInput);
                    amount = 100;
                    isBusy = false;
                } else if (checkInput.equals(twohundredbutton.name)) {
                    amountChosen = 200;
                    checkwithdraw = getClient.withdraw(amountChosen, pinInput);
                    amount = 200;
                    isBusy = false;
                }  else if (checkInput.equals(backbutton.name)){
                    optionScreen();
                } else if (checkInput.equals(abortbutton.name)){
                doTransaction();
            } }
        } while (isBusy);
        checkInput = "";
        if (checkwithdraw) {
            receiptScreen();
        }
        else if (!checkwithdraw){
            as.clear();
            txt1.GiveOutput("You don't have that much dolla!");
            as.add(txt1);
            timedEvent();
            withdrawScreen();
        }
    }

    //functie/method die als eerst wordt aangeroepen in doTransaction om alle variabelen te resetten.
    private void resetVariables(){
         getClient = null;
         pinInput = "";
         checkInput = "";
         checkButtonInput = "";
         pinCheck = false;
         optionChosen = 0;
         amountChosen = 0;
         isBusy = false;
         stringlol = "";
         checkwithdraw = false;
         amount = 0;

    }

    //scherm om te storten.
    private void depositScreen(){
        as.clear();
        txt1.GiveOutput("Deposit screen");
        as.add(txt1);
        isBusy = true;
        as.add(twentybutton);
        as.add(fiftybutton);
        as.add(hundredbutton);
        as.add(twohundredbutton);
        as.add(abortbutton);
        as.add(backbutton);
        do {
            for (int i = 0; i < amountlister.size(); i++) {
                checkInput = amountlister.get(i).GetInput();
                if (checkInput == null) {
                    checkInput = "";
                } else if (checkInput.equals(twentybutton.name)) {
                    amountChosen = 20;
                    getClient.deposit(amountChosen);
                    isBusy = false;
                } else if (checkInput.equals(fiftybutton.name)) {
                    amountChosen = 50;
                    getClient.deposit(amountChosen);
                    isBusy = false;
                } else if (checkInput.equals(hundredbutton.name)) {
                    amountChosen = 100;
                    getClient.deposit(amountChosen);
                    isBusy = false;
                } else if (checkInput.equals(twohundredbutton.name)) {
                    amountChosen = 200;
                    getClient.deposit(amountChosen);
                    isBusy = false;
                } else if (checkInput.equals(backbutton.name)){
                    isBusy = false;
                    optionScreen();
                } else if (checkInput.equals(abortbutton.name)){
                    doTransaction();
                }

            }
        } while (isBusy);
        checkInput = "";
        receiptScreen();
    }

    //scherm om het balans te checken.
    private void balanceCheckScreen(){
        as.clear();
        txt1.GiveOutput("get balance screen");
        as.add(txt1);
        as.add(abortbutton);
        as.add(backbutton);
        checkBbalancetxt.GiveOutput("Your current balance is : "+ getClient.getBalance());
        as.add(checkBbalancetxt);
        getClient.getBalance(pinInput);
        do {
            for (int i = 0; i < amountlister.size(); i++) {
                checkInput = amountlister.get(i).GetInput();
                if (checkInput == null) {
                    checkInput = "";
                }
                else if (checkInput.equals(backbutton.name)) {
                    isBusy = false;
                    optionScreen();
                } else if (checkInput.equals(abortbutton.name)) {
                    doTransaction();
                }
            }
            } while (isBusy);
        checkInput = "";
    }

    //functie/method die een timeunit instelt op 2 seconden ( dus 2 secondes wachten).
    private void timedEvent(){
        try {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    //functie/method die aangeroepen wordt als het pinproces afgebroken wordt.
    private void goodbyeScreen(){
        as.clear();
        txt1.GiveOutput("Bye,have a great time!");
        as.add(txt1);
        timedEvent();
    }

    //functie/method die aangeroepen wordt als het rekeningnummer klopt, weergeeft de naam die aan de client gekoppeld is.
    private void welcomeScreen(){
        as.clear();
        txt1.GiveOutput("Welcome " + getClient.getName() );
        as.add(txt1);
        timedEvent();
    }

    //functie/method die aangeroepen wordt als er op "yes" wordt geklikt op de receiptScreen, toont het bedrag en naam
    //wat er op de rekeningnummer is gezet of eraf gehaald is.
    private void printReceipt(){
        as.clear();
        txt1.GiveOutput("Receipt is being printed, please wait.");
        as.add(txt1);
        //receipt for withdrawing
        if (optionChosen == 1)
        {
            printtxt.GiveOutput("Now withdrawing " + amount +  ", please wait");
            rp.GiveOutput("You withdrew " + amount + " dolla");
            as.add(printtxt);
        }
        //receipt for depositing
        else if (optionChosen == 2)
        {
            printtxt.GiveOutput("Now depositing " + amountChosen + ", please wait");
            rp.GiveOutput("You deposited " + amountChosen + " dolla");
            as.add(printtxt);
        }
        timedEvent();
        goodbyeScreen();
        doTransaction();
    }
}