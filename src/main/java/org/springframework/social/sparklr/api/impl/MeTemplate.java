/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.sparklr.api.impl;

import java.util.List;

import org.springframework.social.sparklr.api.MeOperations;
import org.springframework.social.sparklr.api.Photo;
import org.springframework.social.sparklr.api.SparklrProfile;
import org.springframework.social.sparklr.api.impl.json.PhotoResponse;
import org.springframework.web.client.RestTemplate;

/**
 * @author Michael Lavelle
 */
public class MeTemplate extends AbstractSparklrResourceOperations implements MeOperations {

	public MeTemplate(String oauthApiBaseUrl, RestTemplate restTemplate,
			boolean isAuthorizedForUser) {
		super(oauthApiBaseUrl, restTemplate, isAuthorizedForUser);
	}

	@Override
	public SparklrProfile getUserProfile() {
		requireAuthorization();
	
		return restTemplate.getForObject(getApiResourceUrl("/me"),
					SparklrProfile.class);
		}

	@Override
	protected String getApiResourceBaseUrl() {
		return getApiBaseUrl();
	}

	@Override
	public List<Photo> getPhotos() {

		return restTemplate.getForObject(getApiResourceUrl("/photos?format=json"),
					PhotoResponse.class).getPhotos();
	}
	
	

}
