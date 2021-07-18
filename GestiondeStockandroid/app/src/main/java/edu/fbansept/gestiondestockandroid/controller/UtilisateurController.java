package edu.fbansept.gestiondestockandroid.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;

import edu.fbansept.gestiondestockandroid.R;
import edu.fbansept.gestiondestockandroid.model.Utilisateur;
import edu.fbansept.gestiondestockandroid.utils.requestmanager.JsonObjectRequestWithToken;
import edu.fbansept.gestiondestockandroid.utils.requestmanager.RequestManager;

public class UtilisateurController {

    Utilisateur utilisateurConnecte = null;

    private static UtilisateurController instance = null;

    private UtilisateurController() { }

    public static UtilisateurController getInstance() {

        if(instance == null) {
            instance = new UtilisateurController();
        }

        return instance;
    }

    public interface TelechargementUtilisateurListener {
        void onUtilisateurEstTelecharge(Utilisateur utilisateur);
    }

    public void getUtilisateurConnecte(Context context, TelechargementUtilisateurListener evenement) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequestWithToken(
                context,
                Request.Method.GET,
                context.getResources().getString(R.string.spring_url) + "user/utilisateur-connecte",
                null,
                jsonUtilisateur -> {

                    try {
                        Utilisateur utilisateur = Utilisateur.fromJson(jsonUtilisateur);
                        evenement.onUtilisateurEstTelecharge(utilisateur);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.d("Erreur", error.toString())){
        };

        RequestManager.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public interface TelechargementImageProfilUtilisateurListener {
        void onImageProfilUtilisateurEstTelecharge(Bitmap bitmap);
    }

   /* public void getImageProfilUtilisateurConnecte(Context context, TelechargementImageProfilUtilisateurListener evenement) {

        ImageRequest request = new ImageRequest(Request.Method.GET,
                context.getResources().getString(R.string.server_ip) + "test/image-resource",
                response -> {

                    try {
                        if (response!=null) {

                            FileOutputStream outputStream;
                            //String name=<FILE_NAME_WITH_EXTENSION e.g reference.txt>;
                            //outputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
                            //outputStream.write(response);
                            //outputStream.close();
                            Bitmap bitmap = BitmapFactory.decodeByteArray(response, 0, response.length);

                            evenement.onImageProfilUtilisateurEstTelecharge(bitmap);
                        }
                    } catch (Exception e) {
                        Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
                        e.printStackTrace();
                    }
                },

                error -> {
                    error.printStackTrace();
                });

        RequestManager.getInstance(context).addToRequestQueue(request);
    } */
}