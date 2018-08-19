package cn.luv2code.sample.userprovider.dao;

import cn.luv2code.sample.userprovider.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article,Long> {
}
