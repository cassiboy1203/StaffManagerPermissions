package io.github.cassiboy1203.staffManagerPermissions;

import java.util.ArrayList;
import java.util.List;

public record Group(String name, boolean isDefault, List<String> permissions, List<Group> inheritedGroups) {
    public List<String> getPermissionsIncludingInherited(){
        var inheritedPermissions = new ArrayList<String>(permissions);

        for (var group : inheritedGroups) {
            for (var permission : group.getPermissionsIncludingInherited()) {
                if (!inheritedPermissions.contains(permission)) {
                    inheritedPermissions.add(permission);
                }
            }
        }

        return inheritedPermissions;
    }
}
