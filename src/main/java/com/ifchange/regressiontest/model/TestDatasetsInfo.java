package com.ifchange.regressiontest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "test_datasets_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDatasetsInfo {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "name不能为空")
    private String name;

    @Column(name = "desc")
    private String desc;

    @NotNull(message = "datasetTypeId不能为空")
    @Column(name = "dataset_type_id", nullable = false)
    private Integer datasetTypeId;

    @Column(name = "test_case_count")
    private Integer testCaseCount;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private TestDatasetTypesInfo testDatasetTypesInfo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "testDatasetsInfo")
    private List<Testcases> testcasesList;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "testDatasetsInfo")
    private List<TestExecutionsInfo> testExecutionsInfoList;

    public List<TestExecutionsInfo> getTestExecutionsInfoList() {
        return testExecutionsInfoList;
    }

    public void setTestExecutionsInfoList(List<TestExecutionsInfo> testExecutionsInfoList) {
        this.testExecutionsInfoList = testExecutionsInfoList;
    }

    public List<Testcases> getTestcasesList() {
        return testcasesList;
    }

    public void setTestcasesList(List<Testcases> testcasesList) {
        this.testcasesList = testcasesList;
    }

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

    public Integer getDatasetTypeId() {
        return datasetTypeId;
    }

    public void setDatasetTypeId(Integer datasetTypeId) {
        this.datasetTypeId = datasetTypeId;
    }

    public Integer getTestCaseCount() {
        return testCaseCount;
    }

    public void setTestCaseCount(Integer testCaseCount) {
        this.testCaseCount = testCaseCount;
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
        return "TestDatasetsInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", datasetTypeId=" + datasetTypeId +
                ", testCaseCount=" + testCaseCount +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", testDatasetTypesInfo=" + testDatasetTypesInfo +
                ", testcasesList=" + testcasesList +
                ", testExecutionsInfoList=" + testExecutionsInfoList +
                '}';
    }
}