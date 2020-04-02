package com.epam.prykhodko.mananger;

import com.epam.prykhodko.entity.User;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessManager {

    private static final Pattern PATTERN = Pattern.compile("^(/)(.+)((/\\*)|$)");

    public Optional<Entry<String, List<String>>> checkUrl(Map<String, List<String>> urlMap, String url) {
        return urlMap.entrySet().stream()
            .filter(e -> {
                boolean res = false;
                Matcher matcher = PATTERN.matcher(e.getKey().trim());

                if (matcher.find()) {
                    res = url.contains(matcher.group(2));
                }

                return res;
            })
            .findFirst();
    }

    public boolean checkAccess(User user, List<String> roles) {
        return roles.contains(user.getRole().toString().toLowerCase());
    }

}
