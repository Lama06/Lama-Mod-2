package io.github.lama06.lamamod.version;

public final class ModVersion {
    public int major = 0;
    public int minor = 0;
    public int patch = 0;

    public ModVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     * Erstellt eine ModVersion mithilfe eines Version Tags (zB "v1.2.3")
     *
     * @param versionTag der Version Tag
     */
    public ModVersion(String versionTag) {
        String[] versions = versionTag.replace("v", "").split("\\.");

        try {
            major = Integer.parseInt(versions[0]);
            minor = Integer.parseInt(versions[1]);
            patch = Integer.parseInt(versions[2]);
        } catch(Exception e) {
            System.out.println("Fehler beim lesen des VersionTags: " + versionTag);
        }
    }

    /**
     * Überprüft, ob diese ModVersion älter als eine andere ModVersion ist
     *
     * @param other die andere ModVersion
     * @return Gibt true zurück, wenn diese ModVersion älter ist als die andere.
     * Gibt false zurück, wenn die ModVersionen gleich alt sind.
     */
    public boolean isOlderThan(ModVersion other) {
        if (other.major != major) {
            return other.major > major;
        } else if (other.minor != minor) {
            return other.minor > minor;
        } else if (other.patch != patch) {
            return other.patch > patch;
        } else {
            // Die Versionen sind die selben
            return false;
        }
    }

    /**
     * Gibt die ModVersion als Version Tag zurück (zB v1.2.3)
     */
    public String toVersionTag() {
        return "v" + major + "." + minor + "." + patch;
    }
}