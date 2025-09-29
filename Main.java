import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class Main {

    public static void main(String[] args) {
        //TODO: add the main algorithm
        //eventually we can speak the madlibs yay so fun!
        // SpeakText(xxx)
    }

    public static void SpeakText(String text) {
        try {
            //TODO: try to change the voice 
            System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral(
                "com.sun.speech.freetts"
                    + ".jsapi.FreeTTSEngineCentral");

            Synthesizer synthesizer = Central.createSynthesizer(
                new SynthesizerModeDesc(Locale.US));

            synthesizer.allocate();
            synthesizer.resume();

            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

            synthesizer.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
