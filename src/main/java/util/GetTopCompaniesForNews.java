package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import Model.Article;

public class GetTopCompaniesForNews {

	@SuppressWarnings("unchecked")
	public ArrayList<String> getTopNewsComp(ArrayList<Article> articles, int k) {
		Map<String, Integer> newsCompaniesMap = new HashMap<String, Integer>();
		for (Article art : articles) {

			if (newsCompaniesMap.get(art.getSource().getName()) == null) {
				newsCompaniesMap.put(art.getSource().getName(), 1);
			} else {
				newsCompaniesMap.put(art.getSource().getName(), newsCompaniesMap.get(art.getSource().getName()) + 1);
			}
		}

		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		newsCompaniesMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

		ArrayList<String> topnames = new ArrayList<String>();
		int count = 0;

		Set<String> keys = sortedMap.keySet();
		for (String name : keys) {
			if (count < k) {
				topnames.add(name);
				count++;
			} else {
				break;
			}

		}

		return topnames;
	}

	public ArrayList<String> getAllNewsComp(ArrayList<Article> articles) {
		Map<String, Integer> newsCompaniesMap = new HashMap<String, Integer>();
		for (Article art : articles) {

			if (newsCompaniesMap.get(art.getSource().getName()) == null) {
				newsCompaniesMap.put(art.getSource().getName(), 1);
			} else {
				newsCompaniesMap.put(art.getSource().getName(), newsCompaniesMap.get(art.getSource().getName()) + 1);
			}
		}

		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		newsCompaniesMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

		ArrayList<String> topnames = new ArrayList<String>();

		Set<String> keys = sortedMap.keySet();
		for (String name : keys) {
			topnames.add(name);
		}

		return topnames;
	}

}