package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static List<String> getGroups(Pattern pattern, String string) {
        Matcher matcher = pattern.matcher(string);
        List<String> groups = new ArrayList<>();
        while(matcher.find()) {
            for(int i=1; i<=matcher.groupCount(); i++) {
                groups.add(matcher.group(i));
            }
        }
        return groups;
    }

}
