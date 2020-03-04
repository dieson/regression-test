package com.ifchange.regressiontest.dao;


import com.ifchange.regressiontest.model.Testcases;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestcasesMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int insert(Testcases record);

    int insertSelective(Testcases record);

    Testcases selectByPrimaryKey(Integer id);

    List<Testcases> selectTestCaseAndSetSelective(Testcases record);

    List<Testcases> selectByPrimaryKeyList(List ids);

    List<Testcases> selectSelective(Testcases record);

    List<Testcases> selectBySetId(Integer id);

    Testcases selectTestcaseAndSetInfoByCaseId(Integer id);

    List<Testcases> selectAll();

    int updateByPrimaryKeySelective(Testcases record);

    int updateByPrimaryKeyWithBLOBs(Testcases record);

    int updateByPrimaryKey(Testcases record);
}