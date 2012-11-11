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
package org.springframework.social.sparklr.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.sparklr.api.Sparklr;
import org.springframework.social.sparklr.api.impl.SparklrTemplate;

/**
 * ExFm ServiceProvider implementation.
 * 
 * @author Michael Lavelle
 */
public class SparklrServiceProvider extends AbstractOAuth2ServiceProvider<Sparklr> {

	private String oauthApiBaseUrl;

	public SparklrServiceProvider(String clientId, String clientSecret,
			String oauthAuthorizeUrl, String oauthTokenUrl,
			String oauthApiBaseUrl) {
		super(new SparklrOAuth2Template(clientId, clientSecret, oauthAuthorizeUrl,
				oauthTokenUrl));
		this.oauthApiBaseUrl = oauthApiBaseUrl;
	}

	@Override
	public Sparklr getApi(String accessToken) {
		return new SparklrTemplate(oauthApiBaseUrl, accessToken);
	}

}
