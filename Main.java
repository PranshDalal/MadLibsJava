import java.util.Locale;
import javax.speech.Central;
import javax.speech.EngineList;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.SynthesizerProperties;
import javax.speech.synthesis.Voice;

import java.util.Scanner;

public class Main {
//////
//Made this in order to make the whole process les sline intensive 
    public static String ask(Scanner scanner, String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    //String template = "Yesterday at the <place>, I <verb> a <adjective> <noun> <adverb>, which made everyone feel <emotion>.";

    String place = ask(scanner, "place");
    String verb = ask(scanner, "verb (present tense)");
    String verbPast = ask(scanner, "verb (past tense)");
    String adjective = ask(scanner, "adjective");
    String noun = ask(scanner, "noun");
    String adverb = ask(scanner, "adverb");
    String emotion = ask(scanner, "emotion");
    String event = ask(scanner, "event");
    String name = ask(scanner, "name (person’s name)");
    String animal = ask(scanner, "animal");
    String object = ask(scanner, "object (thing)");
    String relation = ask(scanner, "relation (e.g., friend, teacher, cousin)");
    String celebrity = ask(scanner, "celebrity");

   // String finalSentence = "Yesterday at the " + place + ", I " + verb + " a " + adjective + " " + noun + " " + adverb + ", which made everyone feel " + emotion + ".";

  
  //Nice exmaples from Chat on common book madlibs 

    String sentence1 = "Yesterday at the " + place + ", I " + verb + " a " + adjective + " " + noun + " " + adverb + ", which made everyone feel " + emotion + ".";

    String sentence2 = "During the " + event + ", my " + adjective + " friend " + name + " decided to " + verb + " with a " + noun + ", leaving the crowd feeling " + emotion + ".";

    String sentence3 = "At the " + place + ", the " + adjective + " " + animal + " " + verbPast + " " + adverb + " across the " + noun + ", making me feel very " + emotion + ".";

    String sentence4 = "One day, a " + adjective + " " + noun + " tried to " + verb + " my " + object + " " + adverb + ", but I ended up feeling " + emotion + " instead.";

    String sentence5 = "My " + relation + " told me to " + verb + " the " + adjective + " " + noun + " before the " + event + ", and I felt " + emotion + " when I did it " + adverb + ".";

    String sentence6 = "When I visited " + place + ", a " + adjective + " " + celebrity + " asked me to " + verb + " a " + noun + ", which I did " + adverb + ", causing everyone to feel " + emotion + ".";





    
   // SpeakText(finalSentence);
    SpeakText(sentence1);
    SpeakText(sentence2);
    SpeakText(sentence3);
    SpeakText(sentence4);
    SpeakText(sentence5);
    SpeakText(sentence6);

    }

    public static void SpeakText(String text) {
        try {
            //Fixed all thee voices 

            System.out.println(text);

            System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral(
                "com.sun.speech.freetts"
                    + ".jsapi.FreeTTSEngineCentral");

            SynthesizerModeDesc required = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
            EngineList list = Central.availableSynthesizers(required);
            if (list == null || list.size() == 0) {
                System.err.println("No synthesizers available");
                return;
            }

            SynthesizerModeDesc desc = (SynthesizerModeDesc) list.get(0);
            Synthesizer synthesizer = Central.createSynthesizer(desc);
            synthesizer.allocate();
            synthesizer.resume();

            Voice[] voices = desc.getVoices();
            String desiredVoiceName = "kevin16"; // I switched from kevin to 

            Voice chosen = null;
            if (voices != null) {
                for (Voice v : voices) {
                    if (v.getName().equalsIgnoreCase(desiredVoiceName)) {
                        chosen = v;
                        break;
                    }
                }
            }

            if (chosen == null && voices != null && voices.length > 0) {
                chosen = voices[0]; // fallback to first available voice
                System.out.println("Desired voice not found; falling back to: " + chosen.getName());
            } else if (chosen != null) {


                ///
                /// Had this console stetement but ultimatley not needed
                //System.out.println("Using voice: " + chosen.getName());
            }

            if (chosen != null) {
                SynthesizerProperties props = synthesizer.getSynthesizerProperties();
                props.setVoice(chosen);
            }

            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

            synthesizer.deallocate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
