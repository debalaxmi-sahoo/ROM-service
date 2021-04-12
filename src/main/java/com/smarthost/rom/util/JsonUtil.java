package com.smarthost.rom.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public static List<Double> getCustomerPaymentDetails() {
		final Logger log = LoggerFactory.getLogger(JsonUtil.class);
		List<Double> payments = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
             
        //Read the customer payment list from json hotel_guests.json    
      
        try (InputStream inputStream = (new ClassPathResource("/json/hotel_guests.json").getInputStream())) {
            payments = objectMapper.readValue(inputStream, new TypeReference<List<Double>>() {
          });
            inputStream.close();
        } catch (IOException io) {
            payments = new ArrayList<>();            
        }
        log.info("All payments details - "+payments.toString());

        return payments;
    }

}
