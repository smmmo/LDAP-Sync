package de.danielweisser.android.ldapsync.administration;

import java.util.HashMap;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.util.Log;
import de.danielweisser.android.ldapsync.Constants;
import de.danielweisser.android.ldapsync.authenticator.SettingsData;

public class AdminUtil {

	public static final String TAG = "AdminUtil";
	
	protected SettingsData loadPersistentConnectionData(SettingsData data) {
		HashMap<String, String> persistentProperties = getPersistentValues(data.getmAccountManager(), Constants.LDAP_SERVER_PARAMS);
		
		data.setmHost(persistentProperties.get(Constants.PARAM_HOST));

		String encryption = (persistentProperties.get(Constants.PARAM_ENCRYPTION) == null ? "1" : persistentProperties.get(Constants.PARAM_ENCRYPTION));
		data.setmEncryption(Integer.valueOf(encryption));

		String port = (persistentProperties.get(Constants.PARAM_PORT) == null ? "389" : persistentProperties.get(Constants.PARAM_PORT));
		Integer portAsInteger = Integer.valueOf(port);
		data.setmPort(portAsInteger);
		
		data.setmUsername(persistentProperties.get(Constants.PARAM_USERNAME));
		data.setmPassword(persistentProperties.get(Constants.PARAM_PASSWORD));
		data.setmBaseDN(persistentProperties.get(Constants.PARAM_BASEDN));
		data.setmSearchFilter(persistentProperties.get(Constants.PARAM_SEARCHFILTER));
		
		data.setmRequestNewAccount((data.getmUsername() == null));
		
		return data;
	}
	
	protected SettingsData loadPersistentLdapMappings(SettingsData data) {
		HashMap<String, String> persistentProperties = getPersistentValues(data.getmAccountManager(), Constants.LDAP_MAPPINGS);

		data.setmFirstName(persistentProperties.get(Constants.MAPPING_FIRSTNAME));
		data.setmLastName(persistentProperties.get(Constants.MAPPING_LASTNAME));
		data.setmOfficePhone(persistentProperties.get(Constants.MAPPING_TELEPHONE));
		data.setmCellPhone(persistentProperties.get(Constants.MAPPING_MOBILE));
		data.setmHomePhone(persistentProperties.get(Constants.MAPPING_HOMEPHONE));
		data.setmEmail(persistentProperties.get(Constants.MAPPING_MAIL));
		data.setmStreet(persistentProperties.get(Constants.MAPPING_STREET));
		data.setmCity(persistentProperties.get(Constants.MAPPING_CITY));
		data.setmZip(persistentProperties.get(Constants.MAPPING_ZIP));
		data.setmState(persistentProperties.get(Constants.MAPPING_STATE));
		data.setmCountry(persistentProperties.get(Constants.MAPPING_COUNTRY));
//		TODO Image
		
		return data;
	}
	

	private HashMap<String, String> getPersistentValues(AccountManager am, String[] userDataValues)
	{
		Account acc = getFirstAccount(am);

		HashMap<String, String> retValues = new HashMap<String, String>();
		for (String searchValue : userDataValues) {
			retValues.put(searchValue, am.getUserData(acc, searchValue));
		}
		
		// For ldap server params also get the password
		if(Constants.LDAP_SERVER_PARAMS.equals(userDataValues))
		{
			retValues.put(Constants.PARAM_PASSWORD, am.getPassword(acc));
		}
		
		return retValues;
	}
	
    private Account getFirstAccount(AccountManager am)
    {
		Account[] accounts = am.getAccountsByType(Constants.ACCOUNT_TYPE);
		if(accounts.length < 1)
		{
			Log.e(TAG, "No account found, please add an LDAPSync account in settings/accounts/Add account first");
			return null;
		}
		else if (accounts.length >1)
		{
			Log.e(TAG, "Only 1 account allowed");
			return null;
		}
		else
		{
			return accounts[0];
		}
    }
	
    protected void storeLdapProperties(AccountManager am, SettingsData dataToStore)
    {
    	resetAllStoredProperties(am);
    	
    	Log.i(TAG, "Store the changes");

    	Account acc = getFirstAccount(am);

    	am.setUserData(acc, Constants.PARAM_HOST, dataToStore.getmHost());
    	am.setUserData(acc, Constants.PARAM_ENCRYPTION, ""+dataToStore.getmEncryption());
    	am.setUserData(acc, Constants.PARAM_PORT, String.valueOf(dataToStore.getmPort()));			
    	am.setUserData(acc, Constants.PARAM_USERNAME, dataToStore.getmUsername());
    	am.setPassword(acc, dataToStore.getmPassword());
    	am.setUserData(acc, Constants.PARAM_BASEDN, dataToStore.getmBaseDN());
    	am.setUserData(acc, Constants.PARAM_SEARCHFILTER, dataToStore.getmSearchFilter());

    	logPersistentValues(am, Constants.LDAP_SERVER_PARAMS);
    }
    
    protected void storeMappings(AccountManager am, SettingsData dataToStore)
    {
//    	resetAllStoredProperties(am);
    	
    	Log.i(TAG, "Store the changes");

    	Account acc = getFirstAccount(am);
 	
		am.setUserData(acc, Constants.MAPPING_FIRSTNAME, dataToStore.getmFirstName());
		am.setUserData(acc, Constants.MAPPING_LASTNAME, dataToStore.getmLastName());
		am.setUserData(acc, Constants.MAPPING_TELEPHONE, dataToStore.getmOfficePhone());
		am.setUserData(acc, Constants.MAPPING_MOBILE, dataToStore.getmCellPhone());
		am.setUserData(acc, Constants.MAPPING_HOMEPHONE, dataToStore.getmHomePhone());
		am.setUserData(acc, Constants.MAPPING_MAIL, dataToStore.getmEmail());
		am.setUserData(acc, Constants.MAPPING_STREET, dataToStore.getmStreet());
		am.setUserData(acc, Constants.MAPPING_CITY, dataToStore.getmCity());
		am.setUserData(acc, Constants.MAPPING_STATE, dataToStore.getmState());
		am.setUserData(acc, Constants.MAPPING_ZIP, dataToStore.getmZip());
		am.setUserData(acc, Constants.MAPPING_COUNTRY, dataToStore.getmCountry());

    	logPersistentValues(am, Constants.LDAP_MAPPINGS);
    }
    
    private void resetAllStoredProperties(AccountManager am)
    {
    	Log.i(TAG, "Reset all properties");

    	Account acc = getFirstAccount(am);
    	
    	am.setUserData(acc, Constants.PARAM_HOST, "");
    	am.setUserData(acc, Constants.PARAM_ENCRYPTION, "");
    	am.setUserData(acc, Constants.PARAM_PORT, "");			
    	am.setUserData(acc, Constants.PARAM_USERNAME, "");
    	am.setUserData(acc, Constants.PARAM_PASSWORD, "");
    	am.setUserData(acc, Constants.PARAM_BASEDN, "");
    	am.setUserData(acc, Constants.PARAM_SEARCHFILTER, "");

    	logPersistentValues(am, Constants.LDAP_SERVER_PARAMS);
    }
    
    protected void logPersistentValues(AccountManager am, String[] userDataValues)
    {
    	Log.i(TAG, "---- Persistent values:");
    	HashMap<String, String> persistentProperties = getPersistentValues(am, userDataValues);
    	
    	for (int i=0; userDataValues.length>i; i++)
    	{
    		String value = persistentProperties.get( userDataValues[i]);
    		Log.i(TAG, userDataValues	[i] +": " +value);
    	}
    }

}
