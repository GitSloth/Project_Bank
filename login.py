
#user = "bob"
#code = "1234"

loop = True

users = [ ["bob", "1234", False], ["rick", "6669", False], ["bob_2e", "4269", False] ]

while loop:

    print("please enter your username")

    nameInput = input()

    for y in users:
        if y[0] == nameInput:
            break
        elif y == users[-1] and y[0] != nameInput:
            print("This user does not exist. Would you like to create it?")
            print("Yes [y] | No [n]")
            while 1:
                createUser = input()
                if createUser == "y" or createUser == "n":
                    break
                else:
                    print("type 'y' for yes or 'n' for no")
            
            if createUser == "y":
                print(nameInput + " will be your username. Now enter your desired login code")
                while 1:
                    desiredLogin = input()
                    print("enter it again")
                    secondInput = input()
                    if secondInput == desiredLogin:
                        #print("do something")
                        users.append([nameInput, desiredLogin, 0])
                        y = users[-1]
                        #print(users)
                        break
                    else:
                        print("passwords do not match. start again")
                        print("enter your desired login code")
            elif createUser == "n":
                print("go back to login")


    
    if y[2] == True:
        print("Your account is blocked for failing your login code 3 times")

    else:
        print("Please enter your login code")

        for x in range(3):
            codeCheck = input()

            if codeCheck == y[1]:
                print("You're in")
                break
            else:
                if x != 2:
                    print("Please try again")

        if x == 2:
            print("You failed 3 times you fuckup")
            y[2] = True