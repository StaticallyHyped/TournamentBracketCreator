package com.example.tournamentbracketcreator.viewholder;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.animation.SlideAnimation;
import com.google.android.material.textfield.TextInputEditText;

public class BracketsCellViewHolder extends RecyclerView.ViewHolder {
    private TextView teamOneName, teamTwoName;
    private TextInputEditText teamOneScore, teamTwoScore;
    private Animation animation;
    private RelativeLayout root;

    public BracketsCellViewHolder(@NonNull View itemView) {
        super(itemView);
        teamOneName = itemView.findViewById(R.id.teamone_name);
        teamTwoName = itemView.findViewById(R.id.teamtwo_name);
        teamOneScore = itemView.findViewById(R.id.teamone_score);
        teamTwoScore = itemView.findViewById(R.id.teamtwo_score);
        root = itemView.findViewById(R.id.layout_cellbracket_RL);

    }

    public void setAnimation(int height){
        animation = new SlideAnimation(root, root.getHeight(), height);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(200);
        root.setAnimation(animation);
        root.startAnimation(animation);
    }

    //Getters
    public TextView getTeamOneName() {
        return teamOneName;
    }

    public TextView getTeamTwoName() {
        return teamTwoName;
    }

    public TextInputEditText getTeamOneScore() {
        return teamOneScore;
    }

    public TextInputEditText getTeamTwoScore() {
        return teamTwoScore;
    }

    //Setters
   /* public void setTeamOneName(TextView teamOneName) {
        this.teamOneName = teamOneName;
    }

    public void setTeamTwoName(TextView teamTwoName) {
        this.teamTwoName = teamTwoName;
    }*/

    /*public void setTeamOneScore(TextInputEditText teamOneScore) {
        this.teamOneScore = teamOneScore;
    }*/

    /*public void setTeamTwoScore(TextInputEditText teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }*/
}
