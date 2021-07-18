package com.stock.mvc.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class LigneCommandeFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneCdeFrs;

    @ManyToOne
    private Article article;

    @ManyToOne
    private CommandeFournisseur commandeFournisseur;


    public LigneCommandeFournisseur() { }

    public Long getIdLigneCdeFrs() {
        return idLigneCdeFrs;
    }

    public void setIdLigneCdeFrs(Long idLigneCdeFrs) {
        this.idLigneCdeFrs = idLigneCdeFrs;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public CommandeFournisseur getCommandeFournisseur() {
        return commandeFournisseur;
    }

    public void setCommandeFournisseur(CommandeFournisseur commandeFournisseur) {
        this.commandeFournisseur = commandeFournisseur;
    }


}
