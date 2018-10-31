package com.lib_common.commonadapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib_common.R;
import com.lib_common.entity.CardBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/31  12:11
 * @description
 */
public class SelectActionAdapter extends RecyclerView.Adapter<SelectActionAdapter.ViewHolder>{
    List<CardBean> list = new ArrayList<>();
    private Context mContext;
    private int mPosition = 0;
    private OnSelectClickListener listener;
    public SelectActionAdapter(Context context, List<CardBean> list,OnSelectClickListener listener) {
        mContext = context;
        this.list = list;
        this.listener = listener;
    }
    public CardBean getSelected(){
        return list.get(mPosition);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_actino, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardBean bean = list.get(position);
        if(null!=bean){
            holder.tvName.setText(bean.getCardName());
            if(position==0){
                holder.itemView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.shape_corner_up));
            }else{
                holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
            }
            if(position==getItemCount()-1){
                holder.line.setVisibility(View.GONE);
            }else{
                holder.line.setVisibility(View.GONE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null!=listener){
                        listener.onSelectClick(bean);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private View line;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvName = view.findViewById(R.id.tv_name);
            line = view.findViewById(R.id.line);
        }
    }
    public interface OnSelectClickListener{
        void onSelectClick(CardBean bean);
    }
}