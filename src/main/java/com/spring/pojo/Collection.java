package com.spring.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;



public class Collection {

	private long id;
	private List<String> lists;
	private Set<String> sets;
	private Map<String,String> map;
	private String[] array;
	private Properties properties;
	@Override
	public String toString() {
		return "Collection [id=" + id + ", lists=" + lists + ", sets=" + sets + ", map=" + map + ", array="
				+ Arrays.toString(array) + ", properties=" + properties + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<String> getLists() {
		return lists;
	}
	public void setLists(List<String> lists) {
		this.lists = lists;
	}
	public Set<String> getSets() {
		return sets;
	}
	public void setSets(Set<String> sets) {
		this.sets = sets;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public String[] getArray() {
		return array;
	}
	public void setArray(String[] array) {
		this.array = array;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
