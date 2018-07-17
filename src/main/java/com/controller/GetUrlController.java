package com.controller;

import com.bean.Url;
import com.common.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@RestController
@RequestMapping("/get")
public class GetUrlController {

    @RequestMapping("/init")
    public Object getUrl(){
        List<Url> list = Service.getInstance().urlService.findByStatus(0);
        return list;
    }

    @RequestMapping("/get")
    public Object queryUrl() throws IOException {
        Document doc = null;
        List<Url> list = Service.getInstance().urlService.findByStatus(0);
        for (Url u:list){
            doc = Jsoup.connect(u.getUrl()).get();
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.open-open.com/jsoup/load-document-from-url.htm";
        Document doc = Jsoup.connect(url).get();
        Url u;
        for(Element e:doc.getElementsByTag("a")){
            u = new Url();
            u.setRemark(e.text());
            u.setUrl(e.attr("href"));
            u.setStatus(0);
//            Service.getInstance().urlService.save(u);
            System.out.println(u.toString());
        }
    }

}
