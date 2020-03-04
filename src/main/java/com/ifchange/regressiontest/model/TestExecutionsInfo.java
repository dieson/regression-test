package com.ifchange.regressiontest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "test_executions_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestExecutionsInfo {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "name不能为空")
    private String name;

    @Column(name = "desc")
    @NotEmpty(message = "desc不能为空")
    private String desc;

    @NotNull(message = "testDatasetId不能为空")
    @Column(name = "test_dataset_id", nullable = false)
    private Integer testDatasetId;

    @Column(name = "testcase_count")
    private Integer testcaseCount;

    @Column(name = "finished_testcase_count")
    private Integer finishedTestcaseCount;

    @Column(name = "test_execution_status")
    private String testExecutionStatus;

    @Column(name = "passed_testcase_count")
    private Integer passedTestcaseCount;

    @Column(name = "correctness_score")
    private Double correctnessScore;

    @Column(name = "adjusted_correctness_score")
    private Double adjustedCorrectnessScore;

    @Column(name = "correctness_distribution")
    private String correctnessDistribution;

    @Column(name = "service_address")
    private String serviceAddress;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private TestDatasetsInfo testDatasetsInfo;

    public TestDatasetsInfo getTestDatasetsInfo() {
        return testDatasetsInfo;
    }

    public void setTestDatasetsInfo(TestDatasetsInfo testDatasetsInfo) {
        this.testDatasetsInfo = testDatasetsInfo;
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

    public Integer getTestDatasetId() {
        return testDatasetId;
    }

    public void setTestDatasetId(Integer testDatasetId) {
        this.testDatasetId = testDatasetId;
    }

    public Integer getTestcaseCount() {
        return testcaseCount;
    }

    public void setTestcaseCount(Integer testcaseCount) {
        this.testcaseCount = testcaseCount;
    }

    public Integer getFinishedTestcaseCount() {
        return finishedTestcaseCount;
    }

    public void setFinishedTestcaseCount(Integer finishedTestcaseCount) {
        this.finishedTestcaseCount = finishedTestcaseCount;
    }

    public String getTestExecutionStatus() {
        return testExecutionStatus;
    }

    public void setTestExecutionStatus(String testExecutionStatus) {
        this.testExecutionStatus = testExecutionStatus == null ? null : testExecutionStatus.trim();
    }

    public Integer getPassedTestcaseCount() {
        return passedTestcaseCount;
    }

    public void setPassedTestcaseCount(Integer passedTestcaseCount) {
        this.passedTestcaseCount = passedTestcaseCount;
    }

    public Double getCorrectnessScore() {
        return correctnessScore;
    }

    public void setCorrectnessScore(Double correctnessScore) {
        this.correctnessScore = correctnessScore;
    }

    public Double getAdjustedCorrectnessScore() {
        return adjustedCorrectnessScore;
    }

    public void setAdjustedCorrectnessScore(Double adjustedCorrectnessScore) {
        this.adjustedCorrectnessScore = adjustedCorrectnessScore;
    }

    public String getCorrectnessDistribution() {
        return correctnessDistribution;
    }

    public void setCorrectnessDistribution(String correctnessDistribution) {
        this.correctnessDistribution = correctnessDistribution == null ? null : correctnessDistribution.trim();
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress == null ? null : serviceAddress.trim();
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
        return "TestExecutionsInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", testDatasetId=" + testDatasetId +
                ", testcaseCount=" + testcaseCount +
                ", finishedTestcaseCount=" + finishedTestcaseCount +
                ", testExecutionStatus='" + testExecutionStatus + '\'' +
                ", passedTestcaseCount=" + passedTestcaseCount +
                ", correctnessScore=" + correctnessScore +
                ", adjustedCorrectnessScore=" + adjustedCorrectnessScore +
                ", correctnessDistribution='" + correctnessDistribution + '\'' +
                ", serviceAddress='" + serviceAddress + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}