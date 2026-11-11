public class MainRental {
    public static void main(String[] args) {
        SistemRental rental = new SistemRental("Rental Maju Jaya");

        rental.tambahPenyewa("ACT-01", "Eko");
        rental.tambahPenyewa("ACT-02", "Fina");

        rental.tambahKendaraan("OBJ-A", "Mobil SUV Premium", 10);
        rental.tambahKendaraan("OBJ-B", "Motor Matic Reguler", 2);
        rental.tambahKendaraan("OBJ-C", "Minibus Ekonomis", 50);
        System.out.println("--- MEMULAI PEMBACAAN FILE TRANSAKSI ---");

        try {
            rental.jalankanTransaksiDariFile("input_transaksi.txt");
        } catch (Exception e) {
            System.out.println("[SISTEM FATAL ERROR] File gagal dibaca/diproses: " + e.getMessage());
        }
        rental.simpanLaporanAkhir();
    }
}
