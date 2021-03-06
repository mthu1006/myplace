package com.tu.place.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tu.place.R;
import com.tu.place.model.Place;
import com.tu.place.utils.AppContants;
import com.tu.place.utils.AppUtils;

import java.util.List;

/**
 * Created by SEV_USER on 4/25/2017.
 */

public class AdapterPlace extends RecyclerView.Adapter<AdapterPlace.MyViewHolder> {

    private Context context;
    private List<Place> arrPlace;
    private ItemListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvContent, tvAdress, tvDistance;
        public ImageView imPlace;

        public MyViewHolder(View view) {
            super(view);

            imPlace = (ImageView) view.findViewById(R.id.imPlace);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvContent = (TextView) view.findViewById(R.id.tvContent);
            tvAdress = (TextView) view.findViewById(R.id.tvAddress);
            tvDistance = (TextView) view.findViewById(R.id.tvDistance);
        }
    }


    public AdapterPlace(Context context, List<Place> arrPlace, ItemListener listener) {
        this.arrPlace = arrPlace;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_place, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Place place = arrPlace.get(position);
        holder.tvTitle.setText(place.getTitle());
        holder.tvContent.setText(place.getContent());
        holder.tvAdress.setText(place.getAddress());
        holder.tvDistance.setText(String.valueOf(place.getDistance()/1000f)+" km");
        if(!AppUtils.isEmptyString(place.getImg())) {
            Picasso.with(context).load(AppContants.getURLImg(place.getImg())).placeholder(R.drawable.ic_map).into(holder.imPlace);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onItemLongClick(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrPlace.size();
    }

    public interface ItemListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

}
