/*
  Author     : Andy Vos
  StudentNr  : 0945183
*/
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <Keypad.h>

#include <SPI.h>
#include <MFRC522.h>

#define SS_PIN 10
#define RST_PIN 9
MFRC522 mfrc522(SS_PIN, RST_PIN);   // Maak een MFRC522 instance aan.

#define Pincode_Lenght 5 // Pin lengte + NULL char, dus 4 + 1.

char PinCurrent[Pincode_Lenght];
char PinPassword[Pincode_Lenght] = "1234";

byte pin_count = 0;
bool IsCorrect_Pincode;

char customKey;

const byte ROWS = 4;
const byte COLS = 4;

char hexaKeys[ROWS][COLS] = {
  { '1', '2', '3', 'A' },
  { '4', '5', '6', 'B' },
  { '7', '8', '9', 'C' },
  { '*', '0', '#', 'D' }
};

/*
  byte rowPins[ROWS] = {9, 8, 7, 6};
  byte colPins[COLS] = {5, 4, 3, 2};
*/
byte colPins[COLS] = { A0, A1, 7, 6 };
byte rowPins[ROWS] = { 5, 4, 3, 2 };

Keypad customKeypad = Keypad(makeKeymap(hexaKeys), rowPins, colPins, ROWS, COLS);

LiquidCrystal_I2C lcd(0x3F, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE);

void setup() {
  Serial.begin(9600);   // Initiate a serial communication
  SPI.begin();      // Initiate  SPI bus
  mfrc522.PCD_Init();   // Initiate MFRC522

  lcd.backlight();
  lcd.begin(16, 2); // Configureer het aantal kolommen en rijen van de LCD:

  lcd.setCursor(0, 0);
  lcd.print("Initializing");
  delay(500);
  lcd.print("*");
  delay(500);
  lcd.print("*");
  delay(500);
  lcd.print("*");
  delay(1000);
  lcd.setCursor(0, 2);
  lcd.print("[==>-<CYKA>-<==]");
  delay(500);
  lcd.setCursor(0, 2);
  lcd.print("[==>|<CYKA>|<==]");
  delay(500);
  lcd.setCursor(0, 2);
  lcd.print("[==>-<CYKA>-<==]");
  delay(1000);
}

void loop() {
  
  lcd.clear(); // Maak het scherm schoon.
  lcd.setCursor(0, 0);
  lcd.println("Scan your Card  ");
  delay(500);

  if (!mfrc522.PICC_IsNewCardPresent())
  {
    return;
  }
  // Select one of the cards
  if (!mfrc522.PICC_ReadCardSerial())
  {
    return;
  }
  //Show UID on serial monitor
  Serial.print("UID tag :");
  String content = "";
  byte letter;
  for (byte i = 0; i < mfrc522.uid.size; i++)
  {
    Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
    Serial.print(mfrc522.uid.uidByte[i], HEX);
    content.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
    content.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  Serial.println();
  content.toUpperCase();
  if (content.substring(1) == "C3 E6 74 63") // Verander hier de UID van de kaart die toegang moet krijgen.
  {
    lcd.clear();
    lcd.println("Card Accepted   ");
    delay(3000);

    enterPin(); // Voer een Pincode in.

    delay(3000);
  }
  else {
    lcd.clear();
    lcd.println("Card Denied     ");
    delay(3000);
  }
  delay(500);
}

void enterPin()
{
  bool IsPinComplete = false;
  while (!IsPinComplete) {
    lcd.setCursor(0, 0);
    lcd.print("Enter Pin:      ");

    customKey = customKeypad.getKey();
    if (customKey) // Wanneer er een keypad key is ingedrukt, voer de statement uit.
    {
      PinCurrent[pin_count] = customKey;  // store char in de array.      
      lcd.setCursor(pin_count, 1);        // Verplaats de cursor om de nieuwe char te laten zien.
      //lcd.print(PinCurrent[pin_count]); // Print de char bij de cursor.
      lcd.print("*");
      
      pin_count++;
    }

    if (pin_count == Pincode_Lenght - 1) // Als de array index gelijk is aan de verwachte chars, vergelijk dan PinCurrent met PinPassword.
    {
      delay(500);
      lcd.clear();
      lcd.setCursor(0, 0);
      if (!strcmp(PinCurrent, PinPassword)) // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
      {
        lcd.print("Pin Accepted");
        IsPinComplete = true;
      }
      else {
        lcd.print("Pin Denied");
      }

      delay(1000);// added 1 second delay to make sure the password is completely shown on screen before it gets cleared.
      lcd.clear();
      clearData();
    }
  }
}

void clearData()
{
  while (pin_count != 0)
  { // Dit kan worden gebruikt voor elke array size.
    PinCurrent[pin_count--] = 0; //clear de array.
  }
  return;
}
