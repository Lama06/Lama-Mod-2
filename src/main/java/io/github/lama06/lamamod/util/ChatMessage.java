package io.github.lama06.lamamod.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class ChatMessage {
    private final String text;

    public ChatMessage(String text) {
        this.text = text.trim();
    }

    public String getText() {
        return text.toLowerCase(Locale.ROOT);
    }

    public String getPlainText() {
        return text;
    }

    public String[] getArgs() {
        List<String> args = new ArrayList<>();

        for(String arg : getPlainArgs()) {
            args.add(arg.toLowerCase(Locale.ROOT));
        }

        return args.toArray(new String[] {});
    }

    public String[] getPlainArgs() {
        String[] args = text.split(" ");
        return Arrays.copyOfRange(args, 1, args.length);
    }

    public ChatMessage getSubMessage(int from) {
        return new ChatMessage(text.substring(from + 1));
    }
}
