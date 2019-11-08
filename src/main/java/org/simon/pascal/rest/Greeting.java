/**
 * 
 */
package org.simon.pascal.rest;

import org.ff4j.FF4j;
import org.ff4j.core.FeatureStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author spngos
 *
 */
@RestController
@RequestMapping("/")
@Api(value = "greeting", tags = "operations pertaining to greeting")
public class Greeting {
	
	@Autowired
	private IHello hello;
	@Autowired
	private FF4j fF4j;
	
	@GetMapping()
    @ApiOperation(value = "Get a greeting")
    @ApiResponses({@ApiResponse(code = 200, message = "got a greeting message")})
    public String sayHello() { 
        return hello.sayHello()+" world";
    }
}
