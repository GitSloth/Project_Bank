//
// AUTHOR : ANDY VOS
// STUDNR : 0945183
//

// compile in: src
// javac program/Program.java
// run:
// java program/Program

public class Program {
    public static void main(String[] args) {

        Bank abn = new Bank();
        ATM atm = new ATM(abn);

    }
}
