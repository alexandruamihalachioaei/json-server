package com.alexandru.amihalachioaei.controller;

import com.alexandru.amihalachioaei.logic.PathLogic;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
public class JsonController {

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
	}

	@Autowired
	private PathLogic pathLogic;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getFile(@RequestParam String filename, @RequestParam int httpStatus) throws IOException {

		File file = pathLogic.getFile(filename);

		if(file == null){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

		JsonNode node = mapper.readTree(file);

		return new ResponseEntity<>(node, HttpStatus.valueOf(httpStatus));
	}
}
