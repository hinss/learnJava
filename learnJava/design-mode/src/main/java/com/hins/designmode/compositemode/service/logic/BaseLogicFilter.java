package com.hins.designmode.compositemode.service.logic;

import com.hins.designmode.compositemode.model.TreeNodeLink;

import java.util.List;
import java.util.Map;

/**
 * @author: hins
 * @created: 2020-08-05 08:11
 * @desc:
 **/
public abstract class BaseLogicFilter implements LogicFilter {


    @Override
    public Long filter(String matterValue, List<TreeNodeLink> treeNodeLineInfoList) {
        return null;
    }

    @Override
    public abstract String matterValue(Long treeId, String userId, Map<String, String> decisionMatter);

    private boolean decisionLogic(){

        return false;
    }
}
