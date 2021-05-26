package id.codes.al_kindi_app.Model;

public class Feed {
    String kategori,judul,tanggal_post;
    int like;

    public Feed(){
        
    }
    public Feed(String kategori, String judul, String tanggal_post, int like) {
        this.kategori = kategori;
        this.judul = judul;
        this.tanggal_post = tanggal_post;
        this.like = like;
    }

    public String getKategori() {
        return kategori;
    }

    public String getJudul() {
        return judul;
    }

    public String getTanggal_post() {
        return tanggal_post;
    }

    public int getLike() {
        return like;
    }
}
