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
package egovframework.example.sample.service.impl;

import egovframework.example.sample.service.CCTV;
import egovframework.example.sample.service.DainService;
import egovframework.example.sample.service.Loc;
import egovframework.example.sample.service.LocCCTV;
import lombok.RequiredArgsConstructor;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DainServiceImpl implements DainService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DainServiceImpl.class);

	private final DainMapper dainMapper;

	@Override
	public Loc selectLoc(String locId) throws Exception {
		return dainMapper.selectLoc(locId);
	}

	@Override
	public List<CCTV> selectCCTVList(String locId) throws Exception {
		return dainMapper.selectCCTVList(locId);
	}

	@Override
	public LocCCTV getLocWithCCTV(String locId) throws Exception {
		LocCCTV locCCTV = new LocCCTV(dainMapper.selectLoc(locId));
		locCCTV.setCctvList(dainMapper.selectCCTVList(locId));
		return locCCTV;
	}
}
