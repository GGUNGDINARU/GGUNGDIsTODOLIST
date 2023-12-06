package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private final ArrayList<Todo> mData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        //item view를 저장하는 뷰홀더 클래스
        protected TextView textview_todo_item;
        protected ImageButton deleteBt;

        @SuppressLint("NotifyDataSetChanged")
        public ViewHolder(View itemView) {
            super(itemView);

            this.textview_todo_item = itemView.findViewById(R.id.textview_todo_item);
            this.deleteBt = itemView.findViewById(R.id.button_todo_item);
            //item view와 연결하였으므로 findViewById앞에 itemview 명시하기!

            //arraylist 삭제버튼
            deleteBt.setOnClickListener(v -> {
                int position = getAdapterPosition(); //현재 어댑터가 다루는 리스트의 포지션 가져오기

                if (position != RecyclerView.NO_POSITION) { //삭제된 포지션이 아닌 경우
                    mData.remove(position); //arraylisttodo 타입의 리스트에서 해당 포지션의 item 제거!
                    notifyDataSetChanged(); //어댑터에게 데이터 set이 변경되었음을 알리기!
                }
            });
        }
    }

    TodoAdapter(ArrayList<Todo> list) { //생성자에게서 데이터 리스트 객체를 전달받기
        mData = list;
    }

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoAdapter.ViewHolder holder, int position) {
        holder.textview_todo_item.setText(mData.get(position).getTodoName());
    }
    //포지션에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시함

    @Override
    public int getItemCount() { // 전체 데이터 갯수 리턴
        return mData.size();
    }
}
