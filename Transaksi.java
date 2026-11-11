import java.io.Serializable;

// Association: Transaksi menghubungkan satu Penyewa dengan satu Kendaraan.
public class Transaksi implements Serializable {
    private static final long serialVersionUID = 1L;

    private Penyewa penyewa;
    private Kendaraan kendaraan;
    private int jumlah;
    private double totalBiaya;

    public Transaksi(Penyewa penyewa, Kendaraan kendaraan, int jumlah) {
        this.penyewa = penyewa;
        this.kendaraan = kendaraan;
        this.jumlah = jumlah;
        // Polymorphism: biaya dihitung sesuai jenis kendaraan
        this.totalBiaya = kendaraan.hitungBiaya(jumlah);
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    @Override
    public String toString() {
        return penyewa.getNama() + " menyewa " + kendaraan.getNama() + " x" + jumlah
                + " = Rp" + (long) totalBiaya;
    }
}
