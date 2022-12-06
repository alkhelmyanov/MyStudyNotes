package com.gb.alkhelm.mystudynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.MyViewHolder> {

    private String[] data;
    OnItemClickListener onItemClickListener;

    @NonNull
    @Override // Создает макет ViewHolder (9 штук)
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // parent - контейнер в который будем инфлейтить наши макеты, viewType - в зависимости от него будут разные контейнеры (ViewHolder)
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_list_note_item, parent, false));
    }

    @Override // Связывает макет с View (переиспользует ViewHolder созданный выше)
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(data[position]); // holder свяжи свой макет с контентом data на позиции position
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public void setData(String[] data) {
        this.data = data;
        notifyDataSetChanged(); // перерисовать все полученные данные (очень ресурсоемкий)
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //Прописываем свой ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        //Макет нашего fragment_list_note_item
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition()); //
                }
            });
        }

        //Метод связывающий контент с макетом  fragment_list_note_item
        public void bindContentWithLayout(String content) {
            textView.setText(content);
        }
    }
}
