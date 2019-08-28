package com.example.tournamentbracketcreator.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tournamentbracketcreator.R;
import com.example.tournamentbracketcreator.databinding.TournpoolPlayerRowBinding;

public class PoolAViewHolder extends RecyclerView.ViewHolder {
    public final TournpoolPlayerRowBinding binding;
    /*private Button nameButton;
    private TextView txt_id;
    LinearLayout playerContainer;*/

    public PoolAViewHolder(TournpoolPlayerRowBinding binding) {
        super(binding.getRoot());
        /*this.nameButton = itemView.findViewById(R.id.txt_name);
        this.txt_id = itemView.findViewById(R.id.text_id);
        this.playerContainer = itemView.findViewById(R.id.layout_playeritem);*/
        this.binding = binding;
    }

   /* public Button getTxt_name() {
        return nameButton;
    }

    public void setTxt_name(Button txt_name) {
        this.nameButton = txt_name;
    }

    public TextView getTxt_id() {
        return txt_id;
    }

    public void setTxt_id(TextView txt_id) {
        this.txt_id = txt_id;
    }*/
}
