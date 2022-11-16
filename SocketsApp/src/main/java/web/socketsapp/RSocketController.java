package web.socketsapp;

import com.google.gson.Gson;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class RSocketController {

    @Autowired
    private SolrService solrService;


    @MessageMapping("request-response")
    public Mono<String> reqResp(String author) {
        return Mono.just(solrService.findBookByAuthor(author));
    }


}
