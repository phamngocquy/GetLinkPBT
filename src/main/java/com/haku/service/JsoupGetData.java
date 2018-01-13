package com.haku.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JsoupGetData {

    @Value("${user.agent}")
    private String USER_AGENT;
    private String KEY = "phimbathu.com" + "4590481877" + "96480";
    private static final String ALGORITHM = "AES";

    public List<String> getServer(String url) {
        List<String> servers = new ArrayList<String>();
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/41.0.2228.0 Safari/537.36").timeout(60000).get();
            servers = EpID(document.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return servers;
    }
    private List<String> EpID(String html) {
        List<String> listEpID = new ArrayList<String>();
        Matcher matcher;
        do {
            Pattern pattern = Pattern.compile("\"file\":\"([^\"]+)\"");
            matcher = pattern.matcher(html);
            if (matcher.find()) listEpID.add(matcher.group(1).replaceAll("\\\\/", "/"));
            html = html.replace(matcher.group(0), "");
            matcher = pattern.matcher(html);
        } while (matcher.find());
        return listEpID;

    }
}


