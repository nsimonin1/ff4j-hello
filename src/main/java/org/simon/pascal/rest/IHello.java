/**
 * 
 */
package org.simon.pascal.rest;

import org.ff4j.aop.Flip;

/**
 * @author spngos
 *
 */

public interface IHello {
	@Flip(name="language-french", alterBean="greeting.french")
	String sayHello();
}
