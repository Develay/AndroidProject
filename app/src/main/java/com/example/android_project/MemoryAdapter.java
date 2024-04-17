package com.example.android_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

// Classe qui gère l'adaptation des cartes
public class MemoryAdapter extends BaseAdapter {

    final private Context context;
    final private ArrayList<Integer> cards;
    final private ArrayList<Integer> cardVisibility;

    public MemoryAdapter(Context context, ArrayList<Integer> cards) {
        this.context = context;
        this.cards = cards;

        // Initialize the card visibility list
        cardVisibility = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            cardVisibility.add(0);
        }
    }

    // Méthode qui retourne la vue
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

        if (cardVisibility.get(position)!=0) {
            holder.imageView.setImageResource(getImageResource(position));
        } else {
            holder.imageView.setImageResource(R.drawable.logo_carmatch); // Set default back image
        }

        return view;
    }

    // Méthode qui retourne le ViewHolder
    static class ViewHolder {
        ImageView imageView;
    }

    // Méthode qui retourne l'image associée à la carte
    private int getImageResource(int cardValue) {
        if (cardVisibility.get(cardValue)!=0) {

            switch (cards.get(cardValue)) {
                case 0:
                    return R.drawable.citroen_2ch;
                case 1:
                    return R.drawable.alpine_a110;
                case 2:
                    return R.drawable.audi_quattro;
                case 3:
                    return R.drawable.bugatti_chiron;
                case 4:
                    return R.drawable.chevrolet_camaro;
                case 5:
                    return R.drawable.dodge_ram;
                case 6:
                    return R.drawable.f1_mclaren;
                case 7:
                    return R.drawable.ferrari_f40;
                case 8:
                    return R.drawable.ford_gt40;
                case 9:
                    return R.drawable.lamborghini_huracan_sto;
                case 10:
                    return R.drawable.lancer_evolution_5;
                case 11:
                    return R.drawable.land_rover_defender;
                case 12:
                    return R.drawable.mazda_rx7;
                case 13:
                    return R.drawable.mercedes_classe_g;
                case 14:
                    return R.drawable.nissan_gtr_r34;
                case 15:
                    return R.drawable.peugeot_e208;
                case 16:
                    return R.drawable.porsche_gt3rs;
                case 17:
                    return R.drawable.r5_turbo;
                case 18:
                    return R.drawable.toyota_ae86;
                case 19:
                    return R.drawable.vw_coccinelle;

                default:
                    return R.drawable.nothing;
            }
        } else {
            return R.drawable.logo_carmatch;
        }
    }

    // Méthode qui retourne la visibilité de la carte
    public void showCard(int position,int lock) {
        cardVisibility.set(position, lock);
        notifyDataSetChanged();
    }

    // Méthode qui cache la carte
    public void hideCard(int position) {
        cardVisibility.set(position, 0);
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

    // Méthode qui retourne l'état de visibilié des cartes
    public ArrayList<Integer> getCardVisibility() {
        return cardVisibility;
    }
}