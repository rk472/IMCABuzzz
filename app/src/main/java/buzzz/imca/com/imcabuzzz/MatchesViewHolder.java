package buzzz.imca.com.imcabuzzz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MatchesViewHolder extends RecyclerView.ViewHolder {
    private TextView titleText,resultText;
    public MatchesViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText=itemView.findViewById(R.id.match_title);
        resultText=itemView.findViewById(R.id.match_feedback);
    }
    public void setTitle(String title){
        titleText.setText(title);
    }
    public void setResult(String result){
        resultText.setText(result);
    }
}
