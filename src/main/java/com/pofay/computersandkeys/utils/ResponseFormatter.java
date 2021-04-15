package com.pofay.computersandkeys.utils;

import com.pofay.computersandkeys.entities.Computer;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseFormatter {

    public static JSONObject composeResponse(Computer c) throws JSONException {
        JSONObject color = new JSONObject();
        color.put("color", c.getColors());
        JSONObject computerDetails = toJSON(c);
        computerDetails.put("colors", color);
        JSONObject response = new JSONObject();
        response.put("computer", computerDetails);
        return response;
    }

    public static JSONObject toJSON(Computer c) throws JSONException {
        JSONObject computerDetails = new JSONObject();
        computerDetails.put("maker", c.getMaker());
        computerDetails.put("language", c.getLanguage());
        computerDetails.put("model", c.getModelNumber());
        computerDetails.put("type", c.getType());
        return computerDetails;
    }
}
