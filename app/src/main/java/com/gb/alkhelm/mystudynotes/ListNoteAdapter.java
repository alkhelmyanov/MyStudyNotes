package com.gb.alkhelm.mystudynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.MyViewHolder> {

    private CardsSource cardSource;

    OnItemClickListener onItemClickListener;

    Fragment fragment;

    private int menuPosition;

    public int getMenuPosition() {
        return menuPosition;
    }

    @NonNull
    @Override // Создает макет ViewHolder (9 штук)
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // parent - контейнер в который будем инфлейтить наши макеты, viewType - в зависимости от него будут разные контейнеры (ViewHolder)
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_list_note_item, parent, false));
    }

    @Override // Связывает макет с View (переиспользует ViewHolder созданный выше)
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(cardSource.getCardData(position)); // holder свяжи свой макет с контентом data на позиции position
    }

    @Override
    public int getItemCount() {
        return cardSource.size();
    }

    public void setData(CardsSource cardSource) {
        this.cardSource = cardSource;
        notifyDataSetChanged(); // перерисовать все полученные данные (очень ресурсоемкий)
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    ListNoteAdapter (Fragment fragment){
        this.fragment = fragment;
    }

    //Прописываем свой ViewHolder (обрабатывает каждую конкретную менюшку)
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewListItem;
        private TextView textViewNoteType;
        private CheckBox checkBox;

        //Макет нашего fragment_list_note_item
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewListItem = (TextView) itemView.findViewById(R.id.listItem);
            textViewNoteType = (TextView) itemView.findViewById(R.id.noteType);
            checkBox = (CheckBox) itemView.findViewById(R.id.favorite);



            // Устновлен дублирующий LongClickListener на ItemView чтобы можно было получить позицию клика
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition(); // записываем в menuPosition полученную позицию
                    return false;
                }
            });

            // Повесить контекстное меню на кликабельный объект
            textViewListItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition(); // записываем в menuPosition полученную позицию
                    // view.showContextMenu(); // выводит на экран контекстное меню
                    return false; // после клика выполнение кода продолжается
                }
            });
            fragment.registerForContextMenu(itemView); // зарегистрировать контекстное меню на itemView (на всю карточку). После чего клики на itemView буду приводить к срабатыванию у фрагмента onContextItemSelected и будут вызываться меню прописанные в onCreateContextMenu

            textViewListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition()); //
                }
            });
        }

        //Метод связывающий контент с макетом  fragment_list_note_item
        public void bindContentWithLayout(CardData content) {
            textViewListItem.setText(content.getList_item());
            textViewNoteType.setText(content.getNote_type());
            checkBox.setChecked(content.isFavorite());
        }
    }
    /*private String[] data;
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
    }*/
}
