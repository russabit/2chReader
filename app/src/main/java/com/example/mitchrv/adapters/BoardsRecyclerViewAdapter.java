package com.example.mitchrv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mitchrv.R;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardsRecyclerViewAdapter extends
        RecyclerView.Adapter<BoardsRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> boardChars;
    private ArrayList<String> boardNames;
    private OnViewListener onViewListener;

    public BoardsRecyclerViewAdapter(Context context,
                                     ArrayList<String> boardChars,
                                     ArrayList<String> boardNames,
                                     OnViewListener onViewListener) {
        this.context = context;
        this.boardChars = boardChars;
        this.boardNames = boardNames;
        this.onViewListener = onViewListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listitem_board, parent, false);
        return new ViewHolder(view, onViewListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.boardName.setText(boardChars.get(position));
        holder.boardChar.setText(boardNames.get(position));
    }


    @Override
    public int getItemCount() {
        return boardChars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView boardName, boardChar;
        RelativeLayout parentLayout;

        BoardsRecyclerViewAdapter.OnViewListener onViewListener;

        public ViewHolder(@NonNull View itemView,
                          BoardsRecyclerViewAdapter.OnViewListener onViewListener) {
            super(itemView);
            boardName = itemView.findViewById(R.id.board_char);
            boardChar = itemView.findViewById(R.id.board_name);
            parentLayout = itemView.findViewById(R.id.board_layout);
            this.onViewListener = onViewListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewListener.onViewClick(getAdapterPosition());
        }
    }

    public interface OnViewListener {
        void onViewClick(int position);
    }
}
