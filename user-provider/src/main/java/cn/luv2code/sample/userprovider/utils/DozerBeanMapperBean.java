package cn.luv2code.sample.userprovider.utils;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * instance of DozerBeanMapper
 */
@Component
public class DozerBeanMapperBean {
    @Bean
    public DozerBeanMapper getDozerBeanMapper() {
        return new DozerBeanMapper();
    }

}
