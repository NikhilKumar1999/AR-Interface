
#include "ESP8266WiFi.h"
 
const char* ssid = "Firewall";
const char* password =  "lata72@yogi64";
 bool state;
 char prev;
WiFiServer wifiServer(80);
 
void setup() {
  prev='0';
 state=false;
  Serial.begin(115200);
 
  delay(1000);
 
  WiFi.begin(ssid, password);
 
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting..");
    Serial.println("Connecting..");
  }
 
 // Serial.print("Connected to WiFi. IP:");
  Serial.println(WiFi.localIP());
 
  wifiServer.begin();
 // pinMode(5,OUTPUT);
 // digitalWrite(5,HIGH);
 
}
 
void loop() {
 
  WiFiClient client = wifiServer.available();
 
  if (client) {
     Serial.println("13");
    while (client.connected()) {
 
      while (client.available()>0) {
        char c = client.read();
        Serial.print(c);
      }
 
      delay(10);
    }

    client.stop();
   // Serial.println("Client disconnected");

  }
}
