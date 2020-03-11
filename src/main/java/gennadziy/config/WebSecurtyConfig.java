package gennadziy.config;

import gennadziy.dao.UserRepo;
import gennadziy.model.User;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;


@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurtyConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http
                .authorizeRequests ( )
                .mvcMatchers ( "/" ).permitAll ( )
                .anyRequest ( ).authenticated ( )
                .and ( )
                .csrf ( ).disable ( );
    }

    @Bean
    public PrincipalExtractor principalExtractor ( UserRepo userRepo ) {
        return map -> {
            String id = (String) map.get ( "sub" );

            User user = userRepo.findById ( id ).orElseGet ( () -> {
                User newUser = new User ( );

                newUser.setId ( id );
                newUser.setName ( (String) map.get ( "name" ) );
                newUser.setEmail ( (String) map.get ( "email" ) );
                newUser.setGender ( (String) map.get ( "gender" ) );
                newUser.setLocal ( (String) map.get ( "locale" ) );
                newUser.setUserpic ( (String) map.get ( "picture" ) );

                return newUser;
            } );

            user.setLastVisit ( LocalDateTime.now ( ) );

            return userRepo.save ( user );
        };
    }
}
