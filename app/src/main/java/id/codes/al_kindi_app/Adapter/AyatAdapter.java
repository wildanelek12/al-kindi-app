package id.codes.al_kindi_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.codes.al_kindi_app.Model.Ayat;
import id.codes.al_kindi_app.Model.Quran;
import id.codes.al_kindi_app.R;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.AyatViewHolder> {
    private Context context;
    private ArrayList<Ayat> dataAyat; //inisialisasi List dengan object DataMahasiswa
    public AyatAdapter(Context context,ArrayList<Ayat> dataAyat) {
        this.context = context;
        this.dataAyat = dataAyat;
    }
    @NonNull
    @Override
    public AyatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_ayat, parent, false);
        AyatAdapter.AyatViewHolder holder = new AyatAdapter.AyatViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AyatViewHolder holder, int position) {
        Ayat ayat = dataAyat.get(position);
        holder.tv_number.setText(ayat.getNomor());
        holder.tv_ayat.setText(ayat.getAr());
        holder.tv_arti.setText(ayat.getId());

    }

    @Override
    public int getItemCount() {
        return dataAyat.size(); //mengambil item sesuai urutan
    }

    public class AyatViewHolder extends RecyclerView.ViewHolder {
        TextView tv_number,tv_ayat,tv_arti;
        public AyatViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_ayat = itemView.findViewById(R.id.tv_ayat);
            tv_arti = itemView.findViewById(R.id.tv_arti);
        }
    }
}
