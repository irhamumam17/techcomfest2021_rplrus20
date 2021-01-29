package com.example.donacare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donacare.Model.KelasModel;
import com.example.donacare.R;

import java.util.ArrayList;
import java.util.List;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.HomeViewHolder> {

    private List<KelasModel> dataList_kelas;
    private List<KelasModel> dataListFull;
    Context mContext;
    View viewku;
    OnItemClickListener mListener;

    public interface OnItemClickListener extends HomeAdapter.OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public KelasAdapter( Context mContext, List<KelasModel> dataList) {
        this.dataList_kelas = dataList;
        this.mContext = mContext;
        dataListFull = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.list_item_kelas, parent, false);
        return new HomeViewHolder(viewku, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.tv_title_kelas.setText(dataList_kelas.get(position).getTitle_kelas());
        holder.tv_subtitle_kelas.setText(dataList_kelas.get(position).getSubtitle_kelas());
    }

    @Override
    public int getItemCount() {
        return dataList_kelas.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title_kelas, tv_subtitle_kelas;
        private ImageView img_kelas;
        CardView cv_list_kelas;
        Button btn_lihat_selengkapnya_kelas;

        HomeViewHolder(View itemView, final HomeAdapter.OnItemClickListener listener) {
            super(itemView);
            img_kelas = itemView.findViewById(R.id.image_kelas);
            btn_lihat_selengkapnya_kelas = itemView.findViewById(R.id.btn_lihat_selengkapnya_kelas);
            cv_list_kelas = itemView.findViewById(R.id.cv_list_kelas);
            tv_title_kelas = itemView.findViewById(R.id.tv_title_kelas);
            tv_subtitle_kelas = itemView.findViewById(R.id.tv_subtitle_kelas);

            btn_lihat_selengkapnya_kelas.setOnClickListener(new View.OnClickListener() {
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
}
