package com.ifchange.regressiontest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "testcase_execution_results")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestcaseExecutionResults {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "test_execution_id不能为空")
    @Column(name = "test_execution_id", nullable = false)
    private Integer testExecutionId;

    @NotNull(message = "testcase_id不能为空")
    @Column(name = "testcase_id", nullable = false)
    private Integer testcaseId;

    @NotEmpty(message = "execution_status不能为空")
    @Column(name = "execution_status")
    private String executionStatus;

    @NotEmpty(message = "passed不能为空")
    @Column(name = "passed")
    private Boolean passed;

    @NotEmpty(message = "correctness不能为空")
    @Column(name = "correctness")
    private String correctness;

    @NotEmpty(message = "correctness_score不能为空")
    @Column(name = "correctness_score")
    private Double correctnessScore;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @NotEmpty(message = "result_data不能为空")
    @Column(name = "result_data")
    private String resultData;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToOne
    private Testcases testcases;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToOne
    private TestExecutionsInfo testExecutionsInfo;

    public TestExecutionsInfo getTestExecutionsInfo() {
        return testExecutionsInfo;
    }

    public void setTestExecutionsInfo(TestExecutionsInfo testExecutionsInfo) {
        this.testExecutionsInfo = testExecutionsInfo;
    }

    public Testcases getTestcases() {
        return testcases;
    }

    public void setTestcases(Testcases testcases) {
        this.testcases = testcases;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestExecutionId() {
        return testExecutionId;
    }

    public void setTestExecutionId(Integer testExecutionId) {
        this.testExecutionId = testExecutionId;
    }

    public Integer getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(Integer testcaseId) {
        this.testcaseId = testcaseId;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus == null ? null : executionStatus.trim();
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public String getCorrectness() {
        return correctness;
    }

    public void setCorrectness(String correctness) {
        this.correctness = correctness == null ? null : correctness.trim();
    }

    public Double getCorrectnessScore() {
        return correctnessScore;
    }

    public void setCorrectnessScore(Double correctnessScore) {
        this.correctnessScore = correctnessScore;
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

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData == null ? null : resultData.trim();
    }

    @Override
    public String toString() {
        return "TestcaseExecutionResults{" +
                "id=" + id +
                ", testExecutionId=" + testExecutionId +
                ", testcaseId=" + testcaseId +
                ", executionStatus='" + executionStatus + '\'' +
                ", passed=" + passed +
                ", correctness='" + correctness + '\'' +
                ", correctnessScore=" + correctnessScore +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", resultData='" + resultData + '\'' +
                '}';
    }
}