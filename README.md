# Noswear project (shocking watch profanity tracking)  

[Noswear_Android_Client](https://github.com/StarrLucky/Noswear_Android_Client): sending commands to NFR51 board over BLE  

[Noswear_Shocking_Board](https://github.com/StarrLucky/Noswear_Shocking_Board): controlling shocking circuit by receiving commands from BLE android app.  

--- 

## Noswear_Java voice recognition app  

Recognition profanity from live speech from microphone using Microsoft Congitive Services.
This app recognising live speech and compares words with profanity language [dictionary](dict/profanity_dic.txt).
In case, if it matches with dictionary shocking on command will be sent to Noswear android app trough WiFi connection. 

----
### Manual from demo:
### Prerequisites
* A subscription key for the Speech service. See [Try the speech service for free](https://docs.microsoft.com/azure/cognitive-services/speech-service/get-started).

* [Download the sample code to your development PC.](/README.md#get-the-samples)
* Create an empty workspace in Eclipse and import the folder containing this sample as a project into your workspace.
* Edit the `Main` source:
  * Replace the string `YourSubscriptionKey` with your own subscription key.
  * Replace the string `YourServiceRegion` with the service region of your subscription.
    For example, replace with `westus` if you are using the 30-day free trial subscription.
* Save the modified file.

----

  ![Noswear shocking watch pcb](https://github.com/StarrLucky/Noswear_Shocking_Board/blob/master/pcb.jpg)
