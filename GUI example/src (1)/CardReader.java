/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CardReader extends HardwareElement implements InputDevice{

    //variabelen
    BufferedReader cardReaderReader;

    //constructor
    public CardReader(String cardReaderName)
    {
        super(cardReaderName);
        cardReaderReader = new BufferedReader(new InputStreamReader(System.in));
}

    //zorgt dat de cardreader om input vraagt, dus een rekeningnummer
    @Override
    public String GetInput () {
        try{
            return cardReaderReader.readLine();
        }

        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
