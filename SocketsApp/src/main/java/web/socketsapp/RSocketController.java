package web.socketsapp;

import com.google.gson.Gson;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Controller
public class RSocketController {

    @Autowired
    private SolrService solrService;


    @MessageMapping("request-response")
    public Mono<String> reqResp(String author) {
        return Mono.just(solrService.findBookByAuthor(author));
    }

    @MessageMapping("fire-and-forget")
    public Mono<Void> fireAndForget(BookPayload bookPayload) {
        System.out.println("fire-and-forget");
//        BookPayload bookPayload = new BookPayload(123, Arrays.asList("book", "hardcover"), "New Book", "Author 1");
        solrService.indexingBook(bookPayload);

        return Mono.empty();
    }

}
