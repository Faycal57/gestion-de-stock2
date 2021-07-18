package com.stock.mvc.controller;

import com.stock.mvc.dao.ArticleDao;
import com.stock.mvc.model.Article;
import com.stock.mvc.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
public class ArticleController {

    ArticleDao articleDao;
    JwtUtil jwtUtil;

    @Autowired
    ArticleController(ArticleDao articleDao,JwtUtil jwtUtil){
        this.articleDao = articleDao;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/article")
    public ResponseEntity<List<Article>> getArticle(){
        List<Article> articleListe = articleDao.findAll();
        return ResponseEntity.ok(articleListe);
    }

    @PostMapping("/utilisateur/article")

    public ResponseEntity<String> addArticle (@RequestBody Article article){
        article = articleDao.saveAndFlush(article);
        return ResponseEntity.created(
                URI.create("/utilisateur/article" + article.getIdArticle())
        ).build();
    }

    @DeleteMapping("/utilisateur/article/{id}")
    public ResponseEntity<Integer> deleteArticle (@PathVariable int id) {

        if(articleDao.existsById(id)) {
            articleDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
