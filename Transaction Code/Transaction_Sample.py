
class WithdrawMenu():
    
    billValue = 20  # Tijdelijke hardcoded waardes.
    checkFunds = 1  #
    cancel = 0      #
    

    choosing = 1 
    
    # Loop is bezig als gebruiker nog bezig is met het maken van een keuze.
    while choosing != 0: 
        if billValue == 10:
            print("choose 10")
        elif billValue == 20:
            print("choose 20")
        elif billValue == 50:
            print("choose 50")
        elif cancel == 1:
            print("canceled")
        
    if cancel == 1:
        if checkFunds == 1:
            #client.withdraw(billValue, pinInput)
            print("Transfer succesfull")
            #receiptScreen(0, amount) bonnenetje
        elif checkFunds == 0:
            print("Insufficiant funds")
            WithdrawMenu() # Ga terug
