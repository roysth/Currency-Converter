package vttp2022b2.app.CurrencyConverter.models;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;

public class Query {
    private static final Logger logger = LoggerFactory.getLogger(Query.class);

    private String from;
    private String to;
    private BigDecimal amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    //creating to Json object
    //the 'query' json object is gotten in Currency
    //this is to furthuer get the Json data you want
    public static Query createJson(JsonObject o) {
        logger.info("createJson query");
        Query q = new Query();
        // JsonObject queryObj = o.getJsonObject("query");
        String toStr = o.getString("to");
        q.to = toStr;
        String fromStr = o.getString("from");
        q.from = fromStr;
        JsonNumber jsNum = o.getJsonNumber("amount");
        q.amount = jsNum.bigDecimalValue();
        return q;
    }
}

//this from to amount vairables is the user query and the json query (getting the json data)
//althoguh he did not use the json query object 