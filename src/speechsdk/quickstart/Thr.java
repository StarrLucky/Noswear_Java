package speechsdk.quickstart;


import com.microsoft.cognitiveservices.speech.*;

import java.util.Date;

public class Thr extends Thread {

    @Override
    public void run() {

        try {

            words wrd = new words();
            wrd.loadProfanityDictionary();
            HttpClient cl = new HttpClient();
            Date date = new Date();
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
            System.out.println( String.format("Started @  %tR", date));

            System.out.println("Say something...");

            recognizer.recognized.addEventListener((s, e) -> {
                if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                    // Do something with the recognized text
                    System.out.println("Recognized: " + e.getResult().getText());

                    if (wrd.checkForBadWords2(e.getResult().getText())>0) {
                        try {
                            cl.request(url);
                        } catch (Exception exception) {
                            System.out.println("Exception exception1");
                        }
                    }
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

            recognizer.sessionStopped.addEventListener((s, e)-> {
                System.out.println("\n    Session end event.");
            });

            recognizer.speechEndDetected.addEventListener((s, e)-> {
                System.out.println("\n    Speech end detected event.");
            });






            recognizer.startContinuousRecognitionAsync().get();




        } catch (Exception ex) {
            System.out.println("Unexpected exception: " + ex.getMessage());


        }
    }
}

