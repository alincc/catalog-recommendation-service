package no.nb.utils.recommendation.populator;


import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        GraphBuilder graphBuilder = new GraphBuilder();

        int maxItems = 3;
        int maxActions = 10;

        graphBuilder.setup(maxItems, maxActions);
    }
}
