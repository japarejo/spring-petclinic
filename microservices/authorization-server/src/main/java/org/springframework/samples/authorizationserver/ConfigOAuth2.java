
package org.springframework.samples.authorizationserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration 
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "japarejo";
	   private String clientSecret = "japarejo-secret-key";
	   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
	   		"MIIEowIBAAKCAQEAug62xkqk3ocw2amx3rzHVskwsDZ+UTlWU8oOMvZh54wvYZii\r\n" + 
	   		"iyjb/2QMAMrxEHJfYINfmXVvTYsw2O57IFu7ZgVxtCnE/ow5uyhwDTpE9M7Ot2gW\r\n" + 
	   		"/LT99LdOPGneRrsP5px1qMWBq17aTzonAFbmFcmexJ4pgkEZJAWO/wC3q4bp2ArT\r\n" + 
	   		"MYSVyEW8EwYg6lkemHjmj+xK7yX3ZfXvK7abTNc6fYWdxYneyLGCapHtGiWUtARV\r\n" + 
	   		"narbHAn4WDKKOr2Xd29tHJkLp2f9hSdt6g0f97hTcO80+k3bTrduDHp6Ob2rvBXU\r\n" + 
	   		"SEKu0xxL8EeWHnvLZf/8p7jzeDd9UPuCEwXgqwIDAQABAoIBACEICbTsfZawI6EQ\r\n" + 
	   		"+m4Tz8P9lfJCymw7JLNddOXhTgJgpezKv1i7WUtfBK6DWQbVd09YI3nIGomGw5ZP\r\n" + 
	   		"yHY3Mu7q2oPkwn96f6lVhYcjqChaKuLuzN3yd/+hJ3ecSAUAotMLRew/nETDYzib\r\n" + 
	   		"l179Dn4hvFklxzLDweXeQsPM6c6+p9vUQzbrfLzhFPdlgz9aNXRyHsL+XxRUzpIZ\r\n" + 
	   		"yAPWjipDw4rgc1UiXOn9porENz54Lk5QZzptsDgSWIsj38aEXFpCRKWAlM7nvOv6\r\n" + 
	   		"NeaWMBHeqNBL5MMDIERL6lSCYW0Iey0j1ORnndqm3GwPaNK3qFTqo1tMu7XUeoXP\r\n" + 
	   		"cor1cOECgYEA94b8iyZMaUQ8OwsDwIEAOMaZuV2v9OtHm7/96Frxq6UhbK9Tg2Xx\r\n" + 
	   		"AyMKlYaYx8m5tpu8YRGtVYcg95qPZOA+4Q4jb6GxdXubILeG9awuLY3kstzCttSI\r\n" + 
	   		"GPoeu3yYW9jGMZPao+B3T8a/Fb+tsju7j9tLDYYVLiwlVdjuAOGfmhECgYEAwG0V\r\n" + 
	   		"m3k9J2zUECtmoNDnQjdbZgbBczJzVvEZk/htaOpQRCpMdR8Gtz89WGLNKZFMMFRg\r\n" + 
	   		"Y2ClprkMSA/71xTreWJwvI43x3fZ5E/3mkMbBYKmywAw3rgMULMo2Dia/FqLQ44a\r\n" + 
	   		"XEues0pOwR2pHlgDmnhmux5hoXbtgxp0VkHKsvsCgYBy9TEy2u2f8f3bGgl7DS5y\r\n" + 
	   		"L+JTEazXnbHbJvagRCR7KCz20hKCMwUVBOA0g5dPKmPi6D88Ab6zuxdXh2PTYMYz\r\n" + 
	   		"NaLFhJ3rRyXLsxCt5nJn2798RkT0Lo6qsvc08e+8sFeTzOw9MdGXrmEuk5/y6AT7\r\n" + 
	   		"EciwljYeBIiubxpSNHcwUQKBgQCTK9s2ygdnu+iFZWxc2unEzynsYoZaYN+uQ0BY\r\n" + 
	   		"18x2TZ+OQvuvKEeup4N3sjlP7XzkCeSenTCUzzsBcpge7DUc5Zlr3Sn0OUpBQJgT\r\n" + 
	   		"N/fWwxJmeILoFMP2hNBcbw66q1WQGYM3y+5ZGiAZu2G5f39iCWwo+Reasqb9eqI3\r\n" + 
	   		"xqINOQKBgCfI+1K/mlQkgsziOgzp5ZL/11Gioklsv7ecjpT+Eds95FffPPe3+C6c\r\n" + 
	   		"fFSe7S+GO1ZBlYKcJbpzTcumKy4Nc4kLeHn41+OnauFCHNwyfXTkpMaPVoY/6sT3\r\n" + 
	   		"e+CCoxhPzkltpowFHpeFKN/DmVAd40DeNm7wiAOal5OBMG7IoEUC\r\n" + 
	   		"-----END RSA PRIVATE KEY-----";
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" + 
	   		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAug62xkqk3ocw2amx3rzH\r\n" + 
	   		"VskwsDZ+UTlWU8oOMvZh54wvYZiiiyjb/2QMAMrxEHJfYINfmXVvTYsw2O57IFu7\r\n" + 
	   		"ZgVxtCnE/ow5uyhwDTpE9M7Ot2gW/LT99LdOPGneRrsP5px1qMWBq17aTzonAFbm\r\n" + 
	   		"FcmexJ4pgkEZJAWO/wC3q4bp2ArTMYSVyEW8EwYg6lkemHjmj+xK7yX3ZfXvK7ab\r\n" + 
	   		"TNc6fYWdxYneyLGCapHtGiWUtARVnarbHAn4WDKKOr2Xd29tHJkLp2f9hSdt6g0f\r\n" + 
	   		"97hTcO80+k3bTrduDHp6Ob2rvBXUSEKu0xxL8EeWHnvLZf/8p7jzeDd9UPuCEwXg\r\n" + 
	   		"qwIDAQAB\r\n" + 
	   		"-----END PUBLIC KEY-----";
	   
	   @Value("classpath:privkey.txt")
	   private Resource privkeyresource;
	   
	   @Value("classpath:pubkey.txt")
	   private Resource pubkeyresource;
	   
	   
	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   /*@PostConstruct
	   private void configure() {
		   privateKey=asString(privkeyresource);
		   publicKey=asString(pubkeyresource);
	   }*/
	   
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }
	   
	   public static String asString(Resource resource) {
	        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
	            return FileCopyUtils.copyToString(reader);
	        } catch (IOException e) {
	            throw new UncheckedIOException(e);
	        }
	    }

}
