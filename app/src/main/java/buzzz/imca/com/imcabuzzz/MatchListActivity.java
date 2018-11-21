package buzzz.imca.com.imcabuzzz;

import android.content.Intent;
import android.service.autofill.FieldClassification;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MatchListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);
        RecyclerView list=findViewById(R.id.match_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        FirebaseRecyclerOptions<Matches> options=new FirebaseRecyclerOptions.Builder<Matches>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("matches"),Matches.class)
                .build();
        FirebaseRecyclerAdapter<Matches,MatchesViewHolder> f=new FirebaseRecyclerAdapter<Matches, MatchesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MatchesViewHolder holder, int position, @NonNull Matches model) {
                holder.setTitle(model.getTitle());
                holder.setResult(model.getFeedback());
            }

            @NonNull
            @Override
            public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new MatchesViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.match_row,viewGroup,false));
            }
        };
        f.startListening();
        list.setAdapter(f);
    }

    public void goCurrentMatch(View view) {
        startActivity(new Intent(this,CurrentMatchActivity.class));
    }
}
