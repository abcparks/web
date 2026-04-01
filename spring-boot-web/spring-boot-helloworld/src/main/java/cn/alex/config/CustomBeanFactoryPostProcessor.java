package cn.alex.config;

import cn.alex.bean.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Created by WCY on 2026/3/31
 */
@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition book = beanFactory.getBeanDefinition("book");
        book.setAttribute("id", "$#$");

        Book bean = beanFactory.getBean(Book.class);
        System.out.println(bean);
    }

}
