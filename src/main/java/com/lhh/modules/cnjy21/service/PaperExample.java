package com.lhh.modules.cnjy21.service;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.lhh.modules.cnjy21.constant.APIConstant;
import com.lhh.modules.cnjy21.model.common.Book;
import com.lhh.modules.cnjy21.model.common.Chapter;
import com.lhh.modules.cnjy21.model.common.Version;
import com.lhh.modules.cnjy21.model.paper.Paper;
import com.lhh.modules.cnjy21.model.paper.PaperView;
import com.lhh.modules.cnjy21.model.paper.Type;
import com.lhh.modules.cnjy21.util.APIResult;

import java.util.List;

/**
 * 文档资源列表
 *
 * @author zhoushubing
 */
public class PaperExample {

//	public static void main(String[] args) {
//		APIPaperService service = new APIPaperService();
//
//		APIRequestParams apiRequestParams =  new APIRequestParams();
//		apiRequestParams.chapterId=48216;
//		apiRequestParams.stage=1;
//		apiRequestParams.subjectId=6;
////		apiRequestParams.type=4;
//	    APIResult<PaperView> apiRequest = service.getPaperList(apiRequestParams);
//		System.out.println(apiRequest.getData());
//
//	}


    public static void main(String[] args) {
        APIDocumentService documentService = new APIDocumentService();
        List<Version> versions = documentService.getVersions(APIConstant.STAGE_CHUZHONG, 6);
        System.out.println(new Gson().toJson(versions));


        List<Book> books = documentService.getBooks(35524);
        System.out.println(new Gson().toJson(books));
        APIPaperService service = new APIPaperService();

        List<Chapter> chapters = documentService.getChapters(books.get(0).getBookId());
        System.out.println(new Gson().toJson(chapters));


        APIRequestParams apiRequestParams = new APIRequestParams();
        apiRequestParams.chapterId = 76860;
        apiRequestParams.stage = 2;
        apiRequestParams.subjectId = 6;
//		apiRequestParams.type=4;
        APIResult<Paper> apiRequest = service.getPaperList(apiRequestParams);
        System.out.println(JSON.toJSONString(apiRequest));


        APIResult<Type> aa = service.getPaperTypes(apiRequestParams);
        System.out.println(JSON.toJSONString(aa));

        APIRequestParams aaa = new APIRequestParams();
        aaa.id = 1050599;
        APIResult<PaperView> bb = service.getPaperView(aaa);
        System.out.println(JSON.toJSONString(bb));

    }

}
