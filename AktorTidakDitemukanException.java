// Custom exception: ID penyewa tidak terdaftar (contoh: ACT-99).
public class AktorTidakDitemukanException extends Exception {
    public AktorTidakDitemukanException(String pesan) {
        super(pesan);
    }
}
