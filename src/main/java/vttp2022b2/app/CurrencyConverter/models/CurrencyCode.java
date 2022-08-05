package vttp2022b2.app.CurrencyConverter.models;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

//this method is for the list of currency
//https://openexchangerates.org/api/currencies.json

public class CurrencyCode {
    //Using Map class with both entries as Strings <A,B>
    //A = key (not numerical index) object type, B = object type
    static Map<String, String> currencyCodeLst = new LinkedHashMap<>();

    public Map<String, String> getCurrencyCodeLst() {
        return currencyCodeLst;
    }

    public void setCurrencyCodeLst(Map<String, String> currencyCodeLst) {
        CurrencyCode.currencyCodeLst = currencyCodeLst;
    }

    //Converting the Json string into Json object, then into String to be stored 
    //in the hashmap
    public static Map<String, String> lsOfCountryCode(String json) {

        InputStream is = new ByteArrayInputStream(json.getBytes());

        try (JsonReader jr = Json.createReader(is)) {
            JsonObject currenciesJo = jr.readObject();
            SortedSet<String> sortedKeys = new TreeSet<>(currenciesJo.keySet());

            Iterator<String> key = sortedKeys.iterator();

            while (key.hasNext()) {
                String geo = key.next();
                currencyCodeLst.put(currenciesJo.get(geo).toString(), geo);
            }

        }

        return currencyCodeLst;
    }
}
