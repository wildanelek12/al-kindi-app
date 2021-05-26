package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codes.al_kindi_app.Adapter.FeedAdapter;
import id.codes.al_kindi_app.Model.Feed;

public class FeedContentActivity extends AppCompatActivity {
    @BindView(R.id.rv_feed)
    RecyclerView rv_feed;
    FeedAdapter feedAdapter;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_content);
        ButterKnife.bind(this);
        reference = FirebaseDatabase.getInstance().getReference().child("feed");
        rv_feed.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Feed> options
                = new FirebaseRecyclerOptions.Builder<Feed>()
                .setQuery(reference, Feed.class)
                .build();

        feedAdapter = new FeedAdapter(options);
        rv_feed.setAdapter(feedAdapter);
    }
    @Override protected void onStart()
    {
        super.onStart();
        feedAdapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        feedAdapter.stopListening();
    }
}