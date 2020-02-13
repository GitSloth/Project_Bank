/*
Naam: Shivan Rambaran
Studentnummer: 0973515
Module:TINRPOO-3
Inleverdatum:01-04-2019
 */

public class ReceiptPrinter extends HardwareElement implements OutputDevice {

    //constructor
    public ReceiptPrinter(String receiptName)
    {
        super(receiptName);
    }

    //zorgt dat de receiptprinter iets print.
    @Override
    public String GiveOutput(String output) {
        System.out.println(output);
        return output;
    }


}
