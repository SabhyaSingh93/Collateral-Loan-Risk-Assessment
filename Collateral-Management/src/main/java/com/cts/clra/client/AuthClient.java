package com.cts.clra.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.clra.model.AuthResponse;

/**
 * 
 * @author Shiyam v
 *
 */
@FeignClient(url="${auth.path}",name="AUTH")

public interface AuthClient {
	
	/**
	 *
	 *This function used to validate the token.
	 *The {@link Validation} is true is proceed the next step
	 *if the {@link Validation} is fails send a @return value bad credentials 
	 *
	 *@RequestHeader annotation binds request header values to method parameters.
	 */
	@GetMapping(path="/validate")
	public AuthResponse verifyToken(@RequestHeader(name="Authorization",required=true)String token);
	

}
