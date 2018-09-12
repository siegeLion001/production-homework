package com.lhh.modules.cnjy21.service;

import com.lhh.modules.cnjy21.constant.APIConstant;
import com.lhh.modules.cnjy21.model.paper.Paper;
import com.lhh.modules.cnjy21.model.paper.PaperView;
import com.lhh.modules.cnjy21.model.paper.Type;
import com.lhh.modules.cnjy21.util.APIRequest;
import com.lhh.modules.cnjy21.util.APIResult;

/**
 * API Common 服务
 *
 * @author zhoushubing
 */
public class APIPaperService {

    protected APIRequest apiRequest = APIRequest.getInstance();

    /**
     * 获取试卷列表
     */
    public APIResult<Paper> getPaperList(APIRequestParams params) {
        return apiRequest.request(APIConstant.URL_PAPERS, Paper.class, params);
    }


    /**
     * 获取试卷类型
     */
    public APIResult<Type> getPaperTypes(APIRequestParams params) {
        return apiRequest.request(APIConstant.URL_PAPER_TYPES, Type.class, params);
    }

    /**
     * 获取试卷类型
     */
    public APIResult<PaperView> getPaperView(APIRequestParams params) {
        return apiRequest.request(APIConstant.URL_PAPER_VIEW, PaperView.class, params);
    }

}
