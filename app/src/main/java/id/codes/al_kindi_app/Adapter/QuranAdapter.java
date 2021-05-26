package id.codes.al_kindi_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import id.codes.al_kindi_app.Model.Quran;
import id.codes.al_kindi_app.QuranContentActivity;
import id.codes.al_kindi_app.R;

public class QuranAdapter extends RecyclerView.Adapter<QuranAdapter.quranViewHolder>{
    private Context context;
    private ArrayList<Quran> dataSurat; //inisialisasi List dengan object DataMahasiswa
    public QuranAdapter(Context context , ArrayList<Quran> dataSurat) {
        this.context = context;
        this.dataSurat = dataSurat;
    }

    @NonNull
    @Override
    public quranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_quran, parent, false);
        quranViewHolder holder = new quranViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull quranViewHolder holder, int position) {
        Quran surat = dataSurat.get(position);
        holder.tv_number.setText(surat.getNomor());
        holder.tv_title.setText(surat.getNama());
        holder.tv_desc.setText(surat.getArti()+" ("+String.valueOf(surat.getAyat())+" ayat)");
        holder.cl_quran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuranContentActivity.class);
                intent.putExtra("nomor",surat.getNomor());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSurat.size(); //mengambil item sesuai urutan
    }

    class quranViewHolder extends RecyclerView.ViewHolder {
        TextView tv_number, tv_title, tv_desc;
        ConstraintLayout cl_quran;
        public quranViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            cl_quran = itemView.findViewById(R.id.cl_quran);
        }
    }

}
