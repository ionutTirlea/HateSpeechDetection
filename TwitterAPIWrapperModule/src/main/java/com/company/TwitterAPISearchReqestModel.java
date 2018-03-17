package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by ionut.tirlea on 01/12/2017.
 */
public class TwitterAPISearchReqestModel {

    private String query;
    private String geocode;
    private String language;
    private String locale;
    private String resultType;
    private int count;
    private String until;
    private int sinceId;
    private int maxId;
    private boolean includeEntities;

    private static final String SEPARATOR = "&";

    private HashMap<String, String> requestParametersHashMap;

    public String getQueryURL(){
        return "";
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
        this.requestParametersHashMap.put("q", query);
    }

    public String getGeocode() {
        return geocode;
    }

    public void setGeocode(String geocode) {
        this.geocode = geocode;
        this.requestParametersHashMap.put("geocode", geocode);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        this.requestParametersHashMap.put("lang", language);
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
        this.requestParametersHashMap.put("locale", locale);
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public int getSinceId() {
        return sinceId;
    }

    public void setSinceId(int sinceId) {
        this.sinceId = sinceId;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public boolean isIncludeEntities() {
        return includeEntities;
    }

    public void setIncludeEntities(boolean includeEntities) {
        this.includeEntities = includeEntities;
    }

    public HashMap<String, String> getRequestParametersHashMap() {
        return requestParametersHashMap;
    }

    public void setRequestParametersHashMap(HashMap<String, String> requestParametersHashMap) {
        this.requestParametersHashMap = requestParametersHashMap;
    }
}
