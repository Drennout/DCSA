package web.socketsapp;

import com.google.gson.Gson;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SolrService {
    @Value("${solr}")
    private String solrURL;

    @Autowired
    private HttpSolrClient solr;

    public String findBookByAuthor(String author) {
        Gson gson = new Gson();
        SolrQuery query = new SolrQuery();
        query.set("q", "author:" + author);
        try {

            QueryResponse response = solr.query(query);

            return gson.toJson(response.getResults());

        } catch (SolrServerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void indexingBook(@NotNull BookPayload bookPayload) {
        SolrInputDocument doc = new SolrInputDocument();

        doc.addField("id", bookPayload.getId());
        doc.addField("cat", bookPayload.getCat());
        doc.addField("name", bookPayload.getName());
        doc.addField("author", bookPayload.getAuthor());
        doc.addField("_version_", -1);

        try {
            solr.add(doc);
            System.out.println(doc);
            solr.commit();
        } catch (SolrServerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
