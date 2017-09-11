package com.meirapentermann.presidents;

import java.util.List;

public interface PresidentDAO {
	
	public List<President> getFullList();
	public List<President> filterByLastName(String last);
	public List<President> filterByFirstName(String first);
	public List<President> filterByParty(String party);
	
	}

	
