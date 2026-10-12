package speechsdk.quickstart;


import com.microsoft.cognitiveservices.speech.*;

import java.util.Date;

public class Thr extends Thread {

    private static final String SHOCK_URL = "http://192.168.2.4/shock";

    // Held as fields so the native recognizer is not garbage-collected (which would
    // stop recognition) while this thread is alive, and can be closed on shutdown.
    private SpeechConfig config;
    private SpeechRecognizer recognizer;

    @Override
    public void run() {

        try {

            Words wrd = new Words();
            wrd.loadProfanityDictionary();
            HttpClient cl = new HttpClient();

            String speechSubscriptionKey = System.getenv("SPEECH_KEY");
            String serviceRegion = System.getenv("SPEECH_REGION");
            if (serviceRegion == null || serviceRegion.isEmpty()) {
                serviceRegion = "northeurope";
            }
            if (speechSubscriptionKey == null || speechSubscriptionKey.isEmpty()) {
                System.out.println("Set the SPEECH_KEY environment variable to your Azure Speech subscription key.");
                return;
            }

            config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            config.setOutputFormat(OutputFormat.Simple);
            config.enableDictation();

            recognizer = new SpeechRecognizer(config, "ru-RU");
            System.out.println(String.format("Started @  %tR", new Date()));

            System.out.println("Say something...");

            recognizer.recognized.addEventListener((s, e) -> {
                if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                    // Do something with the recognized text
                    System.out.println("Recognized: " + e.getResult().getText());

                    if (wrd.containsBadWord(e.getResult().getText())) {
                        try {
                            cl.request(SHOCK_URL);
                        } catch (Exception exception) {
                            System.out.println("Shock request failed: " + exception.getMessage());
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
            ex.printStackTrace();
        }
    }

    /** Stops recognition and releases the native Speech SDK resources. */
    public void shutdown() {
        try {
            if (recognizer != null) {
                recognizer.stopContinuousRecognitionAsync().get();
                recognizer.close();
            }
            if (config != null) {
                config.close();
            }
        } catch (Exception ex) {
            System.out.println("Error during shutdown: " + ex.getMessage());
        }
    }
}
