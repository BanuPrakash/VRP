package com.visa.springdemo;

import com.visa.springdemo.service.AppService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringdemoApplication {

    public static void main(String[] args) {
       ApplicationContext ctx =  SpringApplication.run(SpringdemoApplication.class, args);

        AppService service = ctx.getBean("appService", AppService.class);
        service.insert();

//       for(String bean: ctx.getBeanDefinitionNames()) {
//           System.out.println(bean);
//       }
    }

}
