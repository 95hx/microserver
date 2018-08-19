package cn.luv2code.sample.userprovider.service.impl;

import cn.luv2code.sample.userprovider.dao.ArticleDao;
import cn.luv2code.sample.userprovider.dto.ArticleDto;
import cn.luv2code.sample.userprovider.entity.Article;
import cn.luv2code.sample.userprovider.service.ArticleService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticleDao articleDao;
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    /**
     * 查
     *
     * @param id 主键
     * @return ArticleDto
     */
    @Override
    @Transactional(readOnly = true)
    public ArticleDto findById(Long id) {
        Optional<Article> optional = articleDao.findById(id);
        Article article = optional.orElseThrow(EntityNotFoundException::new);
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(article, articleDto);
        return articleDto;
    }

    /**
     * 查
     * @return List<ArticleDto>
     */
    @Override
    @Transactional(readOnly = true)
    public List<ArticleDto> findAll() {
        return articleDao.findAll().stream()
                .map(e -> dozerBeanMapper.map(e, ArticleDto.class))
                .collect(Collectors.toList());
    }

    /**
     * 查
     *
     * @param pageable
     * @return
     */
    @Override
    public List<ArticleDto> findAll(Pageable pageable) {
        return articleDao.findAll(pageable).stream()
                .map(e -> dozerBeanMapper.map(e, ArticleDto.class))
                .collect(Collectors.toList());
    }

    /**
     * 增
     * @param articleDto
     */
    @Override
    @Transactional(readOnly = false)
    public void save(ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        articleDao.save(article);
    }

    /**
     * 删
     * @param id 主键
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        articleDao.deleteById(id);
    }


}
