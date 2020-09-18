#include<SoftwareSerial.h>

SoftwareSerial esp(2,3);
char prev;
bool state;
void setup() {
  state=true;
 pinMode(13,OUTPUT);
 digitalWrite(13,HIGH);
 pinMode(12,OUTPUT);
 
 esp.begin(115200);
 Serial.begin(115200);

}

void loop() {
 while(esp.available())
 {
  String a=esp.readString();
  Serial.println(a);
   //esp.readString();
   if(a=="13\n")
   pinMode(13,LOW);
   if(a=="5\n")
   {
   if(state)
   {
    digitalWrite(12,LOW);
   }
   else
   {
    digitalWrite(12,HIGH);
   }
   }
   state=!state;
 }
 
}   
