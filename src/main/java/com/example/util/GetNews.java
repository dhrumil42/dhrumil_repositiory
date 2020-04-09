package com.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class GetNews {
	
		public BufferedReader getNewsFromKeyword(String keyword) throws IOException {
			URL url;
			url = new URL("http://newsapi.org/v2/everything?q="+keyword+"&apiKey=5c446bdf16c64b69835ea834edb07322");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			try {
				
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
			}
			catch (ProtocolException e) {
				e.printStackTrace();
			}
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			
			return br;
	}

}
