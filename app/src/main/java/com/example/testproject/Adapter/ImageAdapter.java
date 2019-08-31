package com.example.testproject.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject.Model.ImageDetail;
import com.example.testproject.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    String dataString;
    private Activity context;
    ArrayList<ImageDetail> messageItems;
    ArrayList<ImageDetail> messageItemsForSearch;

    public ImageAdapter( Activity context,ArrayList<ImageDetail> ImageDetail) {
        this.messageItemsForSearch = ImageDetail;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_item_list, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, final int position) {
        final ItemViewHolder hol;
        if (holder1 instanceof ItemViewHolder) {
            ItemViewHolder holder = (ItemViewHolder) holder1;

            holder.tv_name.setText(messageItemsForSearch.get(position).getImageName());
            holder.iv_image.setBackgroundResource(messageItemsForSearch.get(position).getImage());

        }
    }

    @Override
    public int getItemCount() {
        return messageItemsForSearch.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;ImageView iv_image;
        public ItemViewHolder(View view) {
            super(view);
            iv_image = (ImageView) view.findViewById(R.id.iv_pic);
            tv_name = (TextView) view.findViewById(R.id.tv_image_name);

        }
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                System.out.println(results.values);
                messageItemsForSearch = (ArrayList<ImageDetail>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
//                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<ImageDetail> FilteredArrList = new ArrayList<ImageDetail>();
                if (messageItems == null) {
                    messageItems = new ArrayList<ImageDetail>(messageItemsForSearch); // saves the original data in recordsList
                }
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = messageItems.size();
                    results.values = messageItems;
                } else {

                    constraint = constraint.toString().toLowerCase();
                    constraint = constraint.toString().replace("\n", "");
                    for (int i = 0; i < messageItems.size(); i++) {
                        if (messageItems.get(i).getImageName() != null) {
                              dataString = messageItems.get(i).getImageName();
                            if (dataString.toLowerCase().contains(constraint.toString().trim().toLowerCase())) {
                                FilteredArrList.add(new ImageDetail(
                                        messageItems.get(i).getImageName(),
                                        messageItems.get(i).getImage()));

                            }
                        } else {

                            dataString = messageItems.get(i).getImageName().trim() ;
                            if (dataString.toLowerCase().contains(constraint.toString().trim().toLowerCase())) {
                                FilteredArrList.add(new ImageDetail(
                                        messageItems.get(i).getImageName(),
                                        messageItems.get(i).getImage()));

                            }

                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;

    }


}
