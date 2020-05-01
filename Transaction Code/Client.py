import sys
maxInt = sys.maxsize
minInt = -sys.maxsize - 1

class Client:
    
    name = ""
    pin = 0000
    balance = 0
    billSupply = 0
    attempt = 0
    uid=""

    def __init__(self,n,p,b):
        self.name = n
        self.pin = p
        self.balance = b

    def getName(self):
        return self.name

    def getAttempt(self):
        return self.attempt  

    def setAttempt(self, attempt):
        self.attempt = self.attempt + attempt          
    
    def deposit(self,amount):
        self.balance = self.balance + amount

    def getBalance(self, pin):
        if(checkPin(pin)):
            return self.balance
        else:
            return minInt     

    def checkPin(self, pin):
        if pin == self.pin:
            return 1
        else:
            return 0

    def withdraw(self, amount, billAmount, pin):
        if checkPin(pin) and balance > 0 and billSupply > 0:
            return 1
        else:
            return 0
