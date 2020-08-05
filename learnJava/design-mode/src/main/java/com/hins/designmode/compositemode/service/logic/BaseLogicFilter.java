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

        for(TreeNodeLink treeNodeLink : treeNodeLineInfoList){
            if (decisionLogic(matterValue, treeNodeLink)){
                return treeNodeLink.getToNodeId();
            }
        }
        return null;
    }

    @Override
    public abstract String matterValue(Long treeId, String userId, Map<String, String> decisionMatter);

    private boolean decisionLogic(String matterValue, TreeNodeLink nodeLink){

        switch (nodeLink.getRuleLimitTypeEnum()) {
            case EQUAL:
                return matterValue.equals(nodeLink.getRuleLimitValue());
            case GREATER:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLink.getRuleLimitValue());
            case LESS:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLink.getRuleLimitValue());
            case GREATER_EQUAL:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLink.getRuleLimitValue());
            case LESS_EQUAL:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLink.getRuleLimitValue());
            default:
                return false;
        }

    }
}
