package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.websocket.server.PathParam;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.hibernate.validator.internal.util.privilegedactions.NewSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import Model.Article;
import Model.NewsSearchResult;
import util.GetNews;
import util.GetTopCompaniesForNews;


@RestController
public class HomeController {
	

	
	@RequestMapping("home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home.jsp");
		return mv;
	}
	
	@RequestMapping("findUsingKeyWord")
	public ModelAndView findUsingKeyWord() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("findByKeyWord.jsp");
		return mv;
	}

	
	@GetMapping("getNewsCompany")
	public ArrayList<String> getNewsCompanies(@RequestParam("number") int a,@RequestParam("keyword") String keyword) throws IOException, ParseException {
		URL url;
		url = new URL("http://newsapi.org/v2/everything?q=corona&apiKey=5c446bdf16c64b69835ea834edb07322");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try {
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
		} 
		catch (ProtocolException e) {
			e.printStackTrace();
		}
		
		GetNews news = new GetNews();
		BufferedReader br = news.getNewsFromKeyword(keyword);
		ObjectMapper mapper = new ObjectMapper();
		NewsSearchResult articlearray = mapper.readerFor(NewsSearchResult.class).readValue(br);
		
		ArrayList<Article> articles = articlearray.getArticles();
		GetTopCompaniesForNews topCompanyNews = new GetTopCompaniesForNews();
		ArrayList<String> newsCompanies = topCompanyNews.getTopNewsComp(articles, a);
		
		return newsCompanies;
	}
	
	@GetMapping("getAllNewsCompany")
	public ArrayList<String> getAllNewsCompanies(@RequestParam("keyword") String keyword) throws IOException, ParseException {
		URL url;
		url = new URL("http://newsapi.org/v2/everything?q=corona&apiKey=5c446bdf16c64b69835ea834edb07322");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try {
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
		} 
		catch (ProtocolException e) {
			e.printStackTrace();
		}
		
		GetNews news = new GetNews();
		BufferedReader br = news.getNewsFromKeyword(keyword);
		ObjectMapper mapper = new ObjectMapper();
		NewsSearchResult articlearray = mapper.readerFor(NewsSearchResult.class).readValue(br);
		
		ArrayList<Article> articles = articlearray.getArticles();
		GetTopCompaniesForNews topCompanyNews = new GetTopCompaniesForNews();
		ArrayList<String> newsCompanies = topCompanyNews.getAllNewsComp(articles);
		
		return newsCompanies;
	}

}
