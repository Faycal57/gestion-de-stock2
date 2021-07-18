package com.stock.mvc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.stock.mvc.dao.UtilisateurDao;
import com.stock.mvc.model.Role;
import com.stock.mvc.model.Utilisateur;
import com.stock.mvc.security.JwtUtil;
import com.stock.mvc.security.UserDetailsCustom;
import com.stock.mvc.security.UserDetailsServiceCustom;
import com.stock.mvc.view.VueUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UtilisateurController {

    private UtilisateurDao utilisateurDao;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceCustom userDetailsServiceCustom;
    private PasswordEncoder passwordEncoder;

    @Autowired
    UtilisateurController(
            UtilisateurDao utilisateurDao,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            UserDetailsServiceCustom userDetailsServiceCustom,
            PasswordEncoder passwordEncoder
    ) {
        this.utilisateurDao = utilisateurDao;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceCustom = userDetailsServiceCustom;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authentification")
    public ResponseEntity<String> authentification(@RequestBody Utilisateur utilisateur) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getMail(), utilisateur.getMotDePasse()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Mauvais mail / mot de passe");
        }

        UserDetailsCustom userDetails = this.userDetailsServiceCustom.loadUserByUsername(utilisateur.getMail());

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }

    @PostMapping("/inscription")
    public ResponseEntity<String> inscription(@RequestBody Utilisateur utilisateur) {

        Optional<Utilisateur> utilisateurDoublon = utilisateurDao.trouverParPseudo(utilisateur.getMail());

        if (utilisateurDoublon.isPresent()) {
            return ResponseEntity.badRequest().body("Ce mail est déja utilisé");
        } else {

            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

            Role roleUtilisateur = new Role();
            roleUtilisateur.setId(1);

            utilisateur.getListeRole().add(roleUtilisateur);

            utilisateurDao.saveAndFlush(utilisateur);

            return ResponseEntity.ok(Long.toString(utilisateur.getId()));
        }
    }

    @PostMapping("/admin/utilisateur")
    public ResponseEntity<String> updateUser(@RequestBody Utilisateur utilisateur) {

        Optional<Utilisateur> utilisateurBddOptional = utilisateurDao.trouverParPseudo(utilisateur.getMail());

        if (utilisateurBddOptional.isPresent()) {
            Utilisateur utilisateurBdd = utilisateurBddOptional.get();
            utilisateur.setMotDePasse(utilisateurBdd.getMotDePasse());
            utilisateurDao.save(utilisateur);
            return ResponseEntity.ok().body("Utilisateur mis à jour");
        }

        return ResponseEntity.notFound().build();
    }

    @JsonView(VueUtilisateur.Standard.class)
    @GetMapping("/user/utilisateur-connecte")
    public ResponseEntity<Utilisateur> getInformationUtilisateurConnecte(
            @RequestHeader(value = "Authorization") String authorization) {
        //la valeur du champs authorization est extrait de l'entête de la requête

        //On supprime la partie "Bearer " de la valeur de l'authorization
        String token = authorization.substring(7);

        //on extrait l'information souhaitée du token
        String username = jwtUtil.getTokenBody(token).getSubject();

        Optional<Utilisateur> utilisateur = utilisateurDao.trouverParPseudo(username);

        if (utilisateur.isPresent()) {
            return ResponseEntity.ok().body(utilisateur.get());
        }

        return ResponseEntity.notFound().build();
    }

    @JsonView(VueUtilisateur.Standard.class)
    @GetMapping("/admin/utilisateur/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long idUtilisateur) {

        Optional<Utilisateur> utilisateur = utilisateurDao.findById(idUtilisateur);

        if (utilisateur.isPresent()) {
            return ResponseEntity.ok(utilisateur.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @JsonView(VueUtilisateur.Standard.class)
    @GetMapping("/admin/utilisateurs")
    public ResponseEntity<List<Utilisateur>> getUtilisateurs() {

        return ResponseEntity.ok(utilisateurDao.findAll());
    }

    @DeleteMapping("/admin/utilisateur/{id}")
    public ResponseEntity<Long> deleteUtilisateur(@PathVariable Long idUtilisateur) {

        if (utilisateurDao.existsById(idUtilisateur)) {
            utilisateurDao.deleteById(idUtilisateur);
            return ResponseEntity.ok(idUtilisateur);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
