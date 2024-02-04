package pk.kleczek.challenge.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static java.util.stream.Collectors.toCollection;

/**
 * A helper to verify what are available properties
 */
public class CheckJsonProperties {
    public static void main(String[] args) throws IOException {
        File file = ResourceUtils.getFile("classpath:germany.json");
        JsonMapper jsonMapper = new JsonMapper();
        JsonNode root = jsonMapper.readTree(file);
        ArrayNode places = root.withArray("places");

        Set<Set<String>> observedKeys = new HashSet<>();

        for (JsonNode place : places) {
            Set<String> keys = place.properties()
                    .stream()
                    .map(e -> e.getKey())
                    .collect(toCollection(() -> new TreeSet<>()));
            observedKeys.add(keys);
        }

        for (Set<String> o : observedKeys) {
            System.out.println(o);
        }
    }
}
