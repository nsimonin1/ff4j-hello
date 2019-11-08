/**
 * 
 */
package org.simon.pascal.rest;

import org.springframework.stereotype.Component;

/**
 * @author spngos
 *
 */
@Component("greeting.french")
public class HelloEn implements IHello{

	@Override
	public String sayHello() {		 
		return "Hello";
	}

}
