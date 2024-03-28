package azure.ai.vision.imageanalysis.samples;


import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.*;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class TextToSpeech {
    private static String speechKey = "0fc9f9569a3f48a4864430bd7e2d588c";
    private static String speechRegion = "eastus";
    public static void dospeak(String text) throws ExecutionException, InterruptedException {
        SpeechConfig config = SpeechConfig.fromSubscription(speechKey, speechRegion);
        System.out.println("Getting Audio for the text...");
        // Creates a speech synthesizer using file as audio output.
        // Replace with your own audio file name.
        String fileName = "outputaudio.wav";
        AudioConfig fileOutput = AudioConfig.fromWavFileOutput(fileName);

        // Creates a speech synthesizer using a wave file as audio output.
        SpeechSynthesizer synthesizer = new SpeechSynthesizer(config, fileOutput);
        {
            SpeechSynthesisResult result = synthesizer.SpeakTextAsync(text).get();
            // Checks result.
            if (result.getReason() == ResultReason.SynthesizingAudioCompleted) {
                System.out.println("The audio was saved to [" + fileName + "]");
            }
            else if (result.getReason() == ResultReason.Canceled) {
                SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(result);
                System.out.println("CANCELED: Reason=" + cancellation.getReason());

                if (cancellation.getReason() == CancellationReason.Error) {
                    System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                    System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                    System.out.println("CANCELED: Did you update the subscription info?");
                }
            }
            result.close();
        }
        synthesizer.close();
        fileOutput.close();
    }

}
