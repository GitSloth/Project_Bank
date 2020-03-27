import users

while True:

    print("please hold your card against the card scanner")

    nameInput = input()

    for user in users.userTable:
        if user[0] == nameInput:
            break
        # elif user == users.userTable[-1] and user[0] != nameInput:
        #     print("This user does not exist. Would you like to create it?")
        #     print("Yes [y] | No [n]")
        #     while 1:
        #         createUser = input()
        #         if createUser == "y" or createUser == "n":
        #             break
        #         else:
        #             print("type 'y' for yes or 'n' for no")
            
        #     if createUser == "y":
        #         print(nameInput + " will be your username. Now enter your desired login code")
        #         while 1:
        #             desiredLogin = input()
        #             print("enter it again")
        #             secondInput = input()
        #             if secondInput == desiredLogin:
        #                 users.userTable.append([nameInput, desiredLogin, 0])
        #                 user = users.userTable[-1]
        #                 break
        #             else:
        #                 print("passwords do not match. start again")
        #                 print("enter your desired login code")
        #     elif createUser == "n":
        #         print("")
        # hoe willen we nieuwe users toevoegen? tot nu toe had ik dat een gebruiker gewoon een nieuwe user kon maken, dat is handig voor een website maar niet voor een bank.
        # lm, je moet uberhaupt niet een nieuwe user kunnen toevoegen vanaf het pinapparaat
        # dat gebeurt als het goed is intern/achter de schermen - dus met een ander systeem dan het pinapparaat-inlogsysteem

    
    if user[2] == 3:
        print("Your account is blocked for failing your login code 3 times")

    else:
        print("Please enter your login code. you have " + str(3 - user[2]) + " attempts.")
        print("or [x] for cancel")


        start = user[2]
        for i in range(user[2],3,1):
                
            codeCheck = input()

            if codeCheck == "x":
                break

            if codeCheck == user[1]:
                break
            else:
                user[2] += 1
                if user[2] < 3:
                    print("Please try again, " + str(3 - user[2]) + " attempts left")
                    print("or [x] for cancel")
                else:
                    print("You entered a wrong login code three times. Your account is blocked")

        if codeCheck != "x":
            if user[2] < 3:
                user[2] = 0
                print("to succes screen")
                while True:
                    print("time to do anything at all now that youre logged in")
                    input()
            else:
                print("back to login screen")