package com.ifchange.regressiontest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "test_dataset_upload_format_templates")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDatasetUploadFormatTemplates {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "datasetTypeId不能为空")
    @Column(name = "dataset_type_id", nullable = false)
    private Integer datasetTypeId;

    @NotEmpty(message = "requestColumName不能为空")
    @Column(name = "request_colum_name")
    private String requestColumName;

    @NotEmpty(message = "requestColumDataType不能为空")
    @Column(name = "request_colum_data_type")
    private String requestColumDataType;

    @NotEmpty(message = "responseColumName不能为空")
    @Column(name = "response_colum_name")
    private String responseColumName;

    @NotEmpty(message = "responseColumDataType不能为空")
    @Column(name = "response_colum_data_type")
    private String responseColumDataType;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private TestDatasetTypesInfo testDatasetTypesInfo;

    public TestDatasetTypesInfo getTestDatasetTypesInfo() {
        return testDatasetTypesInfo;
    }

    public void setTestDatasetTypesInfo(TestDatasetTypesInfo testDatasetTypesInfo) {
        this.testDatasetTypesInfo = testDatasetTypesInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDatasetTypeId() {
        return datasetTypeId;
    }

    public void setDatasetTypeId(Integer datasetTypeId) {
        this.datasetTypeId = datasetTypeId;
    }

    public String getRequestColumName() {
        return requestColumName;
    }

    public void setRequestColumName(String requestColumName) {
        this.requestColumName = requestColumName;
    }

    public String getRequestColumDataType() {
        return requestColumDataType;
    }

    public void setRequestColumDataType(String requestColumDataType) {
        this.requestColumDataType = requestColumDataType;
    }

    public String getResponseColumName() {
        return responseColumName;
    }

    public void setResponseColumName(String responseColumName) {
        this.responseColumName = responseColumName;
    }

    public String getResponseColumDataType() {
        return responseColumDataType;
    }

    public void setResponseColumDataType(String responseColumDataType) {
        this.responseColumDataType = responseColumDataType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "TestDatasetUploadFormatTemplates{" +
                "id=" + id +
                ", datasetTypeId=" + datasetTypeId +
                ", requestColumName='" + requestColumName + '\'' +
                ", requestColumDataType='" + requestColumDataType + '\'' +
                ", responseColumName='" + responseColumName + '\'' +
                ", responseColumDataType='" + responseColumDataType + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", testDatasetTypesInfo=" + testDatasetTypesInfo +
                '}';
    }
}