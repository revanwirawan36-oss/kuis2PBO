// Custom exception: kuota kendaraan tidak mencukupi (contoh: OBJ-B diminta 5, kuota 2).
public class KuotaHabisException extends Exception {
    public KuotaHabisException(String pesan) {
        super(pesan);
    }
}
