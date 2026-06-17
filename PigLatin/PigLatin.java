public class PigLatin {

    public static String toPigLatin(String phrase) {
        String[] words = phrase.split(" ");
        String pigLatinPhrase = "";

        for (int i = 0; i < words.length; i++) {
            String pigWord = translateWordToPigLatin(words[i]);

            if (i == 0) {
                pigWord = pigWord.substring(0, 1).toUpperCase() + pigWord.substring(1).toLowerCase();
            } else {
                pigWord = pigWord.toLowerCase();
            }

            pigLatinPhrase += pigWord;

            if (i < words.length - 1) {
                pigLatinPhrase += " ";
            }
        }

        return pigLatinPhrase;
    }

    public static String translateWordToPigLatin(String word) {
        char firstChar = Character.toLowerCase(word.charAt(0));
        
        if (startsWithVowel(word)) {
            return word + "yay";
        } else {
            int vowelIndex = findFirstVowelIndex(word);
            if (vowelIndex == -1) {
                return word + "ay";
            } else {
                String start = word.substring(0, vowelIndex);
                String end = word.substring(vowelIndex);
                return end + start + "ay";
            }
        }
    }

    private static boolean startsWithVowel(String word) {
        char first = Character.toLowerCase(word.charAt(0));
        return (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u');
    }

    private static int findFirstVowelIndex(String word) {
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                return i;
            }
        }
        return -1;
    }
}