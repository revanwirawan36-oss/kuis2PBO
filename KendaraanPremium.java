public class KendaraanPremium extends Kendaraan {
    private static final long serialVersionUID = 1L;

    public KendaraanPremium(String kode, String nama, int kuota) {
        super(kode, nama, kuota);
    }

    @Override
    public double getTarifPerUnit() {
        return 750000;
    }

    @Override
    public String getKategori() {
        return "Premium";
    }
}
