public class KendaraanEkonomis extends Kendaraan {
    private static final long serialVersionUID = 1L;

    public KendaraanEkonomis(String kode, String nama, int kuota) {
        super(kode, nama, kuota);
    }

    @Override
    public double getTarifPerUnit() {
        return 400000;
    }

    @Override
    public String getKategori() {
        return "Ekonomis";
    }
}
