package com.smilegate.serverdevcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smilegate.serverdevcamp.model.URL;
import com.smilegate.serverdevcamp.service.URLService;

import javafx.util.Pair;

@Controller
public class URLController {

	@Autowired
	private URLService urlService;

	private final String domain = "http://localhost/";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showMainPage() {
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveURL(Model model, @RequestParam String originalURL) {
		// Save user entered URL in database
		Pair<URL, String> URLWithErrorMsg = urlService.saveURL(originalURL);
		
		URL url = URLWithErrorMsg.getKey();
		String errorMsg = URLWithErrorMsg.getValue();

		// If it is not saved in the database
		if (url == null) {
			// Adding an error message to model
			model.addAttribute("errorMsg", errorMsg);
		} 
		// If it is saved in the database
		else {
			// Adding original URL and shortened URL to model
			model.addAttribute("originalURL", url.getOriginalURL());
			model.addAttribute("shortenedURL", domain + url.getShortenedURL());
		}

		return "home";
	}

	@RequestMapping("/{shortenedURL}")
	public String redirectOriginalURL(Model model, @PathVariable String shortenedURL) {
		// Get URL in the database from shortened URL
		URL url = urlService.getURL(shortenedURL);

		// If it is not found in the database
		if (url == null) {
			// Adding an error message to model
			model.addAttribute("errorMsg", "This URL does not exist.");
			return "home";
		} 
		// If it is found in the database
		else {
			// Redirect to original URL
			String originalURL = url.getOriginalURL();
			return "redirect:http://" + originalURL;
		}
	}
}
