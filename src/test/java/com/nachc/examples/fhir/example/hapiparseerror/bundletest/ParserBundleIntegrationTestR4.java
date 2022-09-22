package com.nachc.examples.fhir.example.hapiparseerror.bundletest;

import org.hl7.fhir.r4.model.Bundle;
import org.junit.Test;

import com.nachc.examples.fhir.example.hapiparseerror.util.file.FileUtil;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParserBundleIntegrationTestR4 {

	private static final FhirContext CTX = FhirContext.forR4();

	private static final String TEST_PATIENT_FILE = "/fhir/patient/Glenn0_Hermiston71_d7e5852a-974c-6d45-f81d-5a87bbd05d2a.json";
	
	@Test
	public void shouldParserBundle() {
		String jsonString = FileUtil.getAsString(TEST_PATIENT_FILE);
		IParser parser = CTX.newJsonParser();
		parser.setStripVersionsFromReferences(false);
		CTX.getParserOptions().setStripVersionsFromReferences(false);
		IParser jsonParser = CTX.newJsonParser();
		Bundle bundle = jsonParser.parseResource(Bundle.class, jsonString);
	}
	
}
