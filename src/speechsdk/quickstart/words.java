package speechsdk.quickstart;

import java.util.Arrays;

public class words {
    private int fnd = 0;
    private int foundInTotal = 0;
    private String[] swr  = new String[] {
            "привет",
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
            "ебаное"

    };

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
}
