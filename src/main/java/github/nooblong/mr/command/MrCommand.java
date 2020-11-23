package github.nooblong.mr.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.nooblong.mr.MusicProcessor;
import github.nooblong.mr.net.MessageCommandUpload;
import github.nooblong.mr.net.SimpleNetworkHandler;
import github.nooblong.mr.util.OperateFile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.List;
import java.util.Random;

import static net.minecraft.util.math.MathHelper.clamp;

public class MrCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> mrCommand
                = Commands.literal("mr")
                .requires((commandSource) -> commandSource.hasPermissionLevel(0))
                .then(Commands.literal("upload")
                        .then(Commands.argument("path", StringArgumentType.string())
                        .executes(MrCommand::upLoad))
                )
                .executes(commandContext -> sendMessage(commandContext, "Nothing to say!"));

        dispatcher.register(mrCommand);
    }

    static int upLoad(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        String pathValue = StringArgumentType.getString(commandContext, "path");
        MessageCommandUpload messageCommandUpload = new MessageCommandUpload(pathValue);
        ServerPlayerEntity playerMP = (ServerPlayerEntity) commandContext.getSource().getEntity();
        SimpleNetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> playerMP), messageCommandUpload);
        return 1;
    }

    static int sendMessage(CommandContext<CommandSource> commandContext, String message) throws CommandSyntaxException {
        TranslationTextComponent finalText = new TranslationTextComponent("chat.type.announcement",
                commandContext.getSource().getDisplayName(),
                new StringTextComponent(message));
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