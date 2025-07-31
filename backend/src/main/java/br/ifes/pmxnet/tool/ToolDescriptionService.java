package br.ifes.pmxnet.tool;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.stereotype.Service;

@Service
public class ToolDescriptionService {

    private static final String DBPEDIA_SPARQL = "https://dbpedia.org/sparql";

    public String getDescription(String entity) throws Exception {
        String query = String.format("""
            PREFIX dbo: <http://dbpedia.org/ontology/>
            PREFIX dbr: <http://dbpedia.org/resource/>
            SELECT ?abstract WHERE {
              dbr:%s dbo:abstract ?abstract .
              FILTER (lang(?abstract) = "en")
            } LIMIT 1
            """, entity);

        String url = DBPEDIA_SPARQL + "?query=" + java.net.URLEncoder.encode(query, "UTF-8") + "&format=json";

        String response = Request.get(url)
                .execute().returnContent().asString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        JsonNode results = root.path("results").path("bindings");
        if (results.isArray() && results.size() > 0) {
            return results.get(0).path("abstract").path("value").asText();
        } else {
            return null;
        }
    }
}
