package com.example.pedri.elavamonos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ReceitaListAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Receita> receitasList;


    public ReceitaListAdapter(Context context, int layout, ArrayList<Receita> receitasList) {
        this.context = context;
        this.layout = layout;
        this.receitasList = receitasList;
    }

    @Override
    public int getCount() {
        return receitasList.size();
    }

    @Override
    public Object getItem(int position) {
        return receitasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView2;
        TextView txt_nome, txt_tempo,txt_rendimento;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txt_nome = (TextView) row.findViewById(R.id.txt_nome);
            holder.txt_tempo = (TextView) row.findViewById(R.id.txt_tempo);
            holder.txt_rendimento = (TextView) row.findViewById(R.id.txt_rendimento);
            holder.imageView2 = (ImageView) row.findViewById(R.id.imageView2);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Receita receita = receitasList.get(position);

        holder.txt_nome.setText(receita.getNome());
        holder.txt_tempo.setText(receita.getTempo());
        holder.txt_rendimento.setText(receita.getRendimento());


        byte[] foodImage = receita.getFoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView2.setImageBitmap(bitmap);

        return row;
    }

}
