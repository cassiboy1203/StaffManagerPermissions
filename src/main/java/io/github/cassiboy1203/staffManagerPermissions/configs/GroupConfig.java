package io.github.cassiboy1203.staffManagerPermissions.configs;

import com.google.inject.Singleton;
import io.github.cassiboy1203.staffManagerPermissions.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Singleton
public class GroupConfig extends CustomConfig implements IGroupConfig{

    public GroupConfig() {
        this.fileName = "groups.yml";
    }

    @Override
    public List<Group> getGroups() {
        var groupsSection = config.getConfigurationSection("groups");
        var groupMap = new HashMap<Group, List<String>>();

        for (var groupName : groupsSection.getKeys(false)){
            var groupSection = groupsSection.getConfigurationSection(groupName);

            var isDefault = groupSection.getBoolean("default");
            var permissions = groupSection.getStringList("permissions");
            var inheritance = groupSection.getStringList("inheritance");

            var group = new Group(groupName, isDefault, permissions, new ArrayList<>());
            groupMap.put(group, inheritance);
        }

        for (var groupEntrySet : groupMap.entrySet()){
            var group = groupEntrySet.getKey();
            var inheritedGroups = new ArrayList<Group>();
            for (var inheritedGroup: groupEntrySet.getValue()){
                groupMap.keySet().stream().filter(groupValue -> groupValue.name().equalsIgnoreCase(inheritedGroup)).findAny().ifPresent(inheritedGroups::add);
            }
            group.inheritedGroups().addAll(inheritedGroups);
        }

        return groupMap.keySet().stream().toList();
    }
}
