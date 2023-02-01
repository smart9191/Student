//package yt.uz.students.Config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import uz.sicnt.mysoliq.security.config.AccountFilter;
//import uz.sicnt.mysoliq.security.config.AuthBasicAuthenticationEntryPoint;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//
//    private final AccountFilter accountFilter;
//    private final AuthBasicAuthenticationEntryPoint authBasicAuthenticationEntryPoint;
//
//
//    public SecurityConfiguration(AccountFilter accountFilter, AuthBasicAuthenticationEntryPoint authBasicAuthenticationEntryPoint) {
//        this.accountFilter = accountFilter;
//        this.authBasicAuthenticationEntryPoint = authBasicAuthenticationEntryPoint;
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers(HttpMethod.OPTIONS, "/**")
//                .antMatchers("/app/**/*.{js,html}")
//                .antMatchers("/i18n/**")
//                .antMatchers("/content/**")
//                .antMatchers("/assets/**")
//                .antMatchers("/swagger-ui/index.html")
//                .antMatchers("/playground")
//                .antMatchers("/test/**");
//
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/assets/**").permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/api/company/get-tax-gap",
//                        "/api/company/get-tax-gap/by-date",
//                        "/api/statement/send/edo",
//                        "/v2/api-docs",           // swagger
//                        "/**/v3/api-docs/**",           // swagger
//                        "/webjars/**",            // swagger-ui webjars
//                        "/**/swagger-resources/**",  // swagger-ui resources
//                        "/**/configuration/**",      // swagger configuration
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
//                .anyRequest().authenticated()
//                .and().exceptionHandling().authenticationEntryPoint(authBasicAuthenticationEntryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(accountFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout()
//                .logoutUrl("/login/logout");
//
//
//        http.csrf().ignoringAntMatchers("/**", "/webjars/**", "/swagger-ui.html");
//        http.cors().configurationSource(corsConfigurationSource());
//
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig().applyPermitDefaultValues());
//
//        return source;
//    }
//
//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedHeaders(Arrays.asList("Accept", "Accept-Language",
//                "Origin", "Content-Language", "Content-Type", "X-Requested-With", "Authorization"));
//        corsConfiguration.setExposedHeaders(Arrays.asList("X-Total-Count", "Content-Disposition"));
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setMaxAge(48000L);
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedMethod("*");
//        return corsConfiguration;
//    }
//
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 4
//        return new CorsFilter(source);
//    }
//}
//
//
//
