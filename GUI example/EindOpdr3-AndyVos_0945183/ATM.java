//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ATM{ 
    Bank Bank;
    ATMScreen as;

    private Client client;  
    private Keypad keypad = new Keypad("keypad");
    private CardReader cardReader = new CardReader("CardScan");
    private ScreenButton butt0, butt1, butt2, butt3, butt4, butt5, butt6, butt7, butt8, butt9, buttOk, buttCor
    , buttWithdraw, buttDeposit, buttBalance, buttCancel, buttYes, buttNo
    , butt20Credit, butt50Credit, butt100Credit, butt200Credit;
    private ArrayList<InputDevice> keypadList;
    private ArrayList<InputDevice> menuList;
    private ArrayList<InputDevice> creditList;

    private DisplayText displayText;
    private DisplayText displayPin;
    private DisplayText displayBalance;
    private DisplayText displayReceipt;

    private String pinInput;
    private String pinOutput;

    private Boolean option = true;
    
    private int attempts = 0;

    //ReceiptPrinter receiptPrinter;

    ATM(Bank Bank){
        this.Bank = Bank;

        as = new ATMScreen();
        Frame f = new Frame("My ATM");
        f.setBounds(500, 300, 800, 480);
        f.setBackground(Color.DARK_GRAY);
        f.addWindowListener(new MyWindowAdapter(f));
        f.add(as);
        f.setVisible(true);

        displayText = new DisplayText(new Point(120,80), "Text");
        displayPin = new DisplayText(new Point(120,120), "PinText");
        displayBalance = new DisplayText(new Point(120,120), "BalanceText");
        displayReceipt = new DisplayText(new Point(120,120), "ReceiptText");
        
        butt0 = new ScreenButton(new Point(50,85),"0");
        butt1 = new ScreenButton(new Point(20,10),"1");
        butt2 = new ScreenButton(new Point(50,10),"2");
        butt3 = new ScreenButton(new Point(80,10),"3");
        butt4 = new ScreenButton(new Point(20,35),"4");
        butt5 = new ScreenButton(new Point(50,35),"5");
        butt6 = new ScreenButton(new Point(80,35),"6");
        butt7 = new ScreenButton(new Point(20,60),"7");
        butt8 = new ScreenButton(new Point(50,60),"8");
        butt9 = new ScreenButton(new Point(80,60),"9");

        buttOk = new ScreenButton(new Point(20,110),"Ok"); // Bevestig
        buttCor = new ScreenButton(new Point(70,110),"<"); // Correctie

        buttWithdraw = new ScreenButton(new Point(550,10),"withdraw");
        buttDeposit = new ScreenButton(new Point(550,35),"deposit");
        buttBalance = new ScreenButton(new Point(550,60),"balance");
        buttCancel = new ScreenButton(new Point(550,85),"cancel");
        buttYes = new ScreenButton(new Point(550,35),"yes");
        buttNo = new ScreenButton(new Point(550,60),"no");

        butt20Credit = new ScreenButton(new Point(20,10),"20");
        butt50Credit = new ScreenButton(new Point(20,35),"50");
        butt100Credit = new ScreenButton(new Point(20,60),"100");
        butt200Credit = new ScreenButton(new Point(20,85),"200");

        keypadList = new ArrayList<InputDevice>();
        keypadList.add(butt0);
        keypadList.add(butt1);
        keypadList.add(butt2);
        keypadList.add(butt3);
        keypadList.add(butt4);
        keypadList.add(butt5);
        keypadList.add(butt6);
        keypadList.add(butt7);
        keypadList.add(butt8);
        keypadList.add(butt9);

        keypadList.add(buttOk);
        keypadList.add(buttCor);
        keypadList.add(keypad);  

        menuList = new ArrayList<InputDevice>();
        menuList.add(buttDeposit);
        menuList.add(buttWithdraw);
        menuList.add(buttBalance);
        menuList.add(buttCancel);
        menuList.add(buttYes);
        menuList.add(buttNo);

        creditList = new ArrayList<InputDevice>();
        creditList.add(butt20Credit);
        creditList.add(butt50Credit);
        creditList.add(butt100Credit);
        creditList.add(butt200Credit);
        creditList.add(buttCancel);

        as.add(displayText);
        
        while(true){
            doTransaction();
        }
    }

    public void doTransaction(){

        doCardreader();
        doPin();
        menuScreen();
        
    }

    private void doCardreader(){
        String bankAccountNr = "";
        as.clear();
        displayText.giveOutput("enter card number:");
        as.add(displayText);        
        while(client == null){
            bankAccountNr = cardReader.getInput();
            if(bankAccountNr == null){
                bankAccountNr = "";
            }else {
                client = Bank.get(bankAccountNr);
                if(client == null){
                    System.out.println("Error");
                    delayTime(3);
                }
                else{
                    doTransaction();
                }
            }
        }
        
    }

    
    private void doPin(){
        keypad.getInput();
        boolean checkPin = false;
        pinInput = "";
        as.clear();
        displayText.giveOutput("enter pin:");
        as.add(displayText);
        addButtons();        

        while(!checkPi &&!pinBlocked()){
            for(InputDevice inputD : keypadList){
                String inputButton= inputD.getInput();                
                if(inputButton != null){ 
                    //System.out.println(inputButton);              
                    if (inputButton.equals(buttCor.name))
                    {
                        if (pinInput.length() > 0 && pinInput != null) {
                            pinInput = pinInput.substring(0, pinInput.length() - 1);
                            showPin(pinInput.length());
                            as.add(displayPin);
                            System.out.println(pinInput + " input");
                        }
                    }
                    else if (inputButton.equals(buttOk.name)) 
                    {
                        if (!client.checkPin(pinInput)) 
                        {
                            pinInput = "";
                            pinOutput = "";
                            showPin(pinInput.length());
                            checkPin = false;
                            displayPin.giveOutput("Incorrect Pin");
                            attempts = attempts++;
                            as.add(displayPin);
                        }
                        else if (client.checkPin(pinInput)){
                            checkPin = true;
                            loginScreen();
                        }
                    }
                    else if (pinInput.length() < 4){
                        pinInput += inputButton;
                        inputButton = "";
                        showPin(pinInput.length());
                    }
                }else{inputButton = "";}
            }
        }        
    }

    private Boolean pinBlocked(){
        if(attempts >= 3){
            return true;
        }else{
            return false;
        }
        
    }


    private void menuScreen(){
        as.clear();
        option = true;
        displayText.giveOutput("Options Menu");
        as.add(displayText);
        as.add(buttWithdraw);
        as.add(buttDeposit);
        as.add(buttBalance);
        as.add(buttCancel);
        while (option) {
            for(InputDevice inputM : menuList){
                String inputButton= inputM.getInput();                  

                if (inputButton == null) {
                    inputButton = "";
                } else if (inputButton.equals(buttWithdraw.name)) {
                    inputButton = "";
                    //option = false;
                    System.out.println(buttWithdraw.name);                    
                    withdrawScreen();
                } 
                else if (inputButton.equals(buttDeposit.name)) {
                    inputButton = "";
                    //option = false;
                    System.out.println(buttDeposit.name);
                    depositScreen();
                }
                else if (inputButton.equals(buttBalance.name)) {
                    inputButton = "";
                    System.out.println(buttBalance.name);
                    balanceScreen();
                    option = false;

                } 
                else if (inputButton.equals(buttCancel.name))
                {
                    inputButton = "";
                    option = false;
                }
            }

        }
    }

    private void withdrawScreen(){
        as.clear();
        displayText.giveOutput("Withdraw Menu");
        as.add(displayText);
        as.add(butt20Credit);
        as.add(butt50Credit);
        as.add(butt100Credit);
        as.add(butt200Credit);
        as.add(buttCancel);

        option = true;
        Boolean cancel = false;
        Boolean checkWithdraw = false;

        int amount = 0;

        while (option) {
            for(InputDevice inputC : creditList){
                String inputButton= inputC.getInput();   
                if (inputButton == null) {
                    inputButton = "";
 
                } else if (inputButton.equals(butt20Credit.name)) {
                    checkWithdraw = client.withdraw(20, pinInput);
                    amount = 20;
                    option = false;
                } else if (inputButton.equals(butt50Credit.name)) {
                    checkWithdraw = client.withdraw(50, pinInput);
                    amount = 50;
                    option = false;
                } else if (inputButton.equals(butt100Credit.name)) {
                    checkWithdraw = client.withdraw(100, pinInput);
                    amount = 100;
                    option = false;
                } else if (inputButton.equals(butt200Credit.name)) {
                    checkWithdraw = client.withdraw(200, pinInput);
                    amount = 200;
                    option = false;
                } else if (inputButton.equals(buttCancel.name)){
                    inputButton = "";
                    cancel = true;
                    option = false;
                }
             }             
        }

        if(!cancel){
            if (checkWithdraw) {
                as.clear();
                client.withdraw(amount, pinInput);
                displayText.giveOutput("Transfer succesfull");
                as.add(displayText);
                delayTime(2);
                receiptScreen(0, amount);
            }
            else if (!checkWithdraw){
                as.clear();
                displayText.giveOutput("Insufficiant funds");
                as.add(displayText);
                delayTime(2);
                withdrawScreen();
            }
        }
    }
    private void depositScreen(){
        as.clear();
        displayText.giveOutput("Deposit Menu");
        as.add(displayText);
        as.add(butt20Credit);
        as.add(butt50Credit);
        as.add(butt100Credit);
        as.add(butt200Credit);
        as.add(buttCancel);

        option = true;
        Boolean cancel = false;

        int amount = 0;

        while (option) {
            for(InputDevice inputC : creditList){
                String inputButton= inputC.getInput();   
                if (inputButton == null) {
                    inputButton = "";
 
                } else if (inputButton.equals(butt20Credit.name)) {
                    client.withdraw(20, pinInput);
                    amount = 20;
                    option = false;
                } else if (inputButton.equals(butt50Credit.name)) {
                    client.withdraw(50, pinInput);
                    amount = 50;
                    option = false;
                } else if (inputButton.equals(butt100Credit.name)) {
                    client.withdraw(100, pinInput);
                    amount = 100;
                    option = false;
                } else if (inputButton.equals(butt200Credit.name)) {
                    client.withdraw(200, pinInput);
                    amount = 200;
                    option = false;
                } else if (inputButton.equals(buttCancel.name)){
                    inputButton = "";
                    cancel = true;
                    option = false;
                }
             }             
        }

        as.clear();
        if(!cancel){
            client.deposit(amount);
            displayText.giveOutput("Transfer succesfull");
            as.add(displayText);
            delayTime(2);
            receiptScreen(1, amount);
        }        
    }

    private void balanceScreen(){
        as.clear();
        displayText.giveOutput("Balance Menu");
        as.add(displayText);
        as.add(displayBalance);
        as.add(buttCancel);
        displayBalance.giveOutput("Current balance: "+ client.getBalance(pinInput));
        System.out.println("Current balance: "+ client.getBalance(pinInput));
        option = true;
        while (option) {
            for(InputDevice input : menuList){
                String inputButton= input.getInput();   
                if (inputButton == null) {
                    inputButton = "";
                } else if (inputButton.equals(buttCancel.name)){
                    //inputButton = "";
                    option = false;
                }           
            }             
        }

        as.clear();
        System.out.println("Going Back");
        as.add(displayText);
        delayTime(1);
    }

    private void receiptScreen(int receiptType, int chosenAmount){
        as.clear();
        displayText.giveOutput("Print receipt?");
        as.add(displayText);
        as.add(buttYes);
        as.add(buttNo);
        option = true;
        while (option) {
            for(InputDevice inputM : menuList){
                String inputButton= inputM.getInput();  
                if (inputButton == null)
                {
                    inputButton = "";
                }
                else if (inputButton.equals(buttYes.name))
                {
                    printReceipt(receiptType, chosenAmount);
                    inputButton = "";
                    option = false;
                }
                else if (inputButton.equals(buttNo.name))
                {
                    inputButton = "";
                    option = false;
                }
            }
        }
    }

    private void printReceipt(int receiptType, int chosenAmount){
        as.clear();
        displayText.giveOutput("Receipt");
        as.add(displayText);

        if (receiptType == 0)
        {
            displayReceipt.giveOutput("You have withdrawn " + chosenAmount + " credits");
            as.add(displayReceipt);
        }
        else if (receiptType == 1)
        {
            displayReceipt.giveOutput("You have deposited " + chosenAmount + " credits");
            as.add(displayReceipt);
        }
        delayTime(2);
    }

    private void addButtons(){
        as.add(butt0);
        as.add(butt1);
        as.add(butt2);
        as.add(butt3);
        as.add(butt4);
        as.add(butt5);
        as.add(butt6);
        as.add(butt7);
        as.add(butt8);
        as.add(butt9);

        as.add(buttOk);
        as.add(buttCor);
    }
        
    private void showPin(int pinlength){
        pinOutput = "";
        for(int j = 0; j < pinlength; j++){
            pinOutput = pinOutput + "X";
        }
        displayPin.giveOutput(pinOutput);
        as.add(displayPin);
       
    }

    private void loginScreen(){
        as.clear();
        displayText.giveOutput("Welcome, " + client.getName());
        as.add(displayText);
        delayTime(3);
    }

    private void delayTime(int x){
        try {
            TimeUnit.SECONDS.sleep(x); // Wacht x aantal seconden.
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


}



