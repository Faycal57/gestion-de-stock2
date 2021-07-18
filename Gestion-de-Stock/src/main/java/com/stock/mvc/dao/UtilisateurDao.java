package com.stock.mvc.dao;

import com.stock.mvc.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UtilisateurDao extends JpaRepository <Utilisateur, Long> {

    @Query("FROM Utilisateur u JOIN FETCH u.listeRole WHERE mail = :mail")
    Optional<Utilisateur> trouverParPseusoAvecRoles(@Param("mail") String mail);

    @Query( "FROM Utilisateur u " +
            "JOIN FETCH u.listeRole n " +
            "WHERE mail = :mail " +
            "ORDER BY n.id DESC")
    Optional<Utilisateur> trouverParPseudo(@Param("mail") String mail);
}
