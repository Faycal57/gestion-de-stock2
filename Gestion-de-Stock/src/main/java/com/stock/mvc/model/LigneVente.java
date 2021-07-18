package com.stock.mvc.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class LigneVente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLigneVente;

    @ManyToOne
    private Article article;

    @ManyToOne
    @JoinColumn(name = "vente")
    private Vente vente;

    public LigneVente() { }

    public int getIdLigneVente() {
        return idLigneVente;
    }

    public void setIdLigneVente(int idLigneVente) {
        this.idLigneVente = idLigneVente;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

}
