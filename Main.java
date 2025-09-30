import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String template = "Yesterday at the <place>, I <verb> a <adjective> <noun> <adverb>, which made everyone feel <emotion>.";

    System.out.println("place: ");
    String place = scanner.nextLine();
    System.out.println("verb: ");
    String verb = scanner.nextLine();
    System.out.println("adjective: ");
    String adjective = scanner.nextLine();
    System.out.println("noun: ");
    String noun = scanner.nextLine();
    System.out.println("adverb: ");
    String adverb = scanner.nextLine();
    System.out.println("emotion: ");
    String emotion = scanner.nextLine();
    String finalSentence = "Yesterday at the " + place + ", I " + verb + " a " + adjective + " " + noun + " " + adverb + ", which made everyone feel " + emotion + ".";

    System.out.println(finalSentence);
    SpeakText(finalSentence);

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
