package com.minsait.emprestimo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes/health-check")
public class HealthController {
	
	@GetMapping
	public long health() {
		return System.currentTimeMillis();
		}
	
	@GetMapping("\bruno")
	public long health2() {
		return System.currentTimeMillis();
	}
	

}