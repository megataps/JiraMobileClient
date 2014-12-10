package com.pyco.android.jiramobileclient.entities;

public class MenuItemEntity {

	public static final int ITEM = 0;
	public static final int SECTION = 1;

    private int type;
    private String name;
    private String iconUrl;

    public boolean isHighlight;

    public MenuItemEntity() {
        type = 0;
    	isHighlight = false;
    }

    public MenuItemEntity(int type, String name, String iconUrl) {

        this.type = type;
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setIconId(String name) {
        this.name = name;
    }

    public String geIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
