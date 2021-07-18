package edu.fbansept.gestiondestockandroid.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.fbansept.gestiondestockandroid.R;
import edu.fbansept.gestiondestockandroid.model.Article;

public class ListeArticleAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>  {

    private List<Article> listeArticle;
    private ClickArticleListener listener;



    public interface ClickArticleListener {
        void onClickArticleListener(Article article);
    }

    public ListeArticleAdapter(List<Article> listeArticle, ClickArticleListener listener) {
        this.listeArticle = listeArticle;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView_idArticle;
        private TextView textView_codeArticle;
        private TextView textView_designation;

        public MyViewHolder(@NonNull View view) {
            super(view);
            textView_idArticle = view.findViewById(R.id.textView_idArticle);
            textView_codeArticle = view.findViewById(R.id.textView_codeArticle);
            textView_designation = view.findViewById(R.id.textView_designation);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder view = (MyViewHolder) holder;
        //view.textView_idArticle.setText(listeArticle.get(position).getIdArticle());
        view.textView_codeArticle.setText(listeArticle.get(position).getCodeArticle());
        view.textView_designation.setText(listeArticle.get(position).getDesignation());
    }



    @Override
    public int getItemCount() {
        return listeArticle.size();
    }
}
