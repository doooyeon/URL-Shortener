package com.smilegate.serverdevcamp.model;

public class URLShortener {
	private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BASE62 = ALPHABET.length();
	
	private static final int MAX_SHORTEN_URL_LENGTH = 8;
	public static final long MAX_ID_VALUE = (long) Math.pow(BASE62, MAX_SHORTEN_URL_LENGTH);
	
	/**
	 * Convert id of base 10 to a shortened URL of base 62.
	 */
	public static String idToShortenedURL(long id) {
		StringBuilder sb = new StringBuilder();
		
		if (id == 0) {
			return Character.toString(ALPHABET.charAt((int) id));
		}
		while (id > 0) {
			int remainder = (int) (id % BASE62);
			sb.insert(0, ALPHABET.charAt(remainder));
			
			id = id / BASE62;
		}
		
		String shortenedURL = sb.toString();
		
		return shortenedURL;
	}

	/**
	 * Convert a shortened URL of base 62 to id of base 10.
	 */
	public static long shortenedURLToId(String shortenedURL) {
		long id = 0;
		char[] chars = new StringBuilder(shortenedURL).reverse().toString().toCharArray();
		
        for (int i=0; i <chars.length; i++) {
        	char c = chars[i];
        	id += (long) Math.pow(BASE62, i) * ALPHABET.indexOf(c);
        }

		return id;
	}
}
