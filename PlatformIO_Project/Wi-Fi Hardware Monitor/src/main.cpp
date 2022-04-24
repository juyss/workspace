#include <Arduino.h>

// ESP8266 Libraries
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

// JSON Library
#include <ArduinoJson.h>

// SSD1306 Screen Libraries
#include <Wire.h>
#include <SSD1306Wire.h>
#include "secrets.h"

// Update over Wi-Fi Libraries
#include <ESP8266mDNS.h>
#include <WiFiUdp.h>

// Screen SSD1306
//Adafruit_SSD1306 display = Adafruit_SSD1306(128, 64, &Wire);

SSD1306Wire display(0x3c, D1 , D2);

void startSSD1306() {
    display.init();
    display.flipScreenVertically();
    display.clear();
    display.display();
}

void bootLogoSSD1306() {
    // Display Network Status
    display.clear();
    display.setTextAlignment(TEXT_ALIGN_LEFT);
    display.setFont(ArialMT_Plain_16);
    display.drawString(0, 0, "Hardware Monitor");
    display.drawString(0, 14,"By JinPeng");
    display.setFont(ArialMT_Plain_10);
    display.drawString(0, 45 ,"Connecting to Wi-Fi...");
    display.display();
}

void setup() {
    Serial.begin(115200);
    Serial.println(ssid);
    Serial.println(password);
    WiFi.begin(ssid, password);

    startSSD1306();
//
    while (WiFi.status() != WL_CONNECTED) {
        bootLogoSSD1306();
        Serial.println("waiting");
        delay(1000);
    }
    Serial.println("WiFi connected: " + WiFi.SSID());
    Serial.print("IP address: ");
    Serial.println(WiFi.localIP());
}

void getInfo() {
    WiFiClient client;
    HTTPClient http;

    // Send request
    http.useHTTP10(true);
    http.begin(client, url);
    int code = http.GET();

    //请求失败关掉显示屏
    if(code != HTTP_CODE_OK){
        Serial.print("Request Failed, Response Code:");
        Serial.println(code);
        display.displayOff();
        return;
    } else{
        //请求成功开启显示屏
        display.displayOn();
    }

    // Parse response
    const size_t capacity =
            89 * JSON_ARRAY_SIZE(0) + 11 * JSON_ARRAY_SIZE(1) + 6 * JSON_ARRAY_SIZE(2) + 4 * JSON_ARRAY_SIZE(3) +
            3 * JSON_ARRAY_SIZE(4) + 2 * JSON_ARRAY_SIZE(5) + JSON_ARRAY_SIZE(6) + JSON_ARRAY_SIZE(7) +
            2 * JSON_ARRAY_SIZE(8) + 4 * JSON_ARRAY_SIZE(9) + 123 * JSON_OBJECT_SIZE(7) + 12530;
    DynamicJsonDocument doc(capacity);
    deserializeJson(doc, http.getStream(), DeserializationOption::NestingLimit(12));
    //CPU
    String cpuName = doc["Children"][0]["Children"][0]["Text"];
    String cpuTemp = doc["Children"][0]["Children"][0]["Children"][1]["Children"][0]["Value"];
    cpuTemp = cpuTemp.substring(0,4);
    String cpuLoad = doc["Children"][0]["Children"][0]["Children"][2]["Children"][0]["Value"];
    cpuLoad.replace(" %","");
    //GPU
    String gpuName = doc["Children"][0]["Children"][2]["Text"];
    //NVIDIA
    String logo = gpuName.substring(0,6);
    //GTX 1650
    String model = gpuName.substring(22);
    String gpuTemp = doc["Children"][0]["Children"][2]["Children"][1]["Children"][0]["Value"];
    gpuTemp = gpuTemp.substring(0, 4);
    String gpuLoad = doc["Children"][0]["Children"][2]["Children"][2]["Children"][0]["Value"];
    gpuLoad.replace(" %","");
    //RAM
    String loadRAM = doc["Children"][0]["Children"][1]["Children"][0]["Children"][0]["Value"];
    loadRAM.replace(" %","");
    int process = loadRAM.toInt();

//    CPU: AMD Ryzen 5 4600H
//    CPU Temp: 50.5
//    CPU Load: 5.2 %
//    GPU Name: NVIDIA GTX 1650
//    GPU Temp: 38.0
//    GPU Load: 1.0 %
//    RAM Load: 54.4 %

    // Console Log
    Serial.print("\n\n\n\nCPU: ");
    Serial.println(cpuName);
    Serial.print("CPU Temp: ");
    Serial.println(cpuTemp);
    Serial.print("CPU Load: ");
    Serial.println(cpuLoad);
    Serial.print("GPU Name: ");
    Serial.println(logo +" "+ model);
    Serial.print("GPU Temp: ");
    Serial.println(gpuTemp);
    Serial.print("GPU Load: ");
    Serial.println(gpuLoad);
    Serial.print("RAM Load: ");
    Serial.println(loadRAM);

    // Text on Display 1
    display.clear();

    // CPU
    // CPU - Name
    display.setTextAlignment(TEXT_ALIGN_LEFT);
    display.setFont(ArialMT_Plain_10);
    display.drawString(0 , 0 , cpuName);

    // CPU - Temperature and Load
    display.setFont(ArialMT_Plain_16);
    display.drawString(0, 11,  cpuTemp);
    //绘制摄氏度符号°C
    display.drawString(32, 11, "°C");

    // CPU - Load
    display.drawString(64, 11 ,cpuLoad + "%");

    // GPU
    // GPU - Name
    display.setFont(ArialMT_Plain_10);
    display.setTextAlignment(TEXT_ALIGN_LEFT);
    display.drawString(0, 27, logo + ' ' + model);

    // GPU - Temperature
    display.setFont(ArialMT_Plain_16);
    display.drawString(0, 38, gpuTemp);
    //绘制摄氏度符号°C
    display.drawString(32, 38, "°C");

    // GPU - Load
    display.drawString(64, 38 , gpuLoad + "%");

    // RAM
    display.setFont(ArialMT_Plain_10);
    display.setTextAlignment(TEXT_ALIGN_LEFT);
    display.drawString(0, 52, "RAM");
    display.drawProgressBar(29, 56, 95, 6, process);


    display.display();

    // Disconnect
    http.end();
}

void loop() {
    // Check WiFi Status
    if (WiFi.status() == WL_CONNECTED) {
        // Enable Wi-Fi Hardware Monitor with SSD1306 screen
//        hardwareMonitorSSD1306();
        getInfo();
    }
    delay(1000);
}