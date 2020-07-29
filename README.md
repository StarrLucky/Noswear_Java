# Noswear project (shocking watch profanity tracking)  

[Noswear_Android_Client](https://github.com/StarrLucky/Noswear_Android_Client): Sending commands to NFR51 board over BLE;
[Noswear_Shocking_Board](https://github.com/StarrLucky/Noswear_Shocking_Board): Controlling shocking circuit by receiving commands from BLE android app.

## Noswear_Java voice recognition app  

Recognition profanity from live speech from microphone using Microsoft Congitive Services.
This app recognising live speech and compares words with dictionary of profanity (profanity_dict.txt).
In case, if it matches with dictionary shocking on command will be sent to Noswear android app trough WiFi connection. 



### Manual from demo:
### Prerequisites
* A subscription key for the Speech service. See [Try the speech service for free](https://docs.microsoft.com/azure/cognitive-services/speech-service/get-started).
* A PC (Windows x64, Ubuntu 16.04 or 18.04 on x64 or ARM64, RHEL 8 or CentOS 8 on x64) or Mac (macOS 10.13 or later) capable to run Eclipse,[<sup>[1]</sup>](#footnote1) with a working microphone.
* 64-bit JRE/JDK for Java 8.
* Version 4.8 of [Eclipse](https://www.eclipse.org), 64-bit.[<sup>[1]</sup>](#footnote1)
* On Ubuntu 16.04 or 18.04, run the following commands for the installation of required packages:
## Build the sample

> Note: more detailed step-by-step instructions are available [here](https://docs.microsoft.com/azure/cognitive-services/speech-service/quickstart-java-jre).

* **By downloading the Microsoft Cognitive Services Speech SDK when building this sample, you acknowledge its license, see [Speech SDK license agreement](https://docs.microsoft.com/azure/cognitive-services/speech-service/license).**
* [Download the sample code to your development PC.](/README.md#get-the-samples)
* Create an empty workspace in Eclipse and import the folder containing this sample as a project into your workspace.
* Edit the `Main` source:
  * Replace the string `YourSubscriptionKey` with your own subscription key.
  * Replace the string `YourServiceRegion` with the service region of your subscription.
    For example, replace with `westus` if you are using the 30-day free trial subscription.
* Save the modified file.
## References

* [Quickstart article on the SDK documentation site](https://docs.microsoft.com/azure/cognitive-services/speech-service/quickstart-java-jre)
* [Speech SDK API reference for Java](https://aka.ms/csspeech/javaref)
