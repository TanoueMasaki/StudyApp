package com.example.myapplication;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<String> localDataSet;

    //コンストラクタで配列を受け取ってセット
    public MyAdapter(List<String> dataSet) {
        localDataSet = dataSet;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.my_text_view, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(localDataSet.get(position));
    }

    //アイテムの数を決める
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    //onBindViewHolderで使うViewHolder型を内部クラスで定義
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        //コンストラクタでtext_viewを取得
        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text_view);
        }
        //textViewを渡す
        public TextView getTextView(){
            return textView;
        }
    }
}
