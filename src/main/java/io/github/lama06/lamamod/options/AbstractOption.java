package io.github.lama06.lamamod.options;

import com.google.gson.JsonElement;
import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.events.MessageSentCallback;

import java.util.Locale;

/**
 * Stellt eine Einstellung der Mod dar
 *
 * @param <T> Der Typ der Option, wie zum Beispiel Boolean oder Integer
 */
public abstract class AbstractOption<T> implements MessageSentCallback {
    private final String name;
    private final String description;
    private final String serializedName;
    private final String chatName;
    private T defaultValue;
    private T value;

    /**
     * @param name           der Name der Option, der dem Spieler angezeigt wird
     * @param description    eine Beschreibung, was die Option bewirkt
     * @param serializedName der Name der beim serialisieren in JSON verwendet wird
     * @param chatName       der Name mit dem die Option im Chat geändert werden kann
     * @param defaultValue   der Standard Wert der Einstellung
     */
    protected AbstractOption(String name, String description, String serializedName, String chatName, T defaultValue) {
        this.name = name;
        this.description = description;
        this.serializedName = serializedName;
        this.chatName = chatName;
        this.defaultValue = defaultValue;
    }

    /**
     * Muss implementiert werden und die Einstellung zu einem JsonElement konvertieren
     *
     * @return die Einstellung als ein JsonElement
     */
    public abstract JsonElement toJson();

    /**
     * Muss implementiert werden und die Einstellung aus einem JsonElement laden
     *
     * @param json das JsonElement
     */
    public abstract void fromJson(JsonElement json);

    /**
     * Gibt den Wert der Einstellung zurück.
     * Wenn noch kein Wert gesetzt wurde, wird der Standard Wert verwendet
     *
     * @return den Wert der Einstellung
     */
    public T getValue() {
        if (value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }

    /**
     * Ändert den Wert der Einstellung
     *
     * @param value      der neue Wert der Einstellung
     * @param saveToFile gibt an, ob die Änderung mit der Einstellungen Datei synchronisiert werden soll
     */
    public void setValue(T value, boolean saveToFile) {
        this.value = value;
        if(saveToFile) {
            LamaMod.options.save();
        }
    }

    public void setValue(T value) {
        setValue(value, true);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSerializedName() {
        return serializedName;
    }

    public String getChatName() {
        return chatName.toLowerCase(Locale.ROOT);
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }
}
