package com.task.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.task.spring.model.Order;

/**
 * Handles requests for the Order service.
 */
@Controller
public class OrderController {
	
	@RequestMapping(value = "/get-order", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF() throws IOException {
          
      byte[] contents = Files.readAllBytes(new File("/var/usr/order_123.pdf").toPath());
      HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData("order_file", "order_123.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }

	
	
	
	@RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
	public @ResponseBody Order getOrder(@PathVariable("id") int orderId) {
		Order order = new Order();
		order.setId(orderId);
		order.setName("New Order");
		return order;
	}
	
}
