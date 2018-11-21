package buzzz.imca.com.imcabuzzz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class BatsManViewHolder extends RecyclerView.ViewHolder {
    TextView batsManName,batsManScore;
    public BatsManViewHolder(@NonNull View itemView) {
        super(itemView);
        batsManName=itemView.findViewById(R.id.batsman_name);
        batsManScore=itemView.findViewById(R.id.batsman_score);
    }
    public void setName(String name){
        batsManName.setText(name);
    }
    public void setScore(String score){
        batsManScore.setText(score);
    }
}
