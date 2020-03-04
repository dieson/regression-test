package com.ifchange.regressiontest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "testcases")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Testcases {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "dataset_id不能为空")
    @Column(name = "dataset_id", nullable = false)
    private Integer datasetId;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "data")
    @NotEmpty(message = "data不能为空")
    private String data;

    @Column(name = "expect_data")
    @NotEmpty(message = "expectData不能为空")
    private String expectData;

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

    public Integer getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Integer datasetId) {
        this.datasetId = datasetId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public String getExpectData() {
        return expectData;
    }

    public void setExpectData(String expectData) {
        this.expectData = expectData == null ? null : expectData.trim();
    }

    @Override
    public String toString() {
        return "Testcases{" +
                "id=" + id +
                ", datasetId=" + datasetId +
                ", weight=" + weight +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", data='" + data + '\'' +
                ", expectData='" + expectData + '\'' +
                '}';
    }
}