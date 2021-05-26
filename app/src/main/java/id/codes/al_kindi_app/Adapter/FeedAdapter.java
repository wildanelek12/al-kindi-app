package id.codes.al_kindi_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import id.codes.al_kindi_app.Model.Feed;
import id.codes.al_kindi_app.R;

public class FeedAdapter extends FirebaseRecyclerAdapter<Feed, FeedAdapter.feedViewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FeedAdapter(@NonNull FirebaseRecyclerOptions<Feed> options) {
        super(options);
    }

    @NonNull
    @Override
    public feedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_feed, parent, false);
        return new FeedAdapter.feedViewholder(view);
    }
    @Override
    protected void onBindViewHolder(@NonNull feedViewholder holder, int position, @NonNull Feed model) {
        holder.tv_kategori.setText(model.getKategori());
        holder.tv_title.setText(model.getJudul());
        holder.tv_post_time.setText(model.getTanggal_post());
        holder.tv_total_like.setText(String.valueOf(model.getLike()));
    }

    class feedViewholder extends RecyclerView.ViewHolder {
        TextView tv_kategori, tv_title, tv_post_time,tv_total_like;

        public feedViewholder(@NonNull View itemView) {
            super(itemView);
            tv_kategori = itemView.findViewById(R.id.tv_category_item);
            tv_title = itemView.findViewById(R.id.tv_title_item);
            tv_post_time = itemView.findViewById(R.id.tv_post_time);
            tv_total_like = itemView.findViewById(R.id.tv_total_like);
        }


    }

}
