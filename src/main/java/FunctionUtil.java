import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FunctionUtil {
    public static String getConsonantVowelInChar(String character) {
        final String[] f = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ",
                "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ",
                "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

        final String[] s = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ",
                "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ",
                "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
        final String[] t = {" ", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ",
                "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ",
                "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ",
                "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
        final int ga = 44032;
        int uni = character.codePointAt(0);

        uni = uni - ga;

        int fn = uni / 588;
        int sn = (uni - (fn * 588)) / 28;
        int tn = uni % 28;

        return tn != 0 ? f[fn] + s[sn] + t[tn]
                : f[fn] + s[sn];
    }

    public static String getConsonantVowel(String str) {
        System.out.println(str);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '(')
                sb.append('(');
            else if(str.charAt(i) == ')')
                sb.append(')');
            else if(str.charAt(i) >= '0' &&  str.charAt(i) <= '9')
                sb.append(str.charAt(i));
            else
                sb.append(getConsonantVowelInChar(String.valueOf(str.charAt(i))));
        }
        return sb.toString();
    }

    public static BufferedReader getAnsiFile(String path) throws IOException {

        return new BufferedReader(
                    new InputStreamReader(Files.newInputStream(Paths.get(path))
                    ,"euc-kr")
        );
    }
}