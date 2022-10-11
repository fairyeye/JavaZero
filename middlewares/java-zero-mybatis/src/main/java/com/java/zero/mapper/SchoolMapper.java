package com.java.zero.mapper;

import com.java.zero.entity.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 10586
* @description 针对表【school】的数据库操作Mapper
* @createDate 2022-09-28 15:12:00
* @Entity generator.entity.School
*/
@Mapper
public interface SchoolMapper {

    List<School> query();


}
