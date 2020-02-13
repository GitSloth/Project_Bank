/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

public class HardwareElement extends ATMElement {


    private Boolean power ;

    //constructor
     HardwareElement(String name){
        super(name);
        this.power = false;
    }
    //zorgt dat het apparaat aan staat.
    public void PowerOn(){
        power = true;
    }
    //zorgt dat het apparaat uit staat.
    public void PowerOff(){
        power = false;
    }
}
