package com.ifchange.regressiontest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "test_dataset_types_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDatasetTypesInfo {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "name不能为空")
    private String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "default_service_address")
    @NotEmpty(message = "defaultServiceAddress不能为空")
    private String defaultServiceAddress;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy="testDatasetTypesInfo")
    private List<TestDatasetsInfo> testDatasetsInfoList;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "testDatasetTypesInfo")
    private List<TestDatasetUploadFormatTemplates> testDatasetUploadFormatTemplatesList;

    public List<TestDatasetUploadFormatTemplates> getTestDatasetUploadFormatTemplatesList() {
        return testDatasetUploadFormatTemplatesList;
    }

    public void setTestDatasetUploadFormatTemplatesList(List<TestDatasetUploadFormatTemplates> testDatasetUploadFormatTemplatesList) {
        this.testDatasetUploadFormatTemplatesList = testDatasetUploadFormatTemplatesList;
    }

    public List<TestDatasetsInfo> getTestDatasetsInfoList() {
        return testDatasetsInfoList;
    }

    public void setTestDatasetsInfoList(List<TestDatasetsInfo> testDatasetsInfoList) {
        this.testDatasetsInfoList = testDatasetsInfoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getDefaultServiceAddress() {
        return defaultServiceAddress;
    }

    public void setDefaultServiceAddress(String defaultServiceAddress) {
        this.defaultServiceAddress = defaultServiceAddress == null ? null : defaultServiceAddress.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
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
        return "TestDatasetTypesInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", defaultServiceAddress='" + defaultServiceAddress + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}