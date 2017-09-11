package com.meirapentermann.presidents;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;

public class PresidentFileDAO implements PresidentDAO {
	private static final String filename = "WEB-INF/presidents.txt";
	private ServletContext servletContext;
	private List<President> presidents;
	
	public PresidentFileDAO(ServletContext context) {
		servletContext = context;
		presidents = new ArrayList<>();
		loadPresidentsFromFile();
	}

//PresidentOBJ(String party, String name, String image, String factoid, int termNum) {
	private void loadPresidentsFromFile() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		InputStream is = servletContext.getResourceAsStream(filename);
		try (BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = buf.readLine()) != null) {
				String[] data = line.split(",");
				int termNum = Integer.parseInt(data[0]);
				String fn = data[1];
				String mn = data[2];
				String ln = data[3];
				String range = data[4];
				String link = data[5];
				String party = data[6];
				String reason = data[7];
				presidents.add(new President(termNum, fn, mn, ln, range, link, party, reason));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Override
	public List<President> getFullList() {
		return this.presidents;
	}

	public List<President> getPresidents() {
		return presidents;
	}

	public void setPresidents(List<President> presidents) {
		this.presidents = presidents;
	}
	
	public List<President> filterByLastName(String last) {
		List<President> answer = new ArrayList<>();
		last = last.toLowerCase();
		for (int i = 0; i < presidents.size(); i++) {
			String lastName = presidents.get(i).getLastName().toLowerCase();
			if (lastName.contains(last)) {
				answer.add(presidents.get(i));
			}
		}
		return answer;
	}
	
	public List<President> filterByFirstName(String first) {
		List<President> answer = new ArrayList<>();
		first = first.toLowerCase();
		for (int i = 0; i < presidents.size(); i++) {
			String firstName = presidents.get(i).getFirstName().toLowerCase();
			if (firstName.contains(first)) {
				answer.add(presidents.get(i));
			}
		}
		return answer;
	}
	
	public List<President> filterByParty(String party) {
		List<President> answer = new ArrayList<>();
		party = party.toLowerCase();
		for (int i = 0; i < presidents.size(); i++) {
			String theParty = presidents.get(i).getParty().toLowerCase();
			if (theParty.contains(party)) {
				answer.add(presidents.get(i));
			}
		}
		return answer;
	}
}
