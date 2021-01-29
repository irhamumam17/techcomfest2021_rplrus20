package com.example.donacare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donacare.Model.HomeModel;
import com.example.donacare.Model.KelasModel;
import com.example.donacare.R;
import com.example.donacare.UI.DetailKelasActivity;

import java.util.ArrayList;
import java.util.List;

//import com.example.donacare.PostInfoActivity;
//import com.example.donacare.UI.Home;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> implements Filterable {

    private List<HomeModel> dataList;
    private List<HomeModel> dataListFull;
    Context mContext;
    View viewku;
    OnItemClickListener mListener;

    public HomeAdapter(DetailKelasActivity mContext, ArrayList<KelasModel> kelasModels) {
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public HomeAdapter( Context mContext, List<HomeModel> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
        dataListFull = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.list_item_home, parent, false);
        return new HomeViewHolder(viewku, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.tv_title.setText(dataList.get(position).getTitle());
        holder.tv_subtitle.setText(dataList.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title, tv_subtitle;
        private ImageView img_list_home;
        CardView cv_home;
        Button btn_lihat_selengkapnya_home;

        HomeViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            img_list_home = itemView.findViewById(R.id.image_list_home);
            btn_lihat_selengkapnya_home = itemView.findViewById(R.id.btn_lihat_selengkapnya_home);
            cv_home = itemView.findViewById(R.id.cv_home);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_subtitle = itemView.findViewById(R.id.tv_subtitle);

            btn_lihat_selengkapnya_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return dataListFilter;
    }


    private Filter dataListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<HomeModel> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(dataListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (HomeModel item : dataListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults  filterResults) {
            dataList.clear();
            dataList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };


}
