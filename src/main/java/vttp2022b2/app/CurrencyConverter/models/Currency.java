package vttp2022b2.app.CurrencyConverter.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Currency {
    private static final Logger logger = LoggerFactory.getLogger(Currency.class);

    private String success;
    private String date;
    private BigDecimal result;
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private Query query;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
    
    //unpack the Json string into Json object, and making it into a real string
    public static Currency createJson(String json) throws IOException {
        logger.info("currency createJson");
        Currency c = new Currency();

        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            logger.info(">>>>>>>" + o.getJsonObject("query"));
            //the getString is to get the Json String
            //the getJsonString will convert it into normal string
            //get the info from the JSon object based on what is required 

            //get the "query" Json object
            c.query = Query.createJson(o.getJsonObject("query"));
            //get the "date" string
            c.date = o.getJsonString("date").getString();
            //get the "success" string. Another method of writing. similar to the 'date'
            //this is used cus the system things its a boolean so have to write it this way
            //can use either method
            c.success = o.get("success").toString();
            //get the "result" value
            c.result = o.getJsonNumber("result").bigDecimalValue();
            logger.info(">>>>>>>" + c.toString());

        }
        return c;
    }

}
