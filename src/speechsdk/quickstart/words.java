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
    private String[] swr  = new String[] {
            "привет",
            "гандон",
            "сука",
            "суки",
            "ссанина",
            "блять",
            "блять",
            "хуй",
            "пидор",
            "пидорасы",
            "пидарас",
            "пидорасина",
            "пидорские",
            "дахули",
            "бляяя",
            "бляяять",
            "пиздец",
            "мразь",
            "сучара",
            "жмых",
            "жмыхи",
            "ебаный",
            "ебаные",
            "бляди",
            "заебали",
            "заебал",
            "заебало",
            "заебала",
            "говно",
            "говнище",
            "ебаное",
            "ебаный",
            "ебаный",
            "ебаная",
            "ебанись",
            "ебал",
            "ебашить",
            "ебать"

    };

    public void loadProfanityDictionary() {
    String str = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("E:\\java2\\microsoft_speech\\jre\\from-microphone\\target\\dict\\profanity_dic.txt")))  {

            while((str = reader.readLine())!=null) {
            prof_dic.add(str);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  System.out.println("LIST:");
        //   System.out.println(prof_dic.toString());

    }


    public int checkForBadWords(String str)
    {

        for (int i=1; i< swr.length; i++)
        {
            if (str.toLowerCase().contains(swr[i])) {
                fnd++;
                foundInTotal++;
                System.out.println("Found:" + swr[i] + " Total:" + foundInTotal);
            }

        }

        if (fnd>0)
        {
            fnd = 0;
            return 1;
        }
        fnd = 0;
        return 0;
    }


    public int checkForBadWords2(String str)
    {
        fnd = 0;
        str.toLowerCase();

        str.replaceAll("\\pP", "");

        String[] words = str.replaceAll("\\pP", "").split("\\s");


        for (int i=0; i< words.length; i++){

        if (prof_dic.contains(words[i])){
            fnd++;
            foundInTotal++;
            System.out.println("Found: " + words[i] + " Total: " + foundInTotal);
        }
        }

        if (fnd>0)
        {
            fnd = 0;
            return 1;
        }
        fnd = 0;
    //    System.out.println(" Not found ");
        return 0;
    }


}



