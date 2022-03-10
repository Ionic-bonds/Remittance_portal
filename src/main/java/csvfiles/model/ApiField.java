package csvfiles.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.JsonIdentityReference;
// import com.fasterxml.jackson.annotation.JsonProperty;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "ApiField")
@Table(name = "api_field")
public class ApiField {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "api_field_id")
    private long apiFieldId;
    // @JsonProperty("api_id")
    // @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "apiId")
    // @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "api_id")
    private Api apiId;
    @Column(name = "api_field_name")
    private String apiFieldName;
    @Column(name = "datatype")
    private String datatype;
    @Column(name = "mandatory")
    private boolean mandatory;

    public ApiField() {}

    public ApiField(long apiFieldId, Api apiId, String apiFieldName, String datatype, boolean mandatory) {
        this.apiFieldId = apiFieldId;
        this.apiId = apiId;
        this.apiFieldName = apiFieldName;
        this.datatype = datatype;
        this.mandatory = mandatory;
    }

    public long getApiFieldId() {
        return apiFieldId;
    }

    public String getApiFieldName() {
        return apiFieldName;
    }

    public void setApiFieldName(String apiFieldName) {
        this.apiFieldName = apiFieldName;
    }

    public long getApiId() {
        return apiId.getApiId();
    }

    public void setApiId(Api apiId) {
        this.apiId = apiId;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
}