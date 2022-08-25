package com.ouardi.DroolsDemo;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class App {

	public static void main(String[] args){
		KieServices kieService = KieServices.Factory.get();
		KieContainer kieContainer = kieService.getKieClasspathContainer();
		KieSession kSession = kieContainer.newKieSession("payment-rules-session");
		
		PaymentOffer offer = new PaymentOffer();
		offer.setChannel("med_offer");
		
		kSession.insert(offer);
		kSession.fireAllRules();
		
		System.out.println("The discount for the channel " + offer.getChannel() +" is: " + offer.getDiscount());

		
	}
}
