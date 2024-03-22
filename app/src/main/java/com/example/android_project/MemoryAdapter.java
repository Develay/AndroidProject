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
        cardVisibility = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            cardVisibility.add(false);
        }
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
            holder.imageView.setImageResource(getImageResource(cards.get(position)));
        } else {
            holder.imageView.setImageResource(R.drawable.logo_carmatch); // Set default back image
        }

        return view;
    }

    static class ViewHolder {
        ImageView imageView;
    }

    // Get the appropriate image resource based on the card value
    private int getImageResource(int cardValue) {
        switch (cardValue) {
            case 0:
                return R.drawable.nothing;
            case 1:
                return R.drawable.ok;
            case 2:
                return R.drawable.nothing;
            case 3:
                return R.drawable.ok;
            case 4:
                return R.drawable.nothing;
            case 5:
                return R.drawable.ok;
            case 6:
                return R.drawable.nothing;
            case 7:
                return R.drawable.ok;
            case 8:
                return R.drawable.nothing;
            case 9:
                return R.drawable.ok;
            case 10:
                return R.drawable.nothing;
            case 11:
                return R.drawable.nothing;
            case 12:
                return R.drawable.ok;
            case 13:
                return R.drawable.nothing;
            case 14:
                return R.drawable.ok;
            case 15:
                return R.drawable.nothing;
            case 16:
                return R.drawable.ok;
            case 17:
                return R.drawable.nothing;
            case 18:
                return R.drawable.ok;
            case 19:
                return R.drawable.nothing;
            case 20:
                return R.drawable.ok;
            case 21:
                return R.drawable.nothing;
            case 22:
                return R.drawable.ok;
            case 23:
                return R.drawable.nothing;
            case 24:
                return R.drawable.ok;
            case 25:
                return R.drawable.nothing;
            case 26:
                return R.drawable.ok;
            case 27:
                return R.drawable.nothing;
            case 28:
                return R.drawable.ok;
            case 29:
                return R.drawable.nothing;

            default:
                return R.drawable.logo_carmatch;
        }
    }

    // Method to set a card as matched
    public void showCard(int position) {
        cardVisibility.set(position, true);
        notifyDataSetChanged();
    }

    // Method to hide a card
    public void hideCard(int position) {
        cardVisibility.set(position, false);
        notifyDataSetChanged();
    }
}