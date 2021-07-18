package edu.fbansept.gestiondestockandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.fbansept.gestiondestockandroid.R;
import edu.fbansept.gestiondestockandroid.controller.ConnexionController;
import edu.fbansept.gestiondestockandroid.utils.JWTUtils;


public class LoginActivity extends AppCompatActivity {
    TextView textViewMail;
    TextView textViewPassword;
    Button boutonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (JWTUtils.isTokenValide(this)) {
            startActivity(new Intent(this, ArticleActivity.class));
        } else {

            setContentView(R.layout.activity_login);

            textViewMail = findViewById(R.id.mail);
            textViewPassword = findViewById(R.id.password);
            boutonConnexion = findViewById(R.id.button_connexion);

            boutonConnexion.setOnClickListener((View v) -> {
                ConnexionController.getInstance().connexion(
                        this,
                        textViewMail.getText().toString(),
                        textViewPassword.getText().toString(),
                        () -> startActivity(new Intent(this, ArticleActivity.class)),
                        (String messageErreur) -> Toast.makeText(this, messageErreur, Toast.LENGTH_LONG).show()
                );
            });

        }
    }
}
