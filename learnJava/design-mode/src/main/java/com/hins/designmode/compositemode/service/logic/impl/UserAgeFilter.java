package com.hins.designmode.compositemode.service.logic.impl;

import com.hins.designmode.compositemode.service.logic.BaseLogicFilter;

import java.util.Map;

/**
 * @author: hins
 * @created: 2020-08-05 08:14
 * @desc:
 **/
public class UserAgeFilter extends BaseLogicFilter {


    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("age");
    }
}
