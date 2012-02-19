package de.danielweisser.android.ldapsync.model;

public class LdapProperty {

	/** Name used in LDAPSync */
	private String guiName;
	
	/** Default value that is initially used (or after a reset for this LdapProperty) */
	private String defaultLdapName;
	
	/** Optional, if the user has entered a different name via gui */
	private String customLdapName;
	
	public LdapProperty(String guiName, String defaultLdapName) {
		this.guiName = guiName;
		this.defaultLdapName = defaultLdapName;
	}
	
	public String getGuiName() {
		return guiName;
	}

	public void setGuiName(String guiName) {
		this.guiName = guiName;
	}

	public String getDefaultLdapName() {
		return defaultLdapName;
	}

	public void setDefaultLdapName(String defaultLdapName) {
		this.defaultLdapName = defaultLdapName;
	}

	public String getCustomLdapName() {
		return customLdapName;
	}

	public void setCustomLdapName(String customLdapName) {
		this.customLdapName = customLdapName;
	}
	
	/**
	 * Returns the active ldapName for this LdapProperty.
	 */
	public String getActiveLdapName()
	{
		return getCustomLdapName() != null ? getCustomLdapName() : getDefaultLdapName();
	}
}
