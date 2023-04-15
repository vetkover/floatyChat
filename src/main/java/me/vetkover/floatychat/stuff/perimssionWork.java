package me.vetkover.floatychat.stuff;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

public class perimssionWork {
    public static boolean userHasPermission(String user, String permission){
        User playerLuck = LuckPermsProvider.get().getUserManager().getUser(user);
        Boolean access = playerLuck.getCachedData().getPermissionData().checkPermission(permission).asBoolean() || playerLuck.getCachedData().getPermissionData().checkPermission("floatychat.*").asBoolean();
        return  access;
    }
}
