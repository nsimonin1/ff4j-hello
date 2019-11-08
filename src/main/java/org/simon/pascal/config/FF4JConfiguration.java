/**
 * 
 */
package org.simon.pascal.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.ff4j.FF4j;
import org.ff4j.audit.repository.JdbcEventRepository;
import org.ff4j.cache.InMemoryCacheManager;
import org.ff4j.core.Feature;
import org.ff4j.core.FeatureStore;
import org.ff4j.property.PropertyDouble;
import org.ff4j.property.PropertyInt;
import org.ff4j.property.PropertyString;
import org.ff4j.property.store.JdbcPropertyStore;
import org.ff4j.spring.boot.web.api.config.EnableFF4jSwagger;
import org.ff4j.store.InMemoryFeatureStore;
import org.ff4j.store.JdbcFeatureStore;
import org.ff4j.strategy.DarkLaunchStrategy;
import org.ff4j.strategy.WhiteListStrategy;
import org.ff4j.strategy.time.ReleaseDateFlipStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author spngos
 *
 */
@Configuration
@EnableFF4jSwagger
public class FF4JConfiguration {
	@Autowired
	private DataSource dataSource;
	@Bean
    public FF4j getFF4j() {
		
		FF4j ff4j= new FF4j("ff4j.xml");
		
		/*FF4j ff4j= new FF4j();
		ff4j.importFeatures(initFeatures());
		
		
		ff4j.getFeatureStore().importFeatures(initFeatures());
		//ff4j.getPropertiesStore().importProperties(properties);
		 */
		JdbcFeatureStore store=new JdbcFeatureStore(dataSource);
		store.createSchema();
		ff4j.setFeatureStore(store);
		JdbcPropertyStore jdbcPropertyStore=new JdbcPropertyStore(dataSource);
		jdbcPropertyStore.createSchema();
		ff4j.setPropertiesStore(jdbcPropertyStore); 
		JdbcEventRepository eventRepository=new JdbcEventRepository(dataSource);
		eventRepository.createSchema();
		ff4j.setEventRepository(eventRepository);
		// Enable Audit Proxy
		ff4j.audit();				        
		// Enable Cache Proxy
		ff4j.cache(new InMemoryCacheManager());
		// Explicite import
		/*XmlConfig xmlConfig = ff4j.parseXmlConfig("ff4j.xml");
		ff4j.getFeatureStore().importFeatures(xmlConfig.getFeatures().values());
		ff4j.getPropertiesStore().importProperties(xmlConfig.getProperties().values());*/
		return ff4j;
    }
	
	 
	
	private List<Feature> initFeatures() {
		List<Feature> features=new ArrayList<>();
		// Simplest declaration
		Feature f1 = new Feature("f1");
		features.add(f1);
		// Declare with description and initial state
		Feature f2 = new Feature("f2", false, "sample description");
		features.add(f2);    
		// Illustrate ACL & Group
		Set < String > permission = new HashSet<String>();
		permission.add("BETA-TESTER");
		permission.add("VIP");
		Feature f3 = new Feature("f3", false, "sample description", "GROUP_1", permission);
		features.add(f3);       
		// Custom properties
		Feature f4 = new Feature("f4");
		f4.addProperty(new PropertyString("p1", "v1"));
		f4.addProperty(new PropertyDouble("pie", Math.PI));
		f4.addProperty(new PropertyInt("myAge", 12));
		features.add(f4);
		// Flipping Strategy
		Feature f5 = new Feature("f5");
		Calendar nextReleaseDate = Calendar.getInstance();
		nextReleaseDate.set(Calendar.MONTH, Calendar.SEPTEMBER);
		nextReleaseDate.set(Calendar.DAY_OF_MONTH, 1);
		f5.setFlippingStrategy(new ReleaseDateFlipStrategy(nextReleaseDate.getTime()));
		features.add(f5);
		Feature f6 = new Feature("f6");
		f6.setFlippingStrategy(new DarkLaunchStrategy(0.2d));   
		features.add(f6);
		Feature f7 = new Feature("f7");
		f7.setFlippingStrategy(new WhiteListStrategy("localhost"));
		features.add(f7);
		return features;
	}
}
