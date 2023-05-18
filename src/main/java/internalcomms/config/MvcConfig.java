package internalcomms.config;

/* MVC конфигурация для дальнейшего создания аунтефикации**/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("register");
        registry.addViewController("/user/*").setViewName("profile");
        registry.addViewController("/group/*").setViewName("groups");
        registry.addViewController("/projects").setViewName("index");
        registry.addViewController("/projects/*").setViewName("forum");
        registry.addViewController("/new-project").setViewName("new-project");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/");
    }
}