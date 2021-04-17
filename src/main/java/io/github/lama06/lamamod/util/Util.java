package io.github.lama06.lamamod.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import io.github.lama06.lamamod.LamaMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.LiteralText;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.world.World;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

public final class Util {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static UUID Hepux06UUID = UUID.fromString("08f0ac27-c134-4324-8b16-6a53c8361c6d");

    public static void sendMsgToPlayer(String msg) {
        client.inGameHud.getChatHud().addMessage( new LiteralText(LamaMod.prefix + msg));
    }

    public static void sendMsgToChat(String msg) {
        client.player.sendChatMessage(msg);
    }

    public static void addMsgToChatHistory(String msg) {
        client.inGameHud.getChatHud().addToMessageHistory(msg);
    }

    /**
     * Schreibt etwas in eine Datei
     *
     * @param file    die Datei
     * @param content der Inhalt, der in die Datei geschrieben werden soll
     */
    public static void writeToFile(File file, String content) throws IOException {
        System.out.println("Schreibe in Datei: " + file.getName());

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.close();
    }

    /**
     * Liest den Inhalt einer Datei
     *
     * @param file die Datei
     * @return den Inhalt der Datei
     */
    public static String readFromFile(File file) throws FileNotFoundException {
        System.out.println("Lese aus Datei: " + file.getName());

        Scanner scanner = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            content.append(line);
        }
        scanner.close();
        return content.toString();
    }

    public static long getHandle() {
        return client.getWindow().getHandle();
    }

    public static boolean isInNether() {
        return client.world.getRegistryKey().equals(World.NETHER);
    }

    public static boolean isBoolean(JsonElement json) {
        if(json == null || !json.isJsonPrimitive()) {
            return false;
        }

        JsonPrimitive primitive = json.getAsJsonPrimitive();
        return primitive.isBoolean();
    }

    public static boolean isNumber(JsonElement json) {
        if(json == null || !json.isJsonPrimitive()) {
            return false;
        }

        JsonPrimitive primitive = json.getAsJsonPrimitive();
        return primitive.isBoolean();
    }

    public static boolean isString(JsonElement json) {
        if(json == null || !json.isJsonPrimitive()) {
            return false;
        }

        JsonPrimitive primitive = json.getAsJsonPrimitive();
        return primitive.isString();
    }

    public static void crashGame(String reason) {
        client.setCrashReport(new CrashReport(reason, new Exception()));
    }

    public static String getCoordinatesString(boolean netherCoordinates) {
        ClientPlayerEntity player = client.player;
        StringBuilder text = new StringBuilder();

        text.append((int) Math.floor(player.getX())).append(" ");
        text.append((int) Math.floor(player.getY())).append(" ");
        text.append((int) Math.floor(player.getZ())).append(" ");

        if(netherCoordinates) {
            if(Util.isInNether()) {
                text.append(" (in der Oberwelt ");
                text.append((int) Math.floor(player.getX() * 8)).append(" ");
                text.append((int) Math.floor(player.getZ() * 8));
                text.append(")");
            } else {
                text.append(" (im Nether ");
                text.append((int) Math.floor(player.getX() / 8)).append(" ");
                text.append((int) Math.floor(player.getZ() / 8));
                text.append(")");
            }
        }

        return text.toString();
    }

    public static void openURL(String url) {
        try {
            net.minecraft.util.Util.getOperatingSystem().open(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void connectToServer(Screen parent, ServerInfo server) {
        client.openScreen(new ConnectScreen(parent, client, server));
    }
}
