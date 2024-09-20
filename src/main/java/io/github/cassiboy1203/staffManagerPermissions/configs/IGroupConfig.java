package io.github.cassiboy1203.staffManagerPermissions.configs;

import io.github.cassiboy1203.staffManagerPermissions.Group;

import java.util.List;

public interface IGroupConfig extends ICustomConfig{
    List<Group> getGroups();
}
