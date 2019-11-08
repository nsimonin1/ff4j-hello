/**
 * 
 */
package org.simon.pascal.rest;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author spngos
 *
 */
@Component("language-french")
@Primary
public class HelloFr implements IHello{

	@Override
	public String sayHello() {		 
		return "Bonjour";
	}

}
