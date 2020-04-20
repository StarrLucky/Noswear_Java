//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE.md file in the project root for full license information.
//
// <code>
package speechsdk.quickstart;

import com.microsoft.cognitiveservices.speech.*;

import java.util.Arrays;

/**
 * Quickstart: recognize speech using the Speech SDK for Java.
 */



public class Continuous {




    /**
     * @param args Arguments are ignored in this sample.
     */
    public static void main(String[] args) {
        try {

            words wrd = new words();
            HttpClient cl = new HttpClient();
            String url = "http://192.168.2.4/shock";


           String st = null;
            // Replace below with your own subscription key
            String speechSubscriptionKey = "5887f5b4685d49fd8a31d5c1d8c13b3f";
            // Replace below with your own service region (e.g., "westus").
            String serviceRegion = "northeurope";

            int exitCode = 1;
            SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            config.setOutputFormat(OutputFormat.Simple);
            config.enableDictation();

            //SpeechRecognizer recognizer = new SpeechRecognizer(config);
            SpeechRecognizer recognizer = new SpeechRecognizer(config, "ru-RU");


            System.out.println("Say something...");

            recognizer.recognized.addEventListener((s, e) -> {
                if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                    // Do something with the recognized text
                  //  e.getResult().getText();
                   System.out.println("Unexpected exception: " + e.getResult().getText());
                  if (wrd.checkForBadWords(e.getResult().getText())>0) {
                      try {
                          cl.request(url);
                      } catch (Exception exception) {
                          exception.printStackTrace();
                      }
                  }




                    //  System.out.println("RECO OK");
                }
            });


            recognizer.canceled.addEventListener((s, e) -> {
                System.out.println("CANCELED: Reason=" + e.getReason());

                if (e.getReason() == CancellationReason.Error) {
                    System.out.println("CANCELED: ErrorCode=" + e.getErrorCode());
                    System.out.println("CANCELED: ErrorDetails=" + e.getErrorDetails());
                    System.out.println("CANCELED: Did you update the subscription info?");
                }
            });

            recognizer.sessionStarted.addEventListener((s, e) -> {
                System.out.println("\n    Session started event.");
            });



            recognizer.startContinuousRecognitionAsync().get();


        } catch (Exception ex) {
            System.out.println("Unexpected exception: " + ex.getMessage());

            assert(false);
            System.exit(1);
        }
    }



    public void checkforprofanity(String str) {


    }
}


