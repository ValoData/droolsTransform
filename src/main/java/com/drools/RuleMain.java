package com.drools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RuleMain {
	public static void main(String[] args) {
		try {
        List<Data> sourceData = InputReader.readSourceData(args[0]);


     // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-dtables");
    	
    	for(Data d:sourceData) {
        	kSession.insert(d);
        }
        kSession.fireAllRules();

        List<String> outputDataList = sourceData.stream()
                .map(Data::toCsv)
                .collect(Collectors.toList());
        outputDataList.add(0, "Ref,emetteur_contrepartie,note,position,duree_residuelle,Result");
        Files.write(Paths.get(args[1]), outputDataList);
		} catch(IOException e) {
			e.printStackTrace();
		}
    }
}
