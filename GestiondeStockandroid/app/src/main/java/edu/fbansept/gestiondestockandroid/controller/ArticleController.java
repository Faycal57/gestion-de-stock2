package edu.fbansept.gestiondestockandroid.controller;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.fbansept.gestiondestockandroid.R;
import edu.fbansept.gestiondestockandroid.model.Article;
import edu.fbansept.gestiondestockandroid.utils.requestmanager.JsonArrayRequestWithToken;
import edu.fbansept.gestiondestockandroid.utils.requestmanager.RequestManager;

public class ArticleController {

    private static ArticleController instance = null;

    public ArticleController (){ }

    public static ArticleController getInstance(){
        if(instance == null);
        instance = new ArticleController();

        return instance;
    }

        public interface ArticleListener{
        void onArticleListener(List<Article> articles);
    }

    public  void  getArticle(Context context,ArticleListener listener){

        JsonArrayRequestWithToken request = new JsonArrayRequestWithToken(
                context,
                Request.Method.GET,  context.getResources().getString(R.string.spring_url)+ "article",
                null,
                jsonArticles -> {
                    try {
                        List<Article> listeArticle = new ArrayList<>();

                        for (int i = 0; i < jsonArticles.length(); i++) {
                            JSONObject jsonArticle = jsonArticles.getJSONObject(i);

                            listeArticle.add(new Article(jsonArticle));
                        }

                        listener.onArticleListener(listeArticle);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },

                erreur -> {
                    erreur.printStackTrace();
                    System.out.println("erreur");
                });

    RequestManager.getInstance(context).addToRequestQueue(request);



    }
}
