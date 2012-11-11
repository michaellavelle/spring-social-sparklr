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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.sparklr.api.MeOperations;
import org.springframework.social.sparklr.api.Sparklr;
import org.springframework.social.sparklr.api.SparklrProfile;

/**
 * @author Michael Lavelle
 */
public class SparklrAdapterTest {

	private SparklrAdapter apiAdapter = new SparklrAdapter();

	private Sparklr sparklr = Mockito.mock(Sparklr.class);

	@Test
	public void fetchProfile() {
		MeOperations meOperations = Mockito.mock(MeOperations.class);
		Mockito.when(sparklr.meOperations()).thenReturn(meOperations);
		Mockito.when(meOperations.getUserProfile()).thenReturn(
				createSparklrProfile());

		UserProfile profile = apiAdapter.fetchUserProfile(sparklr);
		assertEquals("Matt Slip", profile.getName());
		assertEquals("Matt", profile.getFirstName());
		assertEquals("Slip", profile.getLastName());
		assertNull(profile.getEmail());
		assertEquals("mattslip", profile.getUsername());
	}

	private SparklrProfile createSparklrProfile() {
		SparklrProfile exFmProfile = new SparklrProfile();
		exFmProfile.setUsername("mattslip");
		exFmProfile.setName("Matt Slip");

		//exFmProfile.setImage(image);

		return exFmProfile;
	}

	@Test
	public void setConnectionValues() {
		MeOperations meOperations = Mockito.mock(MeOperations.class);

		Mockito.when(sparklr.meOperations()).thenReturn(meOperations);
		Mockito.when(meOperations.getUserProfile()).thenReturn(
				createSparklrProfile());

		TestConnectionValues connectionValues = new TestConnectionValues();
		apiAdapter.setConnectionValues(sparklr, connectionValues);
		assertEquals("Matt Slip", connectionValues.getDisplayName());

		assertNull(connectionValues.getProfileUrl());
		assertEquals("mattslip", connectionValues.getProviderUserId());
	}

	private static class TestConnectionValues implements ConnectionValues {

		private String displayName;
		private String imageUrl;
		private String profileUrl;
		private String providerUserId;

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getProfileUrl() {
			return profileUrl;
		}

		public void setProfileUrl(String profileUrl) {
			this.profileUrl = profileUrl;
		}

		public String getProviderUserId() {
			return providerUserId;
		}

		public void setProviderUserId(String providerUserId) {
			this.providerUserId = providerUserId;
		}

	}
}
