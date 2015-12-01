package no.nb.microservices.clickstream.model;

import org.springframework.data.domain.Page;

public class ActivitiesPage {

    private final String sessionId;
    private final Page<Activity> page;

    public ActivitiesPage(String sessionId, Page<Activity> page) {
        this.sessionId = sessionId;
        this.page = page;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Page<Activity> getPage() {
        return page;
    }
}
