import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO: add the main algorithm
        //eventually we can speak the madlibs yay so fun!
        // SpeakText(xxx)
        Scanner scanner = new Scanner(System.in);
        String emptysentence = "Earlier today, during CSA I decided that I finished my program while watcthing a <noun> <verb>, and I thought it made Mr.Baez feel very <adjective>.";
        System.out.println("noun: ");
        String noun = scanner.nextLine();
        System.out.println("verb: ");
        String verb = scanner.nextLine();
        System.out.println("adjective: ");
        String adjective = scanner.nextLine();
        String finalsentence = "Earlier today, during CSA I decided that I finished my program while watcthing a " + noun + " " + verb + ", and I thought it made Mr.Baez feel very " + adjective + ".";
        System.out.println(finalsentence);
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
