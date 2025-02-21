package com.github.sejoslaw.multiversecraft.commands;

import com.github.sejoslaw.multiversecraft.MultiverseCraft;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.DimensionArgumentType;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;
import java.util.Set;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class MultiverseCraftTeleportCommand implements ModInitializer {
    public void onInitialize() {
        MultiverseCraft.LOGGER.info("Initializing MultiverseCraft Teleport Command (mvctp)...");
        CommandRegistrationCallback.EVENT.register(MultiverseCraftTeleportCommand::registerCommands);
        MultiverseCraft.LOGGER.info("Finished initializing MultiverseCraft Teleport Command (mvctp)");
    }

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("mvctp")
                .requires(source -> source.hasPermissionLevel(2))
                .then(argument("dimension", DimensionArgumentType.dimension())
                        .executes(context -> teleportToDimensionAtCurrentPosition(context.getSource(), DimensionArgumentType.getDimensionArgument(context, "dimension")))
                        .then(argument("x", DoubleArgumentType.doubleArg())
                                .then(argument("y", DoubleArgumentType.doubleArg())
                                        .then(argument("z", DoubleArgumentType.doubleArg())
                                                .executes(context -> teleportToDimensionAndPosition(
                                                        context.getSource(),
                                                        DimensionArgumentType.getDimensionArgument(context, "dimension"),
                                                        DoubleArgumentType.getDouble(context, "x"),
                                                        DoubleArgumentType.getDouble(context, "y"),
                                                        DoubleArgumentType.getDouble(context, "z")
                                                ))
                                        )
                                )
                        )
                )
        );
    }

    private static int teleportToDimensionAtCurrentPosition(ServerCommandSource source, ServerWorld dimension) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrThrow();
        Vec3d pos = player.getPos();

        return teleportPlayer(player, dimension, pos.getX(), pos.getY(), pos.getZ());
    }

    private static int teleportToDimensionAndPosition(ServerCommandSource source, ServerWorld dimension, double x, double y, double z) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getPlayerOrThrow();
        return teleportPlayer(player, dimension, x, y, z);
    }

    private static int teleportPlayer(ServerPlayerEntity player, ServerWorld dimension, double x, double y, double z) {
        Set<PositionFlag> positionFlags = EnumSet.of(PositionFlag.X, PositionFlag.Y, PositionFlag.Z, PositionFlag.Y_ROT, PositionFlag.X_ROT);
        player.teleport(dimension, x, y, z, positionFlags, player.getYaw(), player.getPitch(), false);
        return 1;
    }
}
