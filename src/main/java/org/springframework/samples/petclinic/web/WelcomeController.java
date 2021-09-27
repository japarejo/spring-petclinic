package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class WelcomeController {
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model,Authentication generalAuth) {
		  if(generalAuth !=null && generalAuth instanceof OAuth2AuthenticationToken) {
			  OAuth2AuthenticationToken authentication=(OAuth2AuthenticationToken)generalAuth;		  
		  OAuth2AuthorizedClient client=authorizedClientService.loadAuthorizedClient( 
                  authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		  String userInfoEndpointUri = client.getClientRegistration()
				  .getProviderDetails().getUserInfoEndpoint().getUri();

				if (!StringUtils.isEmpty(userInfoEndpointUri)) {
				    RestTemplate restTemplate = new RestTemplate();
				    HttpHeaders headers = new HttpHeaders();
				    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
				      .getTokenValue());
				    HttpEntity entity = new HttpEntity("", headers);
				    ResponseEntity <Map>response = restTemplate
				      .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
				    Map userAttributes = response.getBody();
				    model.put("name", userAttributes.get("name"));
				    System.out.println(userAttributes.get("name"));
				}
		  }
	    return "welcome";
	  }
}
