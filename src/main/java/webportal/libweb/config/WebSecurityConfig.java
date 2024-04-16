package webportal.libweb.config;

/* import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; */


/* @Configuration
@EnableWebSecurity */
public class WebSecurityConfig{

    /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/main", "/reg").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll()
                )
                .logout((logout) -> logout.permitAll()
                )
                .build();
    }


    @SuppressWarnings("deprecation")
    @Bean 
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                            .username("user")
                            .password("password")
                            .roles("USER")
                            .build();
        return new InMemoryUserDetailsManager(user);
    } */
}
