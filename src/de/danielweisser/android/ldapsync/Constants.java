/*
 * Copyright 2010 Daniel Weisser
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

package de.danielweisser.android.ldapsync;

import de.danielweisser.android.ldapsync.model.Contact;

/**
 * Constants for the LDAP sync adapter.
 * 
 * @author <a href="mailto:daniel.weisser@gmx.de">Daniel Weisser</a>
 */
public class Constants {

	/**
	 * Account type string.
	 */
	public static final String ACCOUNT_TYPE = "de.danielweisser.android.ldapsync";

	/**
	 * Authtoken type string.
	 */
	public static final String AUTHTOKEN_TYPE = "de.danielweisser.android.ldapsync";

	/**
	 * SD card LDAPSync folder.
	 */
	public static final String SDCARD_FOLDER = "/LDAPSync";

	public static final String PARAM_HOST = "host";
	public static final String PARAM_ENCRYPTION = "encryption";
	public static final String PARAM_PORT = "port";
	public static final String PARAM_USERNAME = "username";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_BASEDN = "baseDN";
	public static final String PARAM_SEARCHFILTER = "searchFilter";
	
	public static final String[] LDAP_SERVER_PARAMS = {PARAM_HOST, PARAM_ENCRYPTION, PARAM_PORT, PARAM_USERNAME, PARAM_BASEDN, PARAM_SEARCHFILTER};
	
	
	public static final String PARAM_CONFIRMCREDENTIALS = "confirmCredentials";

	public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";

	public static final String PARAM_MAPPING = "map_";
	
	public static final String MAPPING_FIRSTNAME = PARAM_MAPPING + Contact.FIRSTNAME;
	public static final String MAPPING_LASTNAME = PARAM_MAPPING + Contact.LASTNAME;
	public static final String MAPPING_TELEPHONE = PARAM_MAPPING + Contact.TELEPHONE;
	public static final String MAPPING_MOBILE = PARAM_MAPPING + Contact.MOBILE;
	public static final String MAPPING_HOMEPHONE = PARAM_MAPPING + Contact.HOMEPHONE;
	public static final String MAPPING_MAIL = PARAM_MAPPING + Contact.MAIL;
	public static final String MAPPING_PHOTO = PARAM_MAPPING + Contact.PHOTO;
	public static final String MAPPING_STREET = PARAM_MAPPING + Contact.STREET;
	public static final String MAPPING_CITY = PARAM_MAPPING + Contact.CITY;
	public static final String MAPPING_STATE = PARAM_MAPPING + Contact.STATE;
	public static final String MAPPING_ZIP = PARAM_MAPPING + Contact.ZIP;
	public static final String MAPPING_COUNTRY = PARAM_MAPPING + Contact.COUNTRY;
	
	public static final String[] LDAP_MAPPINGS = { MAPPING_FIRSTNAME, MAPPING_LASTNAME, MAPPING_TELEPHONE, MAPPING_MOBILE, MAPPING_HOMEPHONE, MAPPING_MAIL,
			MAPPING_STREET, MAPPING_CITY, MAPPING_STATE, MAPPING_ZIP, MAPPING_COUNTRY };

}
