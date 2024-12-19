package com.example.chozabredtttttttto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private final List<Language> languages;
    private int selectedPosition = -1; // Позиция выбранного языка

    private final OnLanguageSelectedListener listener;

    public interface OnLanguageSelectedListener {
        void onLanguageSelected(Language language);
    }

    public LanguageAdapter(List<Language> languages, OnLanguageSelectedListener listener) {
        this.languages = languages;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_language_tile, parent, false);
        return new LanguageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        Language language = languages.get(position);

        // Устанавливаем название языка
        holder.languageName.setText(language.getName());
        holder.languageFlag.setImageResource(language.getFlagResId());

        // Меняем фон в зависимости от того, выбран элемент или нет
        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.tile_selected_background); // Выбранный фон
        } else {
            holder.itemView.setBackgroundResource(R.drawable.tile_background); // Обычный фон
        }

        // Обработка нажатия на элемент
        holder.itemView.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition(); // Запоминаем выбранную позицию
            notifyDataSetChanged(); // Обновляем все элементы
            listener.onLanguageSelected(language); // Вызываем коллбек
        });
    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    static class LanguageViewHolder extends RecyclerView.ViewHolder {
        TextView languageName;
        ImageView languageFlag;

        LanguageViewHolder(View itemView) {
            super(itemView);
            languageName = itemView.findViewById(R.id.languageName);
            languageFlag = itemView.findViewById(R.id.languageFlag);
        }
    }
}