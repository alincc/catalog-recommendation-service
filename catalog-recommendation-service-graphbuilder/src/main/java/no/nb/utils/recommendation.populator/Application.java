package no.nb.utils.recommendation.populator;


import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        GraphBuilder graphBuilder = new GraphBuilder();

        int maxItems = 500000;
        int maxActions = 10000000;

        graphBuilder.setup(maxItems, maxActions);
    }
}
