/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public  class Keypad extends HardwareElement implements InputDevice {

    //variabelen
    BufferedReader keypadReader;
    String b;

    //constructor
    public Keypad(String keypadName) {
        super(keypadName);
        keypadReader = new BufferedReader(new InputStreamReader(System.in));
    }

    //zorgt dat de keypad om input vraagt en checkt de input op meer dan 1 karakter.
    @Override
    public String GetInput() {
        try {
            if (keypadReader.ready()) {
                b = keypadReader.readLine();
                if (b.length() < 2) {
                    return b;
                }
                else{
                    return null;
                }
            }
            else {
                return null;
            }
        }
        catch (Exception err){
            System.out.print(err);
        }
        return null;
        }
}
