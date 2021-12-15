package com.example.winkel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{

    ArrayList<Item_class> list;
    private OnNoteListener mOnNoteListener;
    public AdapterClass(ArrayList<Item_class> list, OnNoteListener onNoteListener)
    {
        this.list = list;
        this.mOnNoteListener = onNoteListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder,viewGroup,false);
        return new MyViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(list.get(i).getItemName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,avb,root,disp;
        OnNoteListener onNoteListener;
        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            name = itemView.findViewById(R.id.itemname);
            avb = itemView.findViewById(R.id.itavb);
            root=itemView.findViewById(R.id.itimg);
            disp=itemView.findViewById(R.id.mpimg);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int postion);
    }
}
