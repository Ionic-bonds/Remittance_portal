package csvfiles.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Api")
@Table(name = "api")

public class Api {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "api_id")
    private long apiId;
    @Column(name = "api_name")
    private String apiName;

    public Api() {}

    public Api(long apiId, String apiName) {
        this.apiId = apiId;
        this.apiName = apiName;
    }

    public long getApiId() {
        return apiId;
    }

    public void setApiId(long apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}