package pl.coderslab.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import pl.coderslab.notice.service.SpringDataUserDetailsService;

import javax.sql.DataSource;
import javax.validation.Validator;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("admin/addAdmin", "user/addUser", "/home", "notice/**").permitAll()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/welcome").hasAnyRole("USER")
                .anyRequest().permitAll()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/checkrole")
                .and().logout().logoutSuccessUrl("/logout")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");
    }


    public void addViewControllers(ViewControllerRegistry registry) {
          registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/403").setViewName("403");
    }

    @Autowired
    DataSource dataSource;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }
}



