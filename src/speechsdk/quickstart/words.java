package speechsdk.quickstart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class words {
    private int fnd = 0;
    private int foundInTotal = 0;
    private  String[] profanity_dic = new String[0];
    private  List<String> prof_dic = new ArrayList<>();

    public void loadProfanityDictionary() {
    String str = null;
    
        try (BufferedReader reader = new BufferedReader(new FileReader("E:\\java2\\microsoft_speech\\jre\\from-microphone\\target\\dict\\profanity_dic.txt")))  {
            while((str = reader.readLine())!=null)
            {
            prof_dic.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public int checkForBadWords2(String str)
    {
        fnd = 0;
        str.toLowerCase();

        str.replaceAll("\\pP", "");

        String[] words = str.replaceAll("\\pP", "").split("\\s");

        if ( words.length>1 ) {

            for (int i=0; i< words.length; i++){
                if (prof_dic.contains(words[i].toLowerCase())){
                    fnd++;
                    foundInTotal++;
                    System.out.print("(Распознано: " + words[i].toLowerCase() +")\n");
                    // System.out.println("Распознано: " + words[i].toLowerCase() + " Всего: " + foundInTotal);
                }
            }
        }
        else if (words.length==1) {

            if (prof_dic.contains(words[0].toLowerCase())) {
                fnd++;
                foundInTotal++;
                System.out.print("(Распознано: " + words[0].toLowerCase() + ")\n");
            }
            } 
        else { 
            System.out.println(" Error with checking word in dictionary "); 
        }
        
        if (fnd>0)
        {
            return 1;
        }
        fnd = 0;

        return 0;
    }
}

