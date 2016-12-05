package pl.lodz.p.iis.library;

/**
 * @Author Mateusz Wieczorek on 05.12.2016.
 */
public class EncryptionFileRQ {

    private String name;
    private String encryptionKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    @Override
    public String toString() {
        return "EncryptionFileRQ{" +
                "name='" + name + '\'' +
                ", encryptionKey='" + encryptionKey + '\'' +
                '}';
    }
}
