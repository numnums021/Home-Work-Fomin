package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class XMLUtils {

    public static String toXML(Request request) throws JacksonException {
        return new XmlMapper().writeValueAsString(request);
    }

    public static String toXML(Response response) throws JacksonException {
        return new XmlMapper().writeValueAsString(response);
    }

    public static Request XMLtoRequest(String xmlRequest) throws JacksonException {
        return new XmlMapper().readValue(xmlRequest, Request.class);
    }

    public static Response XMLtoResponse(String xmlResponse) throws JacksonException{
        return new XmlMapper().readValue(xmlResponse, Response.class);
    }

}
