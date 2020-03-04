package com.ifchange.regressiontest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "testcase_service_input_templates")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestcaseServiceInputTemplates {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "datasetTypeId不能为空")
    @Column(name = "dataset_type_id", nullable = false)
    private Integer datasetTypeId;

    @Column(name = "request_template")
    @NotEmpty(message = "requestTemplate不能为空")
    private String requestTemplate;

    @Column(name = "response_template")
    @NotEmpty(message = "responseTemplate不能为空")
    private String responseTemplate;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToOne
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

    public String getRequestTemplate() {
        return requestTemplate;
    }

    public void setRequestTemplate(String requestTemplate) {
        this.requestTemplate = requestTemplate;
    }

    public String getResponseTemplate() {
        return responseTemplate;
    }

    public void setResponseTemplate(String responseTemplate) {
        this.responseTemplate = responseTemplate;
    }

    @Override
    public String toString() {
        return "TestcaseServiceInputTemplates{" +
                "id=" + id +
                ", datasetTypeId=" + datasetTypeId +
                ", requestTemplate='" + requestTemplate + '\'' +
                ", responseTemplate='" + responseTemplate + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", testDatasetTypesInfo=" + testDatasetTypesInfo +
                '}';
    }
}