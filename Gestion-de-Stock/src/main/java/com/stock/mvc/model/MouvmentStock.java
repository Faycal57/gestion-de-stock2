package com.stock.mvc.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class MouvmentStock {

    public static final int ENTREE = 1;
    public static final int SORTIE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMvtStk;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMvt;
    private BigDecimal quantite;
    private  int typeMvt;

    @ManyToOne
    private Article article;

    public MouvmentStock() { }

    public static int getENTREE() {
        return ENTREE;
    }

    public static int getSORTIE() {
        return SORTIE;
    }

    public Long getIdMvtStk() {
        return idMvtStk;
    }

    public void setIdMvtStk(Long idMvtStk) {
        this.idMvtStk = idMvtStk;
    }

    public Date getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(Date dateMvt) {
        this.dateMvt = dateMvt;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public int getTypeMvt() {
        return typeMvt;
    }

    public void setTypeMvt(int typeMvt) {
        this.typeMvt = typeMvt;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}

