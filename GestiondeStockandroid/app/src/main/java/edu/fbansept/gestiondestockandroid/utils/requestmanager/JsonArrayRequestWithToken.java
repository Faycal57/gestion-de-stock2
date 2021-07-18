package edu.fbansept.gestiondestockandroid.utils.requestmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import edu.fbansept.gestiondestockandroid.R;

public class JsonArrayRequestWithToken extends JsonArrayRequest {

    Context context;

    public JsonArrayRequestWithToken(Context context, int method, String url, @Nullable JSONArray jsonRequest, Response.Listener<JSONArray> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        this.context = context;
    }

    @Override
    public Map<String, String> getHeaders() {
        SharedPreferences preference = context.getSharedPreferences(
                context.getResources().getString(R.string.fichier_preference), 0); // 0 - for private mode
        Map<String, String> params = new HashMap<>();
        params.put("Content-Type", "application/json; charset=UTF-8");
        params.put("Authorization", "Bearer " + preference.getString("token",""));
        return params;
    }
}
