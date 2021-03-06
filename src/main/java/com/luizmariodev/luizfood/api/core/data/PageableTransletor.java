package com.luizmariodev.luizfood.api.core.data;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class PageableTransletor {

	public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {
		
		var orders = pageable.getSort().stream()
						.filter(order -> fieldsMapping.containsKey(order.getProperty()))
						.map(order -> new Order(order.getDirection(), fieldsMapping.get(order.getProperty())))
						.collect(Collectors.toList());
		
		return PageRequest.of(pageable.getPageSize(), pageable.getPageSize(), Sort.by(orders));
	}

}
