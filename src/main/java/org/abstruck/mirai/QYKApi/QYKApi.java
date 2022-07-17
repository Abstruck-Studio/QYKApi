package org.abstruck.mirai.QYKApi;

import kotlin.Lazy;
import kotlin.LazyKt;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.permission.*;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;

public final class QYKApi extends JavaPlugin {
    public static final QYKApi INSTANCE = new QYKApi();

    private QYKApi() {
        super(new JvmPluginDescriptionBuilder("org.abstruck.mirai.QYKApi", "0.1.0")
                .name("QYKApi")
                .author("Abstruck")
                .build());
    }


    private final Lazy<Permission> QYKApiPermission = LazyKt.lazy(()->{
        try {
            return PermissionService.getInstance().register(
                    permissionId("talk"),
                    "与机器人对话的权限",
                    getParentPermission()
            );
        } catch (PermissionRegistryConflictException e) {
            throw new RuntimeException(e);
        }
    });
    public boolean hasPermission(User usr) {
        PermitteeId pid;
        if (usr instanceof Member) {
            pid = new AbstractPermitteeId.ExactMember(((Member) usr).getGroup().getId(), usr.getId());
        } else {
            pid = new AbstractPermitteeId.ExactUser(usr.getId());
        }
        return PermissionService.hasPermission(pid, QYKApiPermission.getValue());
    }
    @Override
    public void onEnable() {
        CommandManager.INSTANCE.registerCommand(new Talk(),true);
        getLogger().info("Plugin loaded!");
    }
}