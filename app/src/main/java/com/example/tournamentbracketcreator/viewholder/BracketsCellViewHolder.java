package com.example.tournamentbracketcreator.viewholder;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.animation.SlideAnimation;

public class BracketsCellViewHolder extends RecyclerView.ViewHolder {
    private TextView teamOneName, teamTwoName, teamOneScore, teamTwoScore;
    private Animation animation;
    private RelativeLayout root;

    public BracketsCellViewHolder(@NonNull View itemView) {
        super(itemView);
        //teamOneName = itemView.findViewById(R.id.team_one_name);
        //teamTwoName = itemView.findViewById(R.id.team_two_name);
    }

    public void setAnimation(int height){
        animation = new SlideAnimation(root, root.getHeight(), height);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(200);
        root.setAnimation(animation);
        root.startAnimation(animation);
    }

    public TextView getTeamOneName() {
        return teamOneName;
    }

    public TextView getTeamTwoName() {
        return teamTwoName;
    }

    public void setTeamOneName(TextView teamOneName) {
        this.teamOneName = teamOneName;
    }

    public void setTeamTwoName(TextView teamTwoName) {
        this.teamTwoName = teamTwoName;
    }

    public TextView getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(TextView teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public TextView getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(TextView teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }
}
