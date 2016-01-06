package no.nb.utils.recommendation.populator;

import com.opencsv.CSVWriter;
import no.nb.microservices.recommendation.model.query.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphBuilder {

    public void setup(@RequestParam int maxItems, @RequestParam int maxActions) throws IOException {
        Location loc1 = new Location("Norway", "Nordland", "Rana");
        Location loc2 = new Location("Norway", "Nordland", "Grong");
        Location loc3 = new Location("Norway", "Nordland", "Salten");
        Location loc4 = new Location("Norway", "Finnmark", "Alta");
        Location loc5 = new Location("Sweden", "Vâsterbotten", "Hemavan");
        Location loc6 = new Location("Norway", "Akershus", "Oslo");
        Location loc7 = new Location("Norway", "Hedmark", "Lillehammer");
        Location loc8 = new Location("Norway", "Hedmark", "Gjovik");
        Location loc9 = new Location("Norway", "Hedmark", "Brummendal");
        Location loc10 = new Location("Norway", "Hedmark", "Hamar");

        List<Location> locations = Arrays.asList(loc1, loc2, loc3, loc4, loc5, loc6, loc7, loc8, loc9, loc10);
        List<String> mediaTypes = new ArrayList<>(Arrays.asList("bøker", "film", "aviser", "radio", "fjernsyn", "foto", "musikk", "plakater", "programrapporter", "lydopptak", "musikkmanuskripter", "privatarkiv", "kart", "noter", "tidsskrift"));
        List<String> publishers = new ArrayList<>(Arrays.asList("Andreas", "Alfred", "Jim", "Raymond", "Ronny", "Geir", "Alf", "Henrik", "Sture", "Knut", "Leif", "Sara", "Bjorg", "Per", "Arne"));
        List<String> topics = new ArrayList<>(Arrays.asList("Mat", "Skog", "Ski", "Fjell", "Dataspill", "Landskap", "Programmering", "Telefoni", "Planeter", "Verdensrommet"));
        List<String> interests = new ArrayList<>(Arrays.asList("Mat", "Skog", "Ski", "Fjell", "Gaming", "Software Development", "Festing", "Snikring", "Løping", "Fjellturer", "Sove"));
        List<String> queries = new ArrayList<>(Arrays.asList("Folkemusikk fra Rana", "Dagbladet", "Donald duck jul", "", "", "", "", "", "", "Spill", "Fjellfolk", "Data i rommet", "Widerøe Rana"));
        List<String> actions = new ArrayList<>(Arrays.asList("DOWNLOADED", "LIKED", "VISITED"));

        // Items
        createItems(maxItems, mediaTypes, publishers, topics, locations);

        //Actions
        createActions(maxItems, maxActions, interests, queries, locations);
    }

    private void createItems(int maxItems, List<String> mediaTypes, List<String> publishers, List<String> topics, List<Location> locations) throws IOException {
        CSVWriter itemsWriter = new CSVWriter(new FileWriter("catalog-recommendation-service-graphbuilder/data/items.csv", false), ',');
        itemsWriter.writeNext(new String[]{"itemId:Id", "mediaType", "topics", ":LABEL"}, false);

        CSVWriter itemHasLocatonWriter = new CSVWriter(new FileWriter("catalog-recommendation-service-graphbuilder/data/itemHasLocation.csv", false), ',');
        itemHasLocatonWriter.writeNext(new String[]{":START_ID", ":END_ID", ":TYPE"}, false);

        CSVWriter itemPublishedByWriter = new CSVWriter(new FileWriter("catalog-recommendation-service-graphbuilder/data/itemPublishedBy.csv", false), ',');
        itemPublishedByWriter.writeNext(new String[]{":START_ID", ":END_ID", ":TYPE"}, false);

        for (int i = 0; i < maxItems; i++) {
            Item item = new Item();
            item.setItemId("item-" + i);
            item.setMediaType(mediaTypes.get(randInt(mediaTypes.size())));
            item.setLocation(locations.get(randInt(locations.size())));
            item.setPublisher(publishers.get(randInt(publishers.size())));

            // Build topics
            List<String> itemTopics = new ArrayList<String>();
            for (int t = 0; t < randInt(topics.size()); t++) {
                if (!itemTopics.contains(topics.get(t))) {
                    itemTopics.add(topics.get(t));
                }
            }
            item.setTopics(itemTopics);

            // Write Item
            itemsWriter.writeNext(new String[] {item.getItemId(), item.getMediaType(), item.getTopics().toString().replaceAll("\\[|\\]", ""), "Item"}, false);

            // Write Item has location
            itemHasLocatonWriter.writeNext(new String[] {item.getItemId(), item.getLocation().getMunicipality().toLowerCase(), "HAS_LOCATION"}, false);

            // Write Item published by
            itemPublishedByWriter.writeNext(new String[] {item.getPublisher().toLowerCase(), item.getItemId(), "PUBLISHED"}, false);
        }

        itemsWriter.close();
        itemHasLocatonWriter.close();
        itemPublishedByWriter.close();
    }

    private void createActions(int maxItems, int maxActions, List<String> interests, List<String> queries, List<Location> locations) throws IOException {
        CSVWriter usersWriter = new CSVWriter(new FileWriter("catalog-recommendation-service-graphbuilder/data/users.csv", false), ',');
        usersWriter.writeNext(new String[]{"userId:Id", "age:int", "gender", "interests", ":LABEL"}, false);

        CSVWriter sessionWriter = new CSVWriter(new FileWriter("catalog-recommendation-service-graphbuilder/data/sessions.csv", false), ',');
        sessionWriter.writeNext(new String[]{"sessionId:Id", "timestamp:int", ":LABEL"}, false);

        CSVWriter userHasSessionWriter = new CSVWriter(new FileWriter("catalog-recommendation-service-graphbuilder/data/userHasSession.csv", false), ',');
        userHasSessionWriter.writeNext(new String[]{":START_ID", ":END_ID", ":TYPE"}, false);

        CSVWriter sessionActionsWriter = new CSVWriter(new FileWriter("catalog-recommendation-service-graphbuilder/data/sessionActions.csv", false), ',');
        sessionActionsWriter.writeNext(new String[]{":START_ID", ":END_ID", ":TYPE"}, false);

        // Action
        int actionCount = 0;
        for (int i = 0; i < maxActions; i++) {
            ItemAction action = new ItemAction();
            User user = new User();
            user.setAge(randInt(99));
            user.setGender(((i % 2) == 0) ? "MALE" : "FEMALE");
            user.setUserId("user-" + i);

            // Builds interests
            List<String> userInterests = new ArrayList<String>();
            for (int t = 0; t < randInt(interests.size()); t++) {
                if (!userInterests.contains(interests.get(t))) {
                    userInterests.add(interests.get(t));
                }
            }
            user.setInterests(userInterests);
            action.setUser(user);

            // Write users
            usersWriter.writeNext(new String[] {user.getUserId(), user.getAge() + "", user.getGender(), user.getInterests().toString().replaceAll("\\[|\\]", ""), "User"}, false);

            for (int j = 0; j < randInt(10); j++) {
                Session session = new Session();
                session.setLocation(locations.get(randInt(locations.size())));
                session.setSessionId("session-" + i + "." +  j);
                session.setDate(getRandomDate());
                action.setSession(session);

                // Write sessions
                sessionWriter.writeNext(new String[] {session.getSessionId(), session.getDate().getTime() + "", "Session"}, false);

                // Write User has session
                userHasSessionWriter.writeNext(new String[] {user.getUserId(), session.getSessionId(), "CREATED"}, false);

                for (int p = 0; p < randInt(10); p++) {
                    action.setAction("VISITED");
                    action.setQuery(queries.get(randInt(queries.size())));

                    // Write User has session
                    sessionActionsWriter.writeNext(new String[] {action.getSession().getSessionId(), "item-" + randInt(maxItems), action.getAction()}, false);

                    actionCount++;

                    if (actionCount >= maxActions) {
                        usersWriter.close();
                        sessionWriter.close();
                        userHasSessionWriter.close();
                        sessionActionsWriter.close();
                        return;
                    }

                }
            }
        }
        usersWriter.close();
        sessionWriter.close();
        userHasSessionWriter.close();
        sessionActionsWriter.close();
    }

    public static Date getRandomDate() {
        Random r = new Random();
        long unixtime = (long) (1293861599+r.nextDouble()*60*60*24*365);
        Date d = new Date(unixtime);
        return d;
    }

    public static int randInt(int max) {
        return (int)(Math.random() * max);
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
