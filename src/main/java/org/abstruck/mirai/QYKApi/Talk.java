package org.abstruck.mirai.QYKApi;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.Objects;

public class Talk extends JRawCommand {
    Talk(){
        super(QYKApi.INSTANCE,"talk");
        setDescription("talk to your robot");
        setPrefixOptional(true);
        setUsage("/talk");
        setPermission(QYKApi.INSTANCE.getParentPermission());
    }

    @Override
    public void onCommand(CommandSender sender, MessageChain messages){
        Objects.requireNonNull(sender.getSubject()).sendMessage(QingYunKeApi.getDialogue(messages.contentToString()));
    }
}
