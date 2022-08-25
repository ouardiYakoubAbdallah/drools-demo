package com.ouardi.DroolsDemo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.core.definitions.InternalKnowledgePackage;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.runtime.KieSession;


public class App {

	public static void main(String[] args) throws DroolsParserException, IOException {
	
		App app = new App();
		app.executeBusinessRules();
		
	}
	
	public void executeBusinessRules() throws DroolsParserException, IOException {
		KnowledgeBuilderImpl builder = new KnowledgeBuilderImpl();

		String rulesFilePath = "/resources/offers.drl";
		URL f = getClass().getResource(rulesFilePath);
		
		InputStream resourceAsStream = getClass().getResourceAsStream(rulesFilePath);

		Reader rulesReader = new InputStreamReader(resourceAsStream);
		
		builder.addPackageFromDrl(rulesReader);
		
		InternalKnowledgePackage[] rulePackage = builder.getPackages();
		
		InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		
		knowledgeBase.addPackage(rulePackage[0]);
		
		PaymentOffer offer = new PaymentOffer();
		offer.setChannel("big_offer");
		
		KieSession session = knowledgeBase.newKieSession();
		session.insert(offer);
		session.fireAllRules();
		
		System.out.println("The discount for the channel " + offer.getChannel() +" is: " + offer.getDiscount());
		
	}

}
