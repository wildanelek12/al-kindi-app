package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import id.codes.al_kindi_app.Adapter.AyatAdapter;
import id.codes.al_kindi_app.Adapter.QuranAdapter;
import id.codes.al_kindi_app.Model.Ayat;
import id.codes.al_kindi_app.Model.Quran;

public class QuranContentActivity extends AppCompatActivity {
    @BindView(R.id.rv_ayat)
    RecyclerView rv_ayat;
    ArrayList<Ayat> listAyat; // Create an ArrayList object
    String url = "https://api.npoint.io/99c279bb173a6e28359c/surat/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_content);

        url+=getIntent().getStringExtra("nomor");
        listAyat = new ArrayList<Ayat>();

        rv_ayat = findViewById(R.id.rv_ayat); //findId recyclerView yg ada pada activity_read_all.xml

        rv_ayat.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        rv_ayat.setLayoutManager(new LinearLayoutManager(this));

        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi FAN
        getData(); // pemanggilan fungsi get data



    }

    private void getData() {
        AndroidNetworking.get(url)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(QuranContentActivity.this, "200ok", Toast.LENGTH_SHORT).show();

                        try {
                            for (int i = 0;i<response.length();i++){
                                JSONObject data = response.getJSONObject(i);
                                listAyat.add(new Ayat(
                                        data.getString("ar"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                        data.getString("id"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                        data.getString("nomor"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                        data.getString("tr") //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                ));
                            }
                            AyatAdapter adapter = new AyatAdapter(QuranContentActivity.this,listAyat);
                            rv_ayat.setAdapter(adapter);
                        }
                        catch (JSONException e) {
                            Toast.makeText(QuranContentActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }



                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(QuranContentActivity.this, anError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}