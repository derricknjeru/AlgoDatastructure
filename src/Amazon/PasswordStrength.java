package Amazon;

public class PasswordStrength {
    /**
     * Find the password strength.
     * For each substring of the password which contains at least one vowel and one consonant, its strength goes up by 1.
     * vowels={'a', 'e', 'i', 'o', 'u'}, and rest of letters are all consonant.
     * (Only lower alphabet letters)
     *
     * Input:
     * thisisbeautiful
     *
     * output:
     * 6
     *
     * explaination:
     * this, is, be, aut, if, ul
     * input:
     * hackerrank
     *
     * output:
     * 3
     *
     * explaination:
     * hack, er, rank
     * input:
     * aeiou
     *
     * output:
     * 0
     */
    public static void main(String[] args) {
        String password = "aeiou";
        System.out.println(findStrength(password));
    }

    private static int findStrength(String s) {
        int ans = 0;
        char[] arr = s.toCharArray();
        boolean isVowel = false, isConsonant = false;
        for (char c : arr) {
            if (isVowel(c)) {
                isVowel = true;
            } else {
                isConsonant = true;
            }

            if (isVowel && isConsonant) {
                ans++;
                isVowel = isConsonant = false;
            }
        }
        return ans;
    }

    private static boolean isVowel(char c) {
        String vowels = "aeiou";
        return vowels.indexOf(c) != -1;
    }
}
