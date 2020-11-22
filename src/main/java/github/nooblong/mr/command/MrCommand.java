package github.nooblong.mr.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.nooblong.mr.net.MySimpleNetworkHandler;
import github.nooblong.mr.util.OperateFile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;
import java.util.Random;
import java.util.RandomAccess;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.minecraft.util.math.MathHelper.clamp;

/**
 * Created by TGG on 9/02/2020.
 * <p>
 * Adds a command "mbequote"
 * prints a variety of quotes depending on the arguments given
 * <p>
 * Shows a more complicated parsing structure, including mixing different types of literals and arguments
 * <p>
 * mbequote python --> random Monty Python quote
 * <p>
 * mbequote blues --> random Blues Brothers quote
 * <p>
 * mbequote yogi bear --> random Yogi Bear quote
 * mbequote yogi berra --> random Yogi Berra quote
 * mbequote yogi --> "Which Yogi do you mean?"
 * mbequote yogi xxxx --> error
 * <p>
 * mbequote gibberish <lettercount>  --> random bunch of letters, total length lettercount
 * <p>
 * mbequote xxxx --> prints xxxxx (where xxxx is the text typed by the player)
 * <p>
 * mbequote  --> Nothing to say!
 * <p>
 * https://github.com/Mojang/brigadier for syntax
 * <p>
 * My advice to you: be very careful laying out the builder chain.  It is very easy to put a bracket in the wrong place,
 * and this completely throws the syntax out leading to strange parsing errors.
 */
public class MrCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> mrCommand
                = Commands.literal("mr")
                .requires((commandSource) -> commandSource.hasPermissionLevel(0))
                .then(Commands.literal("upload")
                        .then(Commands.argument("path", StringArgumentType.string())
                        .executes(MrCommand::upLoad))
                )
                .executes(commandContext -> sendMessage(commandContext, "Nothing to say!"));  // blank: didn't match a literal or the custommessage argument

        dispatcher.register(mrCommand);
    }

    static int upLoad(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        String pathValue = StringArgumentType.getString(commandContext, "path");
        String path = pathValue;
        List<byte[]> toSend = OperateFile.readOgg(path);
        byte[] lastBytes = toSend.get(toSend.size() - 1);
        for (byte[] bytes : toSend) {
            MySimpleNetworkHandler.sendToServer(OperateFile.getFileName(path), bytes, toSend.size(), lastBytes.length);
        }
        //finish mark
        MySimpleNetworkHandler.sendToServer("finish", new byte[]{1}, toSend.size(), lastBytes.length);
        ClientPlayerEntity player = Minecraft.getInstance().player;
        player.sendChatMessage("upload success");
        commandContext.getSource().getServer().getPlayerList().sendMessage(new StringTextComponent("uploading..."));
        return 1;
    }

    static int sendMessage(CommandContext<CommandSource> commandContext, String message) throws CommandSyntaxException {
        TranslationTextComponent finalText = new TranslationTextComponent("chat.type.announcement", commandContext.getSource().getDisplayName(), new StringTextComponent(message));
        commandContext.getSource().getServer().getPlayerList().sendMessage(finalText);
        return 1;
    }

    static String gibberish(int length) {
        Random random = new Random();
        length = clamp(length, 0, 40);
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; ++i) builder.append((char) ('a' + random.nextInt(26)));
        return builder.toString();
    }


}