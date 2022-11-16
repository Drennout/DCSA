package web.socketsapp;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConf {
    @Value("${solr}")
    private String solrURL;

    @Bean
    public HttpSolrClient httpSolrClient() {
        HttpSolrClient solr = new HttpSolrClient.Builder(solrURL).build();
        solr.setParser(new XMLResponseParser());
        return solr;
    }
}
