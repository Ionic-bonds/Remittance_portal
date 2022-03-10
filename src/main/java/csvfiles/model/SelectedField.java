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

@Entity(name = "SelectedField")
@Table(name = "selected_field")
public class SelectedField {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "selected_field_id")
    private long selectedFieldId;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "api_field_id")
    private ApiField apiFieldId;
    @Column(name = "selected_field_code")
    private String selectedFieldCode;
    @Column(name = "selected_field_value")
    private String selectedFieldValue;

    public SelectedField() {};

    public SelectedField(long selectedFieldId, ApiField apiFieldId, String selectedFieldCode, String selectedFieldValue) {
        this.selectedFieldId = selectedFieldId;
        this.apiFieldId = apiFieldId;
        this.selectedFieldCode = selectedFieldCode;
        this.selectedFieldValue = selectedFieldValue;
    }

    public long getSelectedFieldId() {
        return selectedFieldId;
    }

    public void setSelectedFieldId(long selectedFieldId) {
        this.selectedFieldId = selectedFieldId;
    }

    public long getApiFieldId() {
        return apiFieldId.getApiFieldId();
    }

    public void setApiFieldId(ApiField apiFieldId) {
        this.apiFieldId = apiFieldId;
    }

    public String getSelectedFieldCode() {
        return selectedFieldCode;
    }

    public void setSelectedFieldCode(String selectedFieldCode) {
        this.selectedFieldCode = selectedFieldCode;
    }

    public String getSelectedFieldValue() {
        return selectedFieldValue;
    }

    public void setSelectedFieldValue(String selectedFieldValue) {
        this.selectedFieldValue = selectedFieldValue;
    }
}