import Client
class WithdrawMenu():

    pinInput = 0
    
    # Hoeveelheden van elk biljet
    # [5, 10, 20, 50] biljet in array
    billAmount = [0, 0, 0,0]

    # -- CHOOSE BILL VALUES --
    def choose_value(self,i):
        switcher = {
           0: "plus5",
           1: "min5",
           2: "plus10",
           3: "min10",
           4: "plus20",
           5: "min20",
           6: "plus50",
           7: "min50",
           8: "doWithdraw",
           9: "back",
           10: "reset",
        }
        func = switcher.get(i,"reset")
        return func

    # -- ADD VALUE --
    def plus(self,i):
        i = i + 1
        return i     
    # -- -- -- -- -- -- -- -- -- -- -- -- -- --
    def plus5(self,i):
        billAmount[0] = self.plus(billAmount[0])
    
    def plus10(self,i):
        billAmount[1] = self.plus(billAmount[1])

    def plus5(self,i):
        billAmount[2] = self.plus(billAmount[2])
    
    def plus10(self,i):
        billAmount[3] = self.plus(billAmount[3])

    # -- SUBSTRACT VALUE --
    def min(self,b,i):
        if i <= 0: # negatieve waardes zijn niet toegestaan.
            i = 0
        else:
            i = i - 1
        return i
    # -- -- -- -- -- -- -- -- -- -- -- -- -- --
    def min5(self,i):
        billAmount[0] = self.min(billAmount[0])

    def min10(self,i):
        billAmount[1] = self.min(billAmount[1])

    def min5(self,i):
        billAmount[2] = self.min(billAmount[2])

    def min10(self,i):
        billAmount[3] = self.min(billAmount[3])

    # -- WITHDRAW & CHECKS --
    def totalBillValue(self):
        b1 = 5 * billAmount[0]
        b2 = 10 * billAmount[1]
        b3 = 20 * billAmount[2]
        b4 = 50 * billAmount[3]

        # Voeg alles samen om het total te creën.
        return b1 + b2 + b3 + b4

    # -- -- -- -- -- -- -- -- -- -- -- -- -- --
    def doWithdraw(self):
        if checkFunds() == 1:
            Client.withdraw(totalBillValue(), billAmount, pinInput)
            print("Transfer succesfull")
            #receiptScreen(0, amount) bonnenetje
        elif checkFunds() == 0:
            print("Insufficiant funds")
            WithdrawMenu() # Ga terug
    
    # -- -- -- -- -- -- -- -- -- -- -- -- -- --
    def checkFunds(self):
        if Client.getBalance(pinInput) >= 0:
            return 1
        else:
            return 0

    # -- MENU STUFF --
    def reset(self):
         #Ga terug en start het menu opnieuw op.
        WithdrawMenu()

    def back(self):
        #ga terug naar het hoofdmenu.
        print("go to menu")
