import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SistemRental implements Serializable {
    private static final long serialVersionUID = 1L;

    private String namaRental;

    // HashMap (LinkedHashMap agar urutan ID sesuai urutan input)
    private Map<String, Penyewa> daftarPenyewa = new LinkedHashMap<>();
    private Map<String, Kendaraan> daftarKendaraan = new LinkedHashMap<>();
    private ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

    public SistemRental(String namaRental) {
        this.namaRental = namaRental;
    }

    public String getNamaRental() {
        return namaRental;
    }

    public void tambahPenyewa(String id, String nama) {
        daftarPenyewa.put(id, new Penyewa(id, nama));
    }

    // Jenis kendaraan ditentukan dari kata kunci pada nama (Premium/Reguler/Ekonomis).
    public void tambahKendaraan(String kode, String nama, int kuota) {
        Kendaraan k;
        String n = nama.toLowerCase();
        if (n.contains("premium")) {
            k = new KendaraanPremium(kode, nama, kuota);
        } else if (n.contains("reguler")) {
            k = new KendaraanReguler(kode, nama, kuota);
        } else {
            k = new KendaraanEkonomis(kode, nama, kuota);
        }
        daftarKendaraan.put(kode, k);
    }

    // Format baris: ID>ACT-01.KD>OBJ-A.JML>3.
    public void jalankanTransaksiDariFile(String namaFile) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            int nomor = 0;
            while ((baris = br.readLine()) != null) {
                nomor++;
                baris = baris.trim();
                if (baris.isEmpty()) {
                    continue;
                }
                // Error per baris ditangkap di sini, sehingga baris lain tetap diproses.
                try {
                    prosesBaris(baris);
                } catch (AktorTidakDitemukanException | KendaraanTidakDitemukanException
                         | KuotaHabisException e) {
                    System.out.println("[GAGAL] Baris " + nomor + ": " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("[GAGAL] Baris " + nomor + ": jumlah bukan angka valid");
                } catch (IllegalArgumentException e) {
                    System.out.println("[GAGAL] Baris " + nomor + ": " + e.getMessage());
                }
            }
        }
    }

    private void prosesBaris(String baris) throws AktorTidakDitemukanException,
            KendaraanTidakDitemukanException, KuotaHabisException {
        // Buang titik di akhir, lalu pisah tiga bagian dengan titik
        if (baris.endsWith(".")) {
            baris = baris.substring(0, baris.length() - 1);
        }
        String[] bagian = baris.split("\\.");
        if (bagian.length != 3) {
            throw new IllegalArgumentException("format baris salah: " + baris);
        }

        String idPenyewa = ambilNilai(bagian[0], "ID");
        String kodeKendaraan = ambilNilai(bagian[1], "KD");
        int jumlah = Integer.parseInt(ambilNilai(bagian[2], "JML"));

        Penyewa penyewa = daftarPenyewa.get(idPenyewa);
        if (penyewa == null) {
            throw new AktorTidakDitemukanException("Penyewa " + idPenyewa + " tidak terdaftar");
        }
        Kendaraan kendaraan = daftarKendaraan.get(kodeKendaraan);
        if (kendaraan == null) {
            throw new KendaraanTidakDitemukanException("Kendaraan " + kodeKendaraan + " tidak terdaftar");
        }

        kendaraan.kurangiKuota(jumlah); // bisa melempar KuotaHabisException
        Transaksi t = new Transaksi(penyewa, kendaraan, jumlah);
        daftarTransaksi.add(t);
        System.out.println("[OK] " + t);
    }

    // Memecah "KEY>nilai" dan memastikan KEY sesuai harapan.
    private String ambilNilai(String potongan, String kunciDiharapkan) {
        String[] kv = potongan.split(">");
        if (kv.length != 2 || !kv[0].trim().equals(kunciDiharapkan)) {
            throw new IllegalArgumentException("bagian '" + potongan + "' tidak valid, harusnya "
                    + kunciDiharapkan + ">nilai");
        }
        return kv[1].trim();
    }

    public void simpanLaporanAkhir() {
        // 1. Object Serialization ke snapshot.obj
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("snapshot.obj"))) {
            oos.writeObject(this);
            System.out.println("[INFO] Objek berhasil diserialisasi ke snapshot.obj");
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal serialisasi: " + e.getMessage());
        }

        // 2. Laporan teks: daftar kunci (ID saja) lewat keySet()
        try (FileWriter fw = new FileWriter("laporan.txt")) {
            for (String id : daftarPenyewa.keySet()) {
                fw.write(id + "\n");
            }
            for (String kode : daftarKendaraan.keySet()) {
                fw.write(kode + "\n");
            }
            System.out.println("[INFO] Laporan berhasil ditulis ke laporan.txt");
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menulis laporan: " + e.getMessage());
        }
    }
}
