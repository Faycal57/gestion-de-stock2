package edu.fbansept.gestiondestockandroid.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Utilisateur implements Serializable {

    private Long idUtilisateur;

    private String mail;

    private String motDePasse;

    public Utilisateur(Long id, String mail, String motDePasse) {
        this.idUtilisateur = idUtilisateur;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }

    public static Utilisateur fromJson(JSONObject jsonRole) throws JSONException {
        return new Utilisateur(
                jsonRole.getLong("idUtilisateur"),
                jsonRole.getString("mail"),
                jsonRole.getString("motDePasse")
        );
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
