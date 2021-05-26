package id.codes.al_kindi_app.Model;

public class Quran {
    String arti,asma,audio,nama,nomor;
    int ayat;

    public Quran(String arti, String asma, String audio,int ayat, String nama, String nomor) {
        this.arti = arti;
        this.asma = asma;
        this.audio = audio;
        this.ayat = ayat;
        this.nama = nama;
        this.nomor = nomor;

    }

    public String getArti() {
        return arti;
    }

    public String getAsma() {
        return asma;
    }

    public String getAudio() {
        return audio;
    }

    public String getNama() {
        return nama;
    }

    public String getNomor() {
        return nomor;
    }

    public int getAyat() {
        return ayat;
    }
}

