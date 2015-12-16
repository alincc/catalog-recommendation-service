package no.nb.utils.recommendation.populator;

import com.opencsv.CSVWriter;
import no.nb.microservices.recommendation.model.query.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@RestController
public class HomeController {

    private static final String ITEM_URL = "http://localhost:8109/v1/catalog/recommend/items";
    private static final String ACTION_URL = "http://localhost:8109/v1/catalog/recommend/items/{itemId}/actions";
    private static final String QUERY_URL = "http://localhost:8109/v1/catalog/recommend/query/othershavevisited?itemId={itemId}";


    @RequestMapping("/generate")
    public String setup(@RequestParam int maxItems, @RequestParam int maxActions) throws IOException {
        long start = System.currentTimeMillis();

        Location loc1 = new Location("Norway", "Nordland", "Rana");
        Location loc2 = new Location("Norway", "Nordland", "Grong");
        Location loc3 = new Location("Norway", "Nordland", "Salten");
        Location loc4 = new Location("Norway", "Finnmark", "Alta");
        Location loc5 = new Location("Sweden", "Vâsterbotten", "Hemavan");
        Location loc6 = new Location("Norway", "Akershus", "Oslo");
        Location loc7 = new Location("Norway", "Hedmark", "Lillhammer");
        Location loc8 = new Location("Norway", "Hedmark", "Gjøvik");
        Location loc9 = new Location("Norway", "Hedmark", "Brummendal");
        Location loc10 = new Location("Norway", "Hedmark", "Hamar");

        List<String> mediaTypes = new ArrayList<>(Arrays.asList("bøker", "film", "aviser", "radio", "fjernsyn", "foto", "musikk", "plakater", "programrapporter", "lydopptak", "musikkmanuskripter", "privatarkiv", "kart", "noter", "tidsskrift"));
        List<String> publishers = new ArrayList<>(Arrays.asList("Andreas", "Alfred", "Jim", "Raymond", "Ronny", "Geir", "Alf", "Henrik", "Sture", "Knut", "Leif", "Sara", "Bjørg", "Per", "Arne"));
        List<String> topics = new ArrayList<>(Arrays.asList("Mat", "Skog", "Ski", "Fjell", "Dataspill", "Landskap", "Programmering", "Telefoni", "Planeter", "Verdensrommet"));
        List<String> interests = new ArrayList<>(Arrays.asList("Mat", "Skog", "Ski", "Fjell", "Gaming", "Software Development", "Festing", "Snikring", "Løping", "Fjellturer", "Sove"));
        List<String> queries = new ArrayList<>(Arrays.asList("Folkemusikk fra Rana", "Dagbladet", "Donald duck jul", "", "", "", "", "", "", "Spill", "Fjellfolk", "Data i rommet", "Widerøe Rana"));
        List<String> actions = new ArrayList<>(Arrays.asList("DOWNLOADED", "LIKED"));
        List<Location> locations = Arrays.asList(loc1, loc2, loc3, loc4, loc5, loc6, loc7, loc8, loc9, loc10);

        // Items
        createItems(maxItems, mediaTypes, publishers, topics, locations);

        //Actions
        createActions(maxItems, maxActions, interests, queries, locations);

        long stop = System.currentTimeMillis();
        return "Used: " + (stop - start) + "ms";
    }

    private void createItems(int maxItems, List<String> mediaTypes, List<String> publishers, List<String> topics, List<Location> locations) throws IOException {
        String itemsFile = "items.csv";
        CSVWriter itemsWriter = new CSVWriter(new FileWriter(itemsFile, false), ',');
        itemsWriter.writeNext(new String[]{"itemId", "mediaType", "publisher", "country", "county", "municipality", "topics"});

        for (int i = 0; i < maxItems; i++) {
            Item item = new Item();
            item.setItemId(i + "");
            item.setMediaType(mediaTypes.get(randInt(mediaTypes.size())));
            item.setLocation(locations.get(randInt(locations.size())));
            item.setPublisher(publishers.get(randInt(publishers.size())));

            List<String> itemTopics = new ArrayList<String>();
            for (int t = 0; t < randInt(topics.size()); t++) {
                if (!itemTopics.contains(topics.get(t))) {
                    itemTopics.add(topics.get(t));
                }
            }
            item.setTopics(itemTopics);

            String[] toWrite = new String[] {
                            item.getItemId(),
                            item.getMediaType(),
                            item.getPublisher(),
                            item.getLocation().getCountry(),
                            item.getLocation().getCounty(),
                            item.getLocation().getMunicipality(),
                            item.getTopics().toString().replaceAll("\\[|\\]", "")
            };
            itemsWriter.writeNext(toWrite, true);
        }

        itemsWriter.close();
    }

    private void createActions(int maxItems, int maxActions, List<String> interests, List<String> queries, List<Location> locations) throws IOException {
        String actionsFile = "actions.csv";
        CSVWriter actionsWriter = new CSVWriter(new FileWriter(actionsFile, false), ',');
        actionsWriter.writeNext(new String[]{"userId", "gender", "age", "interests", "sessionId", "country", "county", "municipality", "action", "query", "itemId"});

        // Action
        int actionCount = 0;
        for (int i = 0; i < maxActions; i++) {
            ItemAction action = new ItemAction();
            User user = new User();
            user.setAge(randInt(99));
            user.setGender(((i % 2) == 0) ? "MALE" : "FEMALE");
            user.setUserId(UUID.randomUUID().toString());

            List<String> userInterests = new ArrayList<String>();
            for (int t = 0; t < randInt(interests.size()); t++) {
                if (!userInterests.contains(interests.get(t))) {
                    userInterests.add(interests.get(t));
                }
            }
            user.setInterests(userInterests);

            action.setUser(user);

            for (int j = 0; j < randInt(10); j++) {
                Session session = new Session();
                session.setLocation(locations.get(randInt(locations.size())));
                session.setSessionId(UUID.randomUUID().toString());
                action.setSession(session);

                for (int p = 0; p < randInt(10); p++) {
                    action.setAction("VISITED");
                    action.setQuery(queries.get(randInt(queries.size())));

                    String[] toWrite = new String[] {
                            action.getUser().getUserId(),
                            action.getUser().getGender(),
                            action.getUser().getAge() + "",
                            action.getUser().getInterests().toString().replaceAll("\\[|\\]", ""), // Interests
                            action.getSession().getSessionId(),
                            action.getSession().getLocation().getCountry(),
                            action.getSession().getLocation().getCounty(),
                            action.getSession().getLocation().getMunicipality(),
                            action.getAction(),
                            action.getQuery(),
                            randInt(maxItems) + ""
                    };
                    actionsWriter.writeNext(toWrite, true);
                    actionCount++;

                    if (actionCount >= maxActions) {
                        actionsWriter.close();
                        return;
                    }

                }
            }
        }
        actionsWriter.close();
    }

    public static int randInt(int max) {
        return (int)(Math.random() * max);
    }
}
