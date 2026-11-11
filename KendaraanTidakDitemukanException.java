// Custom exception: kode kendaraan tidak terdaftar.
public class KendaraanTidakDitemukanException extends Exception {
    public KendaraanTidakDitemukanException(String pesan) {
        super(pesan);
    }
}
