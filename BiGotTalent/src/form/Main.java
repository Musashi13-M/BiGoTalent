package form;

public class Main {
    public static void main(String[] args) {
        // Menggunakan Lambda Expression agar lebih ringkas
        java.awt.EventQueue.invokeLater(() -> {
            new FormLogin().setVisible(true);
        });
    }
}