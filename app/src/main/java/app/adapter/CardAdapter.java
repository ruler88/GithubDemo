package app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.model.Github;
import com.example.githubdemo.app.R;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    List<Github> mItems;  //todo: change move to GitHub

    public CardAdapter() {
        super();
        mItems = new ArrayList<Github>();
    }

    public void addSomething() {
        Github github = new Github();
        github.setLogin("The Amazing Spider-Man 2");
        mItems.add(github);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Github github = mItems.get(i);
        viewHolder.login.setText(github.getLogin());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView login;

        public ViewHolder(View itemView) {
            super(itemView);
            login = (TextView)itemView.findViewById(R.id.login);
        }
    }
}