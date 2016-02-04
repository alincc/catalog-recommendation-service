package no.nb.microservices.recommendation.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootResponse extends ResourceSupport {
    @JsonProperty("_embedded")
    private EmbeddedWrapper embeddedWrapper;

    public RootResponse() {
    }

    public RootResponse(EmbeddedWrapper embeddedWrapper) {
        this.embeddedWrapper = embeddedWrapper;
    }
}
