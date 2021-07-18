package edu.fbansept.gestiondestockandroid.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Article implements Serializable {

    private int idArticle;
    private String codeArticle;
    private String designation;
    private double prixUnitaireHT;
    private double tauxTva;
    private double prixUnitaireTTC;
    private String photo;

    private List<CommandeClient> commandeClient = new ArrayList<>();
    private List<LigneCommandeClient> ligneCommandeClients = new ArrayList<>();
    private List<LigneVente> ligneVentes = new ArrayList<>();
    private List<MouvmentStock> mouvmentStocks = new ArrayList<>();

    public Article(JSONObject jsonArticle) throws JSONException {
        this.idArticle = jsonArticle.getInt("idArticle");
        this.codeArticle = jsonArticle.getString("codeArticle");
        this.designation = jsonArticle.getString("designation");
        //this.prixUnitaireHT = jsonArticle.getDouble("prixUnitaireHT");
        //this.tauxTva = jsonArticle.getDouble("tauxTva");
        //this.prixUnitaireTTC = jsonArticle.getDouble("prixUnitaireTTC");
        //this.photo =   jsonArticle.getString("photo");
    }

    public Article(int idArticle, String codeArticle, String designation, double prixUnitaireHT, double tauxTva, double prixUnitaireTTC, String photo) {
        this.idArticle = idArticle;
        this.codeArticle = codeArticle;
        this.designation = designation;
        this.prixUnitaireHT = prixUnitaireHT;
        this.tauxTva = tauxTva;
        this.prixUnitaireTTC = prixUnitaireTTC;
        this.photo = photo;
    }



    public static Article fromJson(JSONObject jsonArticle) throws JSONException {

        List<CommandeClient> commandeClients = new ArrayList<>();
        JSONArray jsonCommandeClients = jsonArticle.getJSONArray("commandeClients");

        for (int i = 0; i < jsonCommandeClients.length(); i++){
            JSONObject jsonCommandeClient = jsonCommandeClients.getJSONObject(i);
            commandeClients.add(CommandeClient.fromJson(jsonCommandeClient));
        }


        return new Article(
                jsonArticle.getInt("idArticle"),
                jsonArticle.getString("codeArticle"),
                jsonArticle.getString("designation"),
                jsonArticle.getDouble("prixUnitaireHT"),
                jsonArticle.getDouble("tauxTva"),
                jsonArticle.getDouble("prixUnitaireTTC"),
                jsonArticle.getString("photo")

        );
    }

    public static JSONObject toJson(Article article) throws JSONException {
        JSONObject jsonArticle = new JSONObject();
        jsonArticle.put("idArticle", article.getIdArticle());
        jsonArticle.put("codeArticle", article.getCodeArticle());
        jsonArticle.put("designation", article.getDesignation());
        jsonArticle.put("prixUnitaireHT", article.getPrixUnitaireHT());
        jsonArticle.put("prixUnitaireTTC", article.getPrixUnitaireTTC());
        jsonArticle.put("photo", article.getPhoto());

        return jsonArticle;
    }

    public int getIdArticle() { return idArticle; }

    public void setIdArticle(int idArticle) { this.idArticle = idArticle; }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrixUnitaireHT() {
        return prixUnitaireHT;
    }

    public void setPrixUnitaireHT(double prixUnitaireHT) {
        this.prixUnitaireHT = prixUnitaireHT;
    }

    public double getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(double tauxTva) {
        this.tauxTva = tauxTva;
    }

    public double getPrixUnitaireTTC() {
        return prixUnitaireTTC;
    }

    public void setPrixUnitaireTTC(double prixUnitaireTTC) {
        this.prixUnitaireTTC = prixUnitaireTTC;
    }

    public List<CommandeClient> getCommandeClient() {
        return commandeClient;
    }

    public void setCommandeClient(List<CommandeClient> commandeClient) {
        this.commandeClient = commandeClient;
    }

    public List<LigneCommandeClient> getLigneCommandeClients() {
        return ligneCommandeClients;
    }

    public void setLigneCommandeClients(List<LigneCommandeClient> ligneCommandeClients) {
        this.ligneCommandeClients = ligneCommandeClients;
    }

    public List<LigneVente> getLigneVentes() {
        return ligneVentes;
    }

    public void setLigneVentes(List<LigneVente> ligneVentes) {
        this.ligneVentes = ligneVentes;
    }

    public List<MouvmentStock> getMouvmentStocks() {
        return mouvmentStocks;
    }

    public void setMouvmentStocks(List<MouvmentStock> mouvmentStocks) {
        this.mouvmentStocks = mouvmentStocks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
