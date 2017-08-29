package com.andrew;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.GregorianCalendar;

import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.TimeZone;

public class WatchFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		System.out.println(String.format("³Q©I¥s¤F %s", new GregorianCalendar().toZonedDateTime()));
		filterChain.doFilter(request, response);
	}

}
