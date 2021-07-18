package edu.fbansept.gestiondestockandroid.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;
    private String denomination;

    public Role(Integer id, String nom) {
        this.id = id;
        this.denomination = nom;
    }

    public static edu.fbansept.gestiondestockandroid.model.Role fromJson(JSONObject jsonRole) throws JSONException {
        return new edu.fbansept.gestiondestockandroid.model.Role(
                jsonRole.getInt("id"),
                jsonRole.getString("denomination")
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
}
