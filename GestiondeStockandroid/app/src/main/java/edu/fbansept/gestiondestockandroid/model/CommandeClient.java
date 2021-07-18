package edu.fbansept.gestiondestockandroid.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandeClient implements Serializable {

    private Long idCommandeClient;
    private String code;
    private Date dateCommande;
    private Client client;

    private  List<Article> listeArticles = new ArrayList<>();
    private List<LigneCommandeClient> ligneCommandeClients = new ArrayList<>();

    public CommandeClient() { }

    public CommandeClient(Long idCommandeClient, String code, Object dateCommande, Object client) {
    }


    /* public FluxExceptionnel(JSONObject jsonFlux) throws JSONException, ParseException {
        super(jsonFlux);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        dateFlux = (Date) format.parse((String) jsonFlux.get("dateFlux"));
    }*/


    public static CommandeClient fromJson(JSONObject jsonCommandeClient) throws JSONException {

        List<LigneCommandeClient> ligneCommandeClients = new ArrayList<>();

        JSONArray jsonLigneCommandeClient = jsonCommandeClient.getJSONArray("ligneCommandeClients");

        for (int i = 0;i < jsonLigneCommandeClient.length(); i++ ) {
            JSONObject json = jsonCommandeClient.getJSONObject(String.valueOf(i));
            ligneCommandeClients.add(LigneCommandeClient.fromJson(json));


        }

        return new CommandeClient(
                jsonCommandeClient.getLong("idCommandeClient"),
                jsonCommandeClient.getString("code"),
                jsonCommandeClient.get("dateCommande"),
                jsonCommandeClient.get("client")
        );
    }

    public static JSONObject toJson(CommandeClient commandeClient) throws JSONException{
        JSONObject jsonCommandeClient = new JSONObject();
        jsonCommandeClient.put("idCommandeClient",commandeClient.idCommandeClient);
        jsonCommandeClient.put("code",commandeClient.code);
        jsonCommandeClient.put("dateCommande",commandeClient.dateCommande);
        jsonCommandeClient.put("client",commandeClient.client);

        return jsonCommandeClient;
    }

}
