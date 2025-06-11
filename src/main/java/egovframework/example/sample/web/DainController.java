/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.example.sample.service.*;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DainController {
    private final DainService dainService;
    @GetMapping("/sample.do")
    public @ResponseBody List<Map<String, String>> selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", "value");
        List<Map<String, String>> li = new ArrayList<>();
        li.add(map);
        return li;
    }

    @GetMapping("/smp.do")
    public @ResponseBody Loc selectSampleList() throws Exception {
        return dainService.selectLoc("S-001");
    }
}
