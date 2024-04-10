package com.example.android_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class MemoryAdapter extends BaseAdapter {

    final private Context context;
    final private ArrayList<Integer> cards;
    final private ArrayList<Boolean> cardVisibility;

    public MemoryAdapter(Context context, ArrayList<Integer> cards) {
        this.context = context;
        this.cards = cards;

        // Initialize the card visibility list
        cardVisibility = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            cardVisibility.add(false);
        }
    }

    // Method to get the view of the grid item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.back_card);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (cardVisibility.get(position)) {
            holder.imageView.setImageResource(getImageResource(position));
        } else {
            holder.imageView.setImageResource(R.drawable.logo_carmatch); // Set default back image
        }

        return view;
    }

    // ViewHolder class to hold the ImageView
    static class ViewHolder {
        ImageView imageView;
    }

    // Get the appropriate image resource based on the card value
    private int getImageResource(int cardValue) {
        if (cardVisibility.get(cardValue)) {

            switch (cards.get(cardValue)) {
                case 0:
                    return R.drawable.c1;
                case 1:
                    return R.drawable.c2;
                case 2:
                    return R.drawable.c3;
                case 3:
                    return R.drawable.c4;
                case 4:
                    return R.drawable.c5;
                case 5:
                    return R.drawable.c6;
                case 6:
                    return R.drawable.c7;
                case 7:
                    return R.drawable.c8;
                case 8:
                    return R.drawable.c9;
                case 9:
                    return R.drawable.c10;
                case 10:
                    return R.drawable.c11;
                case 11:
                    return R.drawable.c12;
                case 12:
                    return R.drawable.c13;
                case 13:
                    return R.drawable.c14;
                case 14:
                    return R.drawable.c15;

                default:
                    return R.drawable.nothing;
            }
        } else {
            return R.drawable.logo_carmatch;
        }
    }

    // Method to show a card
    public void showCard(int position) {
        cardVisibility.set(position, true);
        notifyDataSetChanged();
    }

    // Method to hide a card
    public void hideCard(int position) {
        cardVisibility.set(position, false);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}