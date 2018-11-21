package buzzz.imca.com.imcabuzzz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CurrentMatchActivity extends AppCompatActivity {
    private String batName,bowlName,matchId;
    private TextView batText,bowlText;
    private TextView batScoreText,bowlScoreText;
    private TextView batWicketText,bowlWicketText;
    private TextView overText;
    private RecyclerView batList,bowlList,overList;
    private DatabaseReference curRef,matchRef;
    private LinearLayout lin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_match);
        batText=findViewById(R.id.batting_team_name);
        bowlText=findViewById(R.id.bowling_team_name);
        batScoreText=findViewById(R.id.batting_team_score);
        bowlScoreText=findViewById(R.id.bowling_team_score);
        batWicketText=findViewById(R.id.batting_team_wicket);
        bowlWicketText=findViewById(R.id.bowling_team_wicket);
        batList=findViewById(R.id.batsman_list);
        batList.setHasFixedSize(true);
        batList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        bowlList=findViewById(R.id.bowler_list);
        bowlList.setHasFixedSize(true);
        bowlList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        overList=findViewById(R.id.over_list);
        overList.setLayoutManager(new GridLayoutManager(this,6));
        lin=findViewById(R.id.lin);
        overText=findViewById(R.id.over_ball);
        curRef=FirebaseDatabase.getInstance().getReference().child("current");
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("current")){
                    lin.setVisibility(View.GONE);
                    curRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            curRef.removeEventListener(this);
                            batName=dataSnapshot.child("batting").getValue().toString();
                            bowlName=dataSnapshot.child("bowling").getValue().toString();
                            batText.setText(batName);
                            bowlText.setText(bowlName);
                            String ball=dataSnapshot.child("ball").getValue().toString();
                            overText.setText(Integer.parseInt(ball)/6+"."+Integer.parseInt(ball)%6);
                            matchId=dataSnapshot.child("id").getValue().toString();
                            matchRef = FirebaseDatabase.getInstance().getReference().child("matches");
                            FirebaseRecyclerOptions<BatsMan> options=new FirebaseRecyclerOptions.Builder<BatsMan>()
                                    .setQuery(matchRef.child(matchId).child(batName).child("batting"),BatsMan.class)
                                    .build();
                            FirebaseRecyclerAdapter<BatsMan,BatsManViewHolder> f=new FirebaseRecyclerAdapter<BatsMan, BatsManViewHolder>(options) {
                                @Override
                                protected void onBindViewHolder(@NonNull BatsManViewHolder holder, int position, @NonNull BatsMan model) {
                                    holder.setName(model.getName()+(model.isOut()?"":" (Not out)"));
                                    holder.setScore(Integer.toString(model.getScore()));
                                }

                                @NonNull
                                @Override
                                public BatsManViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                                    return new BatsManViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.batsman_row,viewGroup,false));
                                }
                            };
                            f.startListening();
                            batList.setAdapter(f);
                            FirebaseRecyclerOptions<Over> options2=new FirebaseRecyclerOptions.Builder<Over>()
                                    .setQuery(curRef.child("over"),Over.class)
                                    .build();
                            FirebaseRecyclerAdapter<Over,OverViewHolder> f2=new FirebaseRecyclerAdapter<Over, OverViewHolder>(options2) {
                                @Override
                                protected void onBindViewHolder(@NonNull OverViewHolder holder, int position, @NonNull Over model) {
                                    holder.setRun(model.getRun());
                                }

                                @NonNull
                                @Override
                                public OverViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                                    return new OverViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_over_row,viewGroup,false));
                                }
                            };
                            f2.startListening();
                            overList.setAdapter(f2);
                            FirebaseRecyclerOptions<Bowler> options3=new FirebaseRecyclerOptions.Builder<Bowler>()
                                    .setQuery(matchRef.child(matchId).child(bowlName).child("bowling"),Bowler.class)
                                    .build();
                            FirebaseRecyclerAdapter<Bowler,BowlerViewHolder> f3=new FirebaseRecyclerAdapter<Bowler, BowlerViewHolder>(options3) {
                                @Override
                                protected void onBindViewHolder(@NonNull BowlerViewHolder holder, int position, @NonNull Bowler model) {
                                   holder.setName(model.getName());
                                   holder.setNumber(Integer.toString(position+1));
                                   holder.setExtra(Integer.toString(model.getExtra()));
                                   holder.setRun(Integer.toString(model.getExtra()+model.getScore()));
                                   holder.setWicket(Integer.toString(model.getWicket()));
                                }

                                @NonNull
                                @Override
                                public BowlerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                                    return new BowlerViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.bowler_row,viewGroup,false));
                                }
                            };
                            f3.startListening();
                            bowlList.setAdapter(f3);
                            matchRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    matchRef.removeEventListener(this);
                                    if (dataSnapshot.hasChild(matchId)) {
                                        if(dataSnapshot.child(matchId).hasChild(batName) && dataSnapshot.child(matchId).hasChild(bowlName) ) {
                                            String score1 = dataSnapshot.child(matchId).child(batName).child("score").getValue().toString();
                                            String score2 = dataSnapshot.child(matchId).child(bowlName).child("score").getValue().toString();
                                            batScoreText.setText(score1);
                                            bowlScoreText.setText(score2);
                                            String wicket1 = dataSnapshot.child(matchId).child(batName).child("wicket").getValue().toString();
                                            String wicket2 = dataSnapshot.child(matchId).child(bowlName).child("wicket").getValue().toString();
                                            batWicketText.setText(wicket1);
                                            bowlWicketText.setText(wicket2);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    lin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
