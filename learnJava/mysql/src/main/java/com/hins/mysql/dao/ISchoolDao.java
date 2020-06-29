package com.hins.mysql.dao;


import com.hins.mysql.po.School;

public interface ISchoolDao {

    School querySchoolInfoById(Long treeId);

}
