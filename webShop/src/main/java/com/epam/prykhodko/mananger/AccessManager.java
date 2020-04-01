package com.epam.prykhodko.mananger;

import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.exception.NoAccessRightsException;
import com.epam.prykhodko.exception.NoUserLoginException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class AccessManager {

    private static final Pattern PATTERN = Pattern.compile("^(/)(.+)((/\\*)|$)");
    private Map<String, List<String>> urlMap;
    private User user;
    private String url;

    public AccessManager(String url, Map<String, List<String>> urlMap, User user) {
        this.url = url;
        this.urlMap = urlMap;
        this.user = user;
    }

    public boolean checkAccess() throws NoAccessRightsException, NoUserLoginException {
        boolean pass = false;
        String userLogin = Objects.nonNull(user) ? user.getLogin() : StringUtils.EMPTY;

        Optional<Entry<String, List<String>>> key = urlMap.entrySet().stream()
            .filter(e -> {
                boolean res = false;
                Matcher matcher = PATTERN.matcher(e.getKey().trim());

                if (matcher.find()) {
                    res = url.contains(matcher.group(2));
                }

                return res;
            })
            .findFirst();

        if (!key.isPresent()) {
            return true;
        }

        if (Objects.nonNull(user)) {
            List<String> roles = key.get().getValue();
            pass = roles.contains(user.getRole().toString().toLowerCase());
        }

        if (!pass && StringUtils.EMPTY.equals(userLogin)) {
            throw new NoUserLoginException();
        }

        if (!pass) {
            throw new NoAccessRightsException();
        }

        return true;
    }

}
