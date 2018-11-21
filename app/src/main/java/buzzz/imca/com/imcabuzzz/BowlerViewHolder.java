package buzzz.imca.com.imcabuzzz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class BowlerViewHolder extends RecyclerView.ViewHolder {
    private TextView nameText,runText,extraText,numberText,wicketText;
    public BowlerViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText=itemView.findViewById(R.id.bowler_name);
        runText=itemView.findViewById(R.id.bowler_run);
        extraText=itemView.findViewById(R.id.bowler_extra_run);
        numberText=itemView.findViewById(R.id.bowler_over_no);
        wicketText=itemView.findViewById(R.id.bowler_wickets);
    }
    public void setName(String name){
        nameText.setText(name);
    }
    public void setRun(String run){
        runText.setText(run);
    }
    public void setExtra(String extra){
        extraText.setText(extra);
    }
    public void setNumber(String number){
        numberText.setText(number);
    }
    public void setWicket(String wicket){
        wicketText.setText(wicket);
    }
}
