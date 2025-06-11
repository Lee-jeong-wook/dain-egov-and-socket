package egovframework.example.sample.web;

import java.util.List;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class EgovSampleController {

    /** EgovSampleService */
    private final EgovSampleService sampleService;

    /** EgovPropertyService */
    private final EgovPropertyService propertiesService;

    /** Validator */
    private final DefaultBeanValidator beanValidator;

    /**
     * 글 목록을 조회한다. (pageing)
     * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
     * @param model
     * @return "egovSampleList"
     * @exception Exception
     */
    @GetMapping("/egovSampleList.do")
    public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {

        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing setting */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> sampleList = sampleService.selectSampleList(searchVO);
        model.addAttribute("resultList", sampleList);

        int totCnt = sampleService.selectSampleListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "sample/egovSampleList";
    }

    /**
     * 글 등록 화면을 조회한다.
     * @param searchVO - 목록 조회조건 정보가 담긴 VO
     * @param model
     * @return "egovSampleRegister"
     * @exception Exception
     */
    @GetMapping("/addSample.do")
    public String addSampleView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
        model.addAttribute("sampleVO", new SampleVO());
        return "sample/egovSampleRegister";
    }

    /**
     * 글을 등록한다.
     * @param sampleVO - 등록할 정보가 담긴 VO
     * @param searchVO - 목록 조회조건 정보가 담긴 VO
     * @param status
     * @return "forward:/egovSampleList.do"
     * @exception Exception
     */
    @PostMapping("/addSample.do")
    public String addSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO, BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

        // Server-Side Validation
        beanValidator.validate(sampleVO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("sampleVO", sampleVO);
            return "sample/egovSampleRegister";
        }

        sampleService.insertSample(sampleVO);
        status.setComplete();

        model.addAttribute("searchCondition", sampleVO.getSearchCondition());
        model.addAttribute("searchKeyword", sampleVO.getSearchKeyword());
        model.addAttribute("pageIndex", sampleVO.getPageIndex());

        return "redirect:/egovSampleList.do";
    }

    /**
     * 글 수정화면을 조회한다.
     * @param id - 수정할 글 id
     * @param searchVO - 목록 조회조건 정보가 담긴 VO
     * @param model
     * @return "egovSampleRegister"
     * @exception Exception
     */
    @GetMapping("/updateSampleView.do")
    public String updateSampleView(@RequestParam("selectedId") String id, @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
        SampleVO sampleVO = new SampleVO();
        sampleVO.setId(id);
        // 변수명은 CoC 에 따라 sampleVO
        model.addAttribute(selectSample(sampleVO, searchVO));
        return "sample/egovSampleRegister";
    }

    public SampleVO selectSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
        return sampleService.selectSample(sampleVO);
    }

    /**
     * 글을 수정한다.
     * @param sampleVO - 수정할 정보가 담긴 VO
     * @param searchVO - 목록 조회조건 정보가 담긴 VO
     * @param status
     * @return "forward:/egovSampleList.do"
     * @exception Exception
     */
    @PostMapping("/updateSample.do")
    public String updateSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO, BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

        beanValidator.validate(sampleVO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("sampleVO", sampleVO);
            return "sample/egovSampleRegister";
        }

        sampleService.updateSample(sampleVO);
        status.setComplete();

        model.addAttribute("searchCondition", sampleVO.getSearchCondition());
        model.addAttribute("searchKeyword", sampleVO.getSearchKeyword());
        model.addAttribute("pageIndex", sampleVO.getPageIndex());

        return "redirect:/egovSampleList.do";
    }

    /**
     * 글을 삭제한다.
     * @param sampleVO - 삭제할 정보가 담긴 VO
     * @param searchVO - 목록 조회조건 정보가 담긴 VO
     * @param status
     * @return "forward:/egovSampleList.do"
     * @exception Exception
     */
    @PostMapping("/deleteSample.do")
    public String deleteSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model, SessionStatus status) throws Exception {
        sampleService.deleteSample(sampleVO);
        status.setComplete();

        model.addAttribute("searchCondition", sampleVO.getSearchCondition());
        model.addAttribute("searchKeyword", sampleVO.getSearchKeyword());
        model.addAttribute("pageIndex", sampleVO.getPageIndex());

        return "redirect:/egovSampleList.do";
    }

}