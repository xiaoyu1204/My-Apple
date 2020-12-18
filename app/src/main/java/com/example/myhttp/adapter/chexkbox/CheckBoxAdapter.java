package com.example.myhttp.adapter.chexkbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhttp.R;
import com.example.myhttp.model.bean.checkbox.CheckBox_Bean;

import java.util.List;

public class CheckBoxAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CheckBox_Bean.DataBean> list;

    public CheckBoxAdapter(Context context, List<CheckBox_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_checkbox_rlv_item, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolde= (ViewHolder) holder;
        CheckBox_Bean.DataBean bean = list.get(position);
        viewHolde.checkbox_rlv_id.setText(bean.getName());
        viewHolde.checkbox_rlv_check.setChecked(bean.isSelect());
        viewHolde.checkbox_rlv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionclickitem.onitem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView checkbox_rlv_id;
        public CheckBox checkbox_rlv_check;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.checkbox_rlv_id = (TextView) rootView.findViewById(R.id.checkbox_rlv_id);
            this.checkbox_rlv_check = (CheckBox) rootView.findViewById(R.id.checkbox_rlv_check);
        }

    }
    public interface Ionclickitem{
        void onitem(int qq);
    }
    Ionclickitem ionclickitem;

    public void setIonclickitem(Ionclickitem ionclickitem) {
        this.ionclickitem = ionclickitem;
    }
}
