package buzzz.imca.com.imcabuzzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MatchListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);
    }

    public void goCurrentMatch(View view) {
        startActivity(new Intent(this,CurrentMatchActivity.class));
    }
}
