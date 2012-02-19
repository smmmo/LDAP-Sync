package de.danielweisser.android.ldapsync.administration;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import de.danielweisser.android.ldapsync.Constants;
import de.danielweisser.android.ldapsync.R;
import de.danielweisser.android.ldapsync.authenticator.SettingsData;
import de.danielweisser.android.ldapsync.authenticator.SettingsUtil;

public class EditMappings extends Activity {

	private SettingsUtil settingsUtil;
	private AdminUtil adminUtil;
	protected SettingsData data = new SettingsData(true, false, new Handler());

	
	public EditMappings()
	{
		settingsUtil = new SettingsUtil();
		adminUtil = new AdminUtil();
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		data.setmAccountManager(AccountManager.get(this));
		
		adminUtil.logPersistentValues(data.getmAccountManager(), Constants.LDAP_MAPPINGS);

		data = settingsUtil.setDefaultLDAPMappings(data);
		data = adminUtil.loadPersistentLdapMappings(data);

		setContentView(R.layout.settings_mappings);

		// Set values for LDAP mapping
		settingsUtil.setLdapMappingValues(data, this);
    }
	
	
	public void saveAccount(View view) {
		mapGui2Data(data);

		adminUtil.storeMappings(data.getmAccountManager(), data);
	}

	
	private void mapGui2Data(SettingsData data) {
		
		data.setmFirstName(data.getmFirstNameEdit().getText().toString());
		data.setmLastName(data.getmLastNameEdit().getText().toString());
		data.setmOfficePhone(data.getmOfficePhoneEdit().getText().toString());
		data.setmCellPhone(data.getmCellPhoneEdit().getText().toString());
		data.setmHomePhone(data.getmHomePhoneEdit().getText().toString());
		data.setmEmail(data.getmEmailEdit().getText().toString());
		data.setmStreet(data.getmStreetEdit().getText().toString());
		data.setmCity(data.getmCityEdit().getText().toString());
		data.setmZip(data.getmZipEdit().getText().toString());
		data.setmState(data.getmStateEdit().getText().toString());
		data.setmCountry(data.getmCountryEdit().getText().toString());
	}

}
