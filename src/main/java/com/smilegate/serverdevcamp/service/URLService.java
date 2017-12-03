package com.smilegate.serverdevcamp.service;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilegate.serverdevcamp.dao.URLRepository;
import com.smilegate.serverdevcamp.model.URL;
import com.smilegate.serverdevcamp.model.URLShortener;

import javafx.util.Pair;

@Service
public class URLService {

	@Autowired
	private URLRepository urlRepository;

	/**
	 * Save an original URL to database and then generate a shortened URL.
	 * Returns error message if it can not be stored in the database and is not a valid URL.
	 */
	public Pair<URL, String> saveURL(String originalURL) {

		Pair<URL, String> URLWithErrorMsg;

		// If the URL is valid
		if (validateURL(originalURL)) {
			
			// Identify the same URL for user input
			originalURL = sanitizeURL(originalURL);

			// Search database with Original URL
			URL url = urlRepository.findByOriginalURL(originalURL);

			// If it does not exist in the database
			if (url == null) {
				// Get the id value to be saved next.
				long nextId = urlRepository.getNextId();

				// If the id value to be saved next is greater than or equal to the maximum value
				if (nextId >= URLShortener.MAX_ID_VALUE) {
					// Return an error message
					URLWithErrorMsg = new Pair<URL, String>(null, "Can not generate shortened URL.");
					return URLWithErrorMsg;
				}
				
				// Set the original URL value and save it in the database.
				url = new URL();
				url.setOriginalURL(originalURL);
				url = urlRepository.save(url);
			}
			
			// Return URL after generating shortened URL
			url = generateShortenedURL(url);
			URLWithErrorMsg = new Pair<URL, String>(url, null);
			
		} 
		// If the URL is not valid
		else {
			// Return an error message
			URLWithErrorMsg = new Pair<URL, String>(null, "It is not a valid URL.");
		}

		return URLWithErrorMsg;
	}

	/**
	 * Verify that the URL entered is in the correct URL format
	 */
	private boolean validateURL(String url) {
		// If the user does not enter the protocol, attach the protocol.
		if (!url.substring(0, 4).equals("http"))
			url = "http://" + url;

		UrlValidator urlValidator = new UrlValidator();

		return urlValidator.isValid(url);
	}

	/**
	 * www.smilegate.com, www.smilegate.com/, https://www.smilegate.com
	 * All of the above URLs must have the same shortened URL.
	 */
	private String sanitizeURL(String url) {
		if (url.substring(0, 7).equals("http://"))
			url = url.substring(7);

		if (url.substring(0, 8).equals("https://"))
			url = url.substring(8);

		if (url.charAt(url.length() - 1) == '/')
			url = url.substring(0, url.length() - 1);

		return url;
	}

	/**
	 * Generate a shortened URL from the ID of the database.
	 */
	private URL generateShortenedURL(URL url) {
		// Generate shortened URL via base62.
		String shortenedURL = URLShortener.idToShortenedURL(url.getId());

		// setting Shortened URL.
		url.setShortenedURL(shortenedURL);

		return url;
	}

	/**
	 * Get the original URL from the shortened URL
	 */
	public URL getURL(String shortenedURL) {
		// Convert a shortened URL to the initial ID.
		long id = URLShortener.shortenedURLToId(shortenedURL);

		// Search database with Id
		URL url = urlRepository.findById(id);

		return url;
	}

}
