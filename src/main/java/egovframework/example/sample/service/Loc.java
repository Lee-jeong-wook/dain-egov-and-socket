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
package egovframework.example.sample.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Loc{
	Loc(String locID){
		this.locID = locID;
	}
	private String locID;

	private String locName;

	private String locTrty;

	private float locLat;

	private float locLng;

	public String getLocID() {
		return locID;
	}

	public void setLocID(String locID) {
		this.locID = locID;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getLocTrty() {
		return locTrty;
	}

	public void setLocTrty(String locTrty) {
		this.locTrty = locTrty;
	}

	public float getLocLat() {
		return locLat;
	}

	public void setLocLat(float locLat) {
		this.locLat = locLat;
	}

	public float getLocLng() {
		return locLng;
	}

	public void setLocLng(float locLng) {
		this.locLng = locLng;
	}
}
