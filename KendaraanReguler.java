public class KendaraanReguler extends Kendaraan {
    private static final long serialVersionUID = 1L;

    public KendaraanReguler(String kode, String nama, int kuota) {
        super(kode, nama, kuota);
    }

    @Override
    public double getTarifPerUnit() {
        return 150000;
    }

    @Override
    public String getKategori() {
        return "Reguler";
    }
}
