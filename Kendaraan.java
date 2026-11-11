import java.io.Serializable;

// Superclass abstrak untuk semua jenis kendaraan (Inheritance).
public abstract class Kendaraan implements Serializable {
    private static final long serialVersionUID = 1L;

    private String kode;
    private String nama;
    private int kuota;

    public Kendaraan(String kode, String nama, int kuota) {
        this.kode = kode;
        this.nama = nama;
        this.kuota = kuota;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        this.kuota = kuota;
    }

    // Mengurangi kuota; melempar exception jika kuota tidak cukup.
    public void kurangiKuota(int jumlah) throws KuotaHabisException {
        if (jumlah > kuota) {
            throw new KuotaHabisException("Kuota " + kode + " (" + nama + ") tidak cukup. Sisa: "
                    + kuota + ", diminta: " + jumlah);
        }
        kuota -= jumlah;
    }

    // Polymorphism: tiap jenis kendaraan punya tarif sendiri.
    public abstract double getTarifPerUnit();

    public abstract String getKategori();

    public double hitungBiaya(int jumlah) {
        return getTarifPerUnit() * jumlah;
    }

    @Override
    public String toString() {
        return kode + " - " + nama + " [" + getKategori() + "] sisa kuota: " + kuota;
    }
}
