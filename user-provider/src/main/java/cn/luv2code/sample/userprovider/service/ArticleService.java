package cn.luv2code.sample.userprovider.service;

import cn.luv2code.sample.userprovider.core.BaseService;
import cn.luv2code.sample.userprovider.dto.ArticleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService extends BaseService<ArticleDto,Long> {
    List<ArticleDto> findAll();
    List<ArticleDto> findAll(Pageable pageable);
}
