package com.haku.api;

import com.haku.service.JsoupGetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/token")
public class MakeToKen {

    @Autowired
    JsoupGetData jsoupGetData;

    @RequestMapping(value = "maketoken")
    public List<String> getToken(HttpServletRequest request) {
        List<String> result;
        String url = request.getHeader("url");
        result = jsoupGetData.getServer(url);
        return result;
    }
}
