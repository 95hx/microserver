package cn.luv2code.sample.userprovider.service.impl;

import cn.luv2code.sample.userprovider.config.UseMyProperties;
import cn.luv2code.sample.userprovider.dto.ArticleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;


/**
 * @author hx
 * @Title: ArticleServiceImplTest
 * @ProjectName micro-server-demo
 * @Description: TODO
 * @date 2018/8/2211:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@WebAppConfiguration
public class ArticleServiceImplTest {
    @Resource
    UseMyProperties useMyProperties;
    @Resource
    ArticleServiceImpl articleService;
    @Test
    public void findAllByUserId() {
        List<ArticleDto> allByUserId = articleService.findAllByUserId(new PageRequest(0, 10), 1L);
        String properties = useMyProperties.getProperties();
        System.out.println(properties);
    }

}