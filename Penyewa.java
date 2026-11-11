import java.io.Serializable;

// Entitas aktor: orang yang menyewa kendaraan.
public class Penyewa implements Serializable {
    private static final long serialVersionUID = 1L;

    // Encapsulation: semua field private, akses lewat getter/setter
    private String id;
    private String nama;

    public Penyewa(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return id + " - " + nama;
    }
}
