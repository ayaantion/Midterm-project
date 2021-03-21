package com.example.mytodo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodo.R;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>  {

    private ArrayList<String> list = new ArrayList<>();
    private  onTodoClickListener onTodoClickListener;

    public  void  updateTodo(ArrayList<String> todoList){
        this.list = todoList;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewholder,parent,false);
        TodoViewHolder viewHolder = new TodoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
             holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public  void  onItemClick(onTodoClickListener listener){
        this.onTodoClickListener = listener;
   }

    public class TodoViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTittle;
        private TextView textViewDate;



        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTittle = itemView.findViewById(R.id.txt_tittle);
            textViewDate = itemView.findViewById(R.id.txt_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTodoClickListener.onClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onTodoClickListener.onLongClick(getAdapterPosition());
                    return true;
                }
            });

        }

        public void onBind(String s) {
            textViewTittle.setText(s);

        }
    }
}
