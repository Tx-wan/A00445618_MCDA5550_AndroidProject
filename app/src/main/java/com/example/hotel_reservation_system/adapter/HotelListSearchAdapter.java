package com.example.hotel_reservation_system.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_reservation_system.response.HotelListResponse;
import com.example.hotel_reservation_system.view.ItemClickListener;
import com.example.hotel_reservation_system.R;
import com.example.hotel_reservation_system.model.HotelListModel;

import java.util.List;

public class HotelListSearchAdapter extends RecyclerView.Adapter<HotelListSearchAdapter.ViewHolder> {

    private List<HotelListModel> hotelListData;
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    @NonNull
    @Override
    public HotelListSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).
                inflate(R.layout.hotel_list_layout, parent, false);
        return new HotelListSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListSearchAdapter.ViewHolder holder, int position) {
        String hotelName = hotelListData.get(position).getHotelName();
        String hotelPrice = hotelListData.get(position).getPrice();
        String hotelAvailability = hotelListData.get(position).getAvailability();

        // set up the text
        holder.hotelName.setText(hotelName);
        holder.hotelAvailability.setText(hotelAvailability);
        holder.hotelPrice.setText(hotelPrice);
    }


    @Override
    public int getItemCount() {
        if (hotelListData != null) {
            return hotelListData.size();
        } else {
            return 0;
        }
    }

    public void setHotelListData(List<HotelListModel> hotelLists) {
        this.hotelListData = hotelLists;
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView hotelName, hotelPrice, hotelAvailability;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotel_name_text_view);
            hotelPrice = itemView.findViewById(R.id.price_text_view);
            hotelAvailability = itemView.findViewById(R.id.availability_text_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null) {
                clickListener.onClick(v, getAbsoluteAdapterPosition());
            }
        }
    }
}
