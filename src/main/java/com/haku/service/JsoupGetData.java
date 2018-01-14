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

    @Value("${key.aes}")
    private String AESKEY;

    public List<String> getServer(String url) {
        List<String> servers = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/41.0.2228.0 Safari/537.36").timeout(60000).get();
            Pattern pattern;
            Matcher matcher;
            if (url.endsWith(".html")) {
                pattern = Pattern.compile("-(\\d+)_e(\\d+).html");
                matcher = pattern.matcher(url);
                if (matcher.find()) {
                    AESKEY = AESKEY + matcher.group(matcher.groupCount() - 1);
                    servers.add(AESKEY);
                    servers.addAll(EpID(document.toString()));
                }

            } else {
                pattern = Pattern.compile("-(\\d+)$");
                matcher = pattern.matcher(url);
                if (matcher.find()) {
                    AESKEY = AESKEY + matcher.group(matcher.groupCount());
                    servers.add(AESKEY);
                    servers.addAll(EpID(document.toString()));
                }

            }


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


