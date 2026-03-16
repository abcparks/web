package cn.alex.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件，进行线程池配置
 * Created by WCY on 2021/9/16
 */
@Component
@ConfigurationProperties(value = "primary.async")
public class PrimaryAsyncContent extends AsyncContent {
}
