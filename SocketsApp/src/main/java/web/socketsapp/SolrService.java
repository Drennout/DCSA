package web.socketsapp;

import com.google.gson.Gson;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SolrService {
    @Value("${solr}")
    private String solrURL;

    @Autowired
    private HttpSolrClient client;

    public String findBookByAuthor(String author) {
        Gson gson = new Gson();
        SolrQuery query = new SolrQuery();
        query.set("q", "author:" + author);
        try {

            QueryResponse response = client.query(query);

            return gson.toJson(response.getResults());

        } catch (SolrServerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
