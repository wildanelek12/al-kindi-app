package id.codes.al_kindi_app.Model;

public class Ayat {
    String ar,id,nomor,tr;


    public Ayat(String ar, String id, String nomor, String tr) {
        this.ar = ar;
        this.id = id;
        this.nomor = nomor;
        this.tr = tr;
    }

    public String getAr() {
        return ar;
    }

    public String getId() {
        return id;
    }

    public String getNomor() {
        return nomor;
    }

    public String getTr() {
        return tr;
    }

}
