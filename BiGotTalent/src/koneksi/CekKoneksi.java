package Koneksi;
import java.sql.Connection;

public class CekKoneksi {
    public static void main(String[] args) {
        Connection c = koneksi.configDB();
        if (c != null) {
            System.out.println("HORE! NetBeans Berhasil Terhubung ke XAMPP!");
        } else {
            System.out.println("GAGAL TERHUBUNG!");
        }
    }
}

