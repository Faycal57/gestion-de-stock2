package com.stock.mvc.dao;

import com.stock.mvc.model.Article;
import com.stock.mvc.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article, Integer> {
}
