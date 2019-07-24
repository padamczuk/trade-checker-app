package lab.orion.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NetworkResponse {
    private String knowledge;

    public NetworkResponse(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getKnowledge() {
        return knowledge;
    }
}
