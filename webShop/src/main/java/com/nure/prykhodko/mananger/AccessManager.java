package com.nure.prykhodko.mananger;

import com.nure.prykhodko.entity.UserRole;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class AccessManager {

    private Map<String, List<String>> urlMap;

    public AccessManager(Map<String, List<String>> urlMap) {
        this.urlMap = urlMap;
    }

    public boolean checkUrl(String url) {
        return urlMap.entrySet().stream()
            .anyMatch(e -> url.matches(e.getKey().trim()));
    }

    public boolean checkAccess(UserRole role, String url) {
        Optional<Entry<String, List<String>>> roles = urlMap.entrySet().stream().filter(e -> url.matches(e.getKey().trim())).findFirst();

        if (!roles.isPresent()) {
            return true;
        }

        List<String> roleList = roles.get().getValue();
        return roleList.contains(role.toString().toLowerCase());
    }

}
