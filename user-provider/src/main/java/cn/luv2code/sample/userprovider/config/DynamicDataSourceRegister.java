package cn.luv2code.sample.userprovider.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;

import cn.luv2code.sample.userprovider.config.DynamicDataSource;
import cn.luv2code.sample.userprovider.config.DynamicDataSourceContextHolder;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 动态数据源注册
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    //如配置文件中未指定数据源类型，使用该默认值
    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";
    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues dataSourcePropertyValues;

    // 默认数据源
    private DataSource defaultDataSource;

    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();

    /**
     * 加载多数据源配置
     */
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("DynamicDataSourceRegister.setEnvironment()");
        initDefaultDataSource(environment);
        initCustomDataSources(environment);
    }

    /**
     * 加载主数据源配置.
     */
    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
//        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        Map<String, Object> dsMap = (LinkedHashMap) Binder.get(env)
                .bind("spring.datasource", Bindable.mapOf(String.class, String.class))
                .get();
        //创建数据源;
        defaultDataSource = buildDataSource(dsMap);
        dataBinder(defaultDataSource, env);
    }

    /**
     * 初始化更多数据源
     */
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
//        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
        Map<String, Object> dsMap = (LinkedHashMap) Binder.get(env)
                .bind("custom.datasource", Bindable.mapOf(String.class, String.class))
                .get();
        String dsPrefixs = (String) dsMap.get("names");
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
            dataBinder(ds, env);
        }
    }

    /**
     * 创建datasource.
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {
        Object type = dsMap.get("type");
        if (type == null) {
            type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
        }
        Class<? extends DataSource> dataSourceType;

        try {
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dsMap.get("driverClassName").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 为DataSource绑定更多数据
     */
    private void dataBinder(DataSource dataSource, Environment env) {
//        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
//        dataBinder.setConversionService(conversionService);
//        dataBinder.setIgnoreNestedProperties(false);//false
//        dataBinder.setIgnoreInvalidFields(false);//false
//        dataBinder.setIgnoreUnknownFields(true);//true
        Map<String, Object> values = (LinkedHashMap) Binder.get(env)
                .bind("custom.datasource", Bindable.mapOf(String.class, String.class))
                .get();
        Binder binder = new Binder(new MapConfigurationPropertySource(values));

        binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(dataSource));
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("DynamicDataSourceRegister.registerBeanDefinitions()");
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        // 添加更多数据源
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);

        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        //添加属性：AbstractRoutingDataSource.defaultTargetDataSource
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);
    }

}