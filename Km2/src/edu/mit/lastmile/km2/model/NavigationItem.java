package edu.mit.lastmile.km2.model;

public class NavigationItem {

	public static final int NAVIGATION_PROFILE = 0;
	public static final int NAVIGATION_MENU = 1;
	public static final int NAVIGATION_SEPARATOR = 2;
	
	private int type;
	private int icon;
	private String label;
	private String name;
	private String km2;
	private String location;
	
	public NavigationItem(){
		this.type = NAVIGATION_SEPARATOR;
	}
	
	public NavigationItem(int icon, String label){
		this.icon = icon;
		this.label = label;
		this.type = NAVIGATION_MENU;
	}
	
	public NavigationItem(String name, String km2, String location){
		this.name = name;
		this.km2 = km2;
		this.location = location;
		this.type = NAVIGATION_PROFILE;
	}

	public int getType(){
		return type;
	}
	
	public int getIcon() {
		return icon;
	}

	public String getLabel() {
		return label;
	}
	
	public String getName(){
		return name;
	}
	
	public String getKm2(){
		return km2;
	}
	
	public String getLocation(){
		return location;
	}
	
}
