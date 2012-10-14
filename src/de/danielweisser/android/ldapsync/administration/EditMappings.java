package de.danielweisser.android.ldapsync.administration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.unboundid.ldap.sdk.schema.AttributeTypeDefinition;

import de.danielweisser.android.ldapsync.Constants;
import de.danielweisser.android.ldapsync.R;
import de.danielweisser.android.ldapsync.authenticator.SettingsData;
import de.danielweisser.android.ldapsync.authenticator.SettingsUtil;
import de.danielweisser.android.ldapsync.client.LDAPServerInstance;
import de.danielweisser.android.ldapsync.client.LDAPUtilities;

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

		data = adminUtil.loadPersistentConnectionData(data);
		data = settingsUtil.setDefaultLDAPMappings(data);
		data = adminUtil.loadPersistentLdapMappings(data);

		setContentView(R.layout.settings_mappings);

		// Set values for LDAP mapping
		initSpinners();
		settingsUtil.setLdapMappingValues(data, this);
    }
	
	
	private void initSpinners() {
		LDAPServerInstance ldapServer = new LDAPServerInstance(data.getmHost(), data.getmPort(), data.getmEncryption(), data.getmUsername(), data.getmPassword());
		List<AttributeTypeDefinition> attributesFromLdap = LDAPUtilities.getAttributesFromLdap(ldapServer);
		
		List<String> attributeNames = new ArrayList<String>();
		for (AttributeTypeDefinition attr : attributesFromLdap) {
			attributeNames.add(attr.getNameOrOID());
		}
		
		Collections.sort(attributeNames);

		Spinner spinner = (Spinner) findViewById(R.id.lastname_spinner);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, attributeNames);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
	}

	public void saveAccount(View view) {
		mapGui2Data(data);

		adminUtil.storeMappings(data.getmAccountManager(), data);
	}

	
	private void mapGui2Data(SettingsData data) {
		
		data.setmFirstName(data.getmFirstNameEdit().getText().toString());
		data.setmLastName((String)data.getmLastNameEdit().getSelectedItem());
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
