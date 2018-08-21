package cn.luv2code.sample.userprovider.dao.secondary;

import cn.luv2code.sample.userprovider.entity.secondary.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article,Long> {
}
