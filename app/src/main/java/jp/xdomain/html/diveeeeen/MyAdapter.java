package jp.xdomain.html.diveeeeen;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final String[] localDataSet;
    private int holderNum = 1;
    ViewHolder viewHolder;
    private Activity activity;
    MyColor myColor = new MyColor();

    //コンストラクタで配列を受け取ってセット
    public MyAdapter(Activity activity,String[] dataSet) {
        this.localDataSet = dataSet;
        this.activity = activity;
    }
    public MyAdapter(Activity activity,String[] dataSet,int num) {
        this.localDataSet = dataSet;
        this.holderNum = num;
        this.activity = activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if(this.holderNum == 1) {
            View view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.my_text_view1, viewGroup, false);
            viewHolder = new ViewHolder(view);
        }else if(this.holderNum == 2){
            View view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.my_text_view2, viewGroup, false);
            viewHolder = new ViewHolder(view);
        }
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(localDataSet[position]);
        String tmp = localDataSet[position];

        if(tmp.equals("不正解")){
            viewHolder.getTextView().setTextColor(myColor.getMyColor(this.activity,R.color.red));
        }else if(tmp.equals("正解")){
            viewHolder.getTextView().setTextColor(myColor.getMyColor(this.activity,R.color.green));
        }
    }
    //アイテムの数を決める
    @Override
    public int getItemCount() {
        return localDataSet.length;
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
    //下のgetItemIdとgetItemViewTypeを入れておかないとスクロールしたときに
    //挙動がおかしくなる（onBindViewHolderの中のsetTextColorがはちゃめちゃになる）
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
