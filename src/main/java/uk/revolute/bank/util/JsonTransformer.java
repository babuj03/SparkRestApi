package uk.revolute.bank.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}