#define BLINKER_MIOT_LIGHT//支持小爱同学
#define BLINKER_WIFI

#include <Blinker.h>
#include <Servo.h>

char auth[] = "13202599d71f";   //app中获取到的Secret Key(密钥)
char ssid[] = "HONOR-105FI3";
char pswd[] = "1028507471";
char btn[] = "button";


// 新建组件对象
BlinkerButton Button1(btn);//注意：要和APP组件’数据键名’一致
Servo servo;

// 按下BlinkerAPP按键即会执行该函数
void button_callback(const String &state) {
    Serial.println("get button state: " + state);
    BLINKER_LOG("get button state: ", state);

    Blinker.vibrate();
    if (state == "off") {
        // 反馈开关状态
        Button1.print("off");
        servo.write(130);
        delay(1000);
        servo.write(90);
    } else if (state == "on") {
        // 反馈开关状态
        Button1.print("on");
        servo.write(50);
        delay(1000);
        servo.write(90);
    }
}

//小爱电源类操作的回调函数:
//当小爱同学向设备发起控制, 设备端需要有对应控制处理函数
void miotPowerState(const String &state) {
    Serial.println("need set power state: " + state);
    BLINKER_LOG("need set power state: ", state);

    if (state == BLINKER_CMD_OFF) {//如果语音接收到是关闭灯就设置GPIO口为高电平
        // 反馈开关状态
        Button1.print("off");
        servo.write(130);
        delay(1000);
        servo.write(90);
        BlinkerMIOT.powerState("off");
        BlinkerMIOT.print();
    } else if (state == BLINKER_CMD_ON) {
        // 反馈开关状态
        Button1.print("on");
        servo.write(50);
        delay(1000);
        servo.write(90);
        BlinkerMIOT.powerState("on");
        BlinkerMIOT.print();
    }
}


void setup() {
    // 初始化串口，并开启调试信息，调试用可以删除
    Serial.begin(115200);
    servo.attach(2);
    servo.write(90);
    Blinker.begin(auth, ssid, pswd);
    Button1.attach(button_callback);
    BlinkerMIOT.attachPowerState(miotPowerState);//这段代码一定要加，不加小爱同学控制不了,务必在回调函数中反馈该控制状态
}

void loop() {
    Blinker.run();
}