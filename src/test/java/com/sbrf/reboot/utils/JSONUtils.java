package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;


public class JSONUtils {

    public static String toJSON(Request request) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(request);
    }

    public static String toJSON(Response response) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(response);
    }

    public static Request JSONtoRequest(String jsonRequest) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonRequest, Request.class);
    }

    public static Response JSONtoResponse(String jsonResponse) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonResponse, Response.class);
    }

}
