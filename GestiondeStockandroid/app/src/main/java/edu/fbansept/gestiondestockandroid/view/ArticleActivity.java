package edu.fbansept.gestiondestockandroid.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.fbansept.gestiondestockandroid.R;
import edu.fbansept.gestiondestockandroid.controller.ArticleController;
import edu.fbansept.gestiondestockandroid.view.adapter.ListeArticleAdapter;

public class ArticleActivity extends AppCompatActivity {
    //private FloatingActionButton buttonSupprimerArticle;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       init();
    }

    private  void init(){

        setContentView(R.layout.activity_article);

        recyclerView = findViewById(R.id.recyclerView_article);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArticleController.getInstance().getArticle(this,
                listeArticle-> {

                    recyclerView.setAdapter(new ListeArticleAdapter(listeArticle, v -> {
                        Intent intent = new Intent(
                                this,
                                ArticleActivity.class

                        );


                        startActivity(intent);
                    }));
                }
        );

    }

}