package com.proxsoftware.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource("classpath:application.properties")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*@Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        System.out.println("from costomize");
        container.setPort(8083);
    }*/

    /*@Configuration
    public static class BeanPostProcessorConfiguration {
        @Autowired
        ApplicationContext context;
        int count = 0;

        @Bean
        public BeanPostProcessor beanPostProcessor() {
            Environment environment = context.getEnvironment();
            environment.acceptsProfiles("dev");

            return new BeanPostProcessor() {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//                    System.out.println("Active Profile is: " + Arrays.toString(environment.getActiveProfiles()));
                    return bean;
                }

                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    return bean;
                }

            };

        }
    }*/
}
