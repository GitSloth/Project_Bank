
class Client:
    
    name = ""
    pin = 0000
    balance = 0
    uid=""

    def __init__(self,n,p,b):
        self.name = n
        self.pin = p
        self.balance = b

    def getName(self):
        return self.name
    
    def deposit(self,amount):
        self.balance = self.balance + amount

    def getBalance(self, pin):
        if(checkPin(pin)):
            return self.balance
        else:
            return -self.balance.maxint #WIP
        

    def checkPin(self, pin):
        if pin == self.pin:
            return 1
        else:
            return 0

    def withdraw(self, amount, pin):
        if checkPin(pin) and balance > 0:
            return 1
        else:
            return 0
