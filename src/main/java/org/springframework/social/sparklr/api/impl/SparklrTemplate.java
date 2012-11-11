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

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.sparklr.api.MeOperations;
import org.springframework.social.sparklr.api.Sparklr;
import org.springframework.social.support.ClientHttpRequestFactorySelector;

/**
 * @author Michael Lavelle
 */
public class SparklrTemplate extends AbstractOAuth2ApiBinding implements Sparklr {


	private MeOperations meOperations;
	
	private ObjectMapper objectMapper;

	/**
	 * Create a new instance of ExFmTemplate. This constructor creates a new
	 * ExFmTemplate able to perform unauthenticated operations against ExFm's
	 * API. Some operations do not require OAuth authentication. For example,
	 * retrieving a specified user's profile or loved tracks does not require
	 * authentication . An ExFmTemplate created with this constructor will
	 * support those operations. Those operations requiring authentication will
	 * throw {@link NotAuthorizedException}.
	 */
	public SparklrTemplate(String apiBaseUrl) {
		initialize(apiBaseUrl, null);
	}

	/**
	 * Create a new instance of SparklrTemplate. This constructor creates the
	 * SparklrTemplate using a given access token
	 * 
	 * @param oauthApiBaseUrl An OAuth API Base url for Sparklr
	 * @param accessToken
	 *            An access token given by SparklrTemplate after a successful OAuth
	 *            2 authentication
	 */
	public SparklrTemplate(String oauthApiBaseUrl, String accessToken) {
		super(accessToken);
		initialize(oauthApiBaseUrl, accessToken);
	}

	
	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super
				.getMessageConverters();
		messageConverters.add(new ByteArrayHttpMessageConverter());
		return messageConverters;
	}

	private void initSubApis(String oauthApiBaseUrl, String accessToken) {
	
		meOperations = new MeTemplate(oauthApiBaseUrl, getRestTemplate(),
				isAuthorized());

	}


	// private helpers
	private void initialize(String apiBaseUrl, String accessToken) {
		// Wrap the request factory with a BufferingClientHttpRequestFactory so
		// that the error handler can do repeat reads on the response.getBody()

		super.setRequestFactory(ClientHttpRequestFactorySelector
				.bufferRequests(getRestTemplate().getRequestFactory()));
		initSubApis(apiBaseUrl, accessToken);

	}

	


	@Override
	public MeOperations meOperations() {
		return meOperations;
	}
	
	

}
