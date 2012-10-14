package de.danielweisser.android.ldapsync.administration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
	
	public static final String TAG = "EditMappings";

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
		settingsUtil.initLdapMappingGuiElements(data, this);
		initSpinners();
		try
		{
			settingsUtil.setLdapMappingValues(data);
		}
		catch (IndexOutOfBoundsException ioobe)
		{
			Log.e(TAG, "Element not found, maybe not allowed. Skipping setLdapMappingValues()", ioobe);
		}
    }
	
	
	private void initSpinners() {
		LDAPServerInstance ldapServer = new LDAPServerInstance(data.getmHost(), data.getmPort(), data.getmEncryption(), data.getmUsername(), data.getmPassword());
		List<AttributeTypeDefinition> attributesFromLdap = LDAPUtilities.getAttributesFromLdap(ldapServer);
		
		List<String> attributeNames = new ArrayList<String>();
		for (AttributeTypeDefinition attr : attributesFromLdap) {
			attributeNames.add(attr.getNameOrOID());
		}
		
		Collections.sort(attributeNames);

		createSpinner(data.getmFirstNameEdit(), attributeNames);
		createSpinner(data.getmLastNameEdit(), attributeNames);
		createSpinner(data.getmOfficePhoneEdit(), attributeNames);
		createSpinner(data.getmCellPhoneEdit(), attributeNames);
		createSpinner(data.getmHomePhoneEdit(), attributeNames);
		createSpinner(data.getmEmailEdit(), attributeNames);
		createSpinner(data.getmStreetEdit(), attributeNames);
		createSpinner(data.getmCityEdit(), attributeNames);
		createSpinner(data.getmZipEdit(), attributeNames);
		createSpinner(data.getmStateEdit(), attributeNames);
		createSpinner(data.getmCountryEdit(), attributeNames);
	}

	protected void createSpinner(Spinner spinner, List<String> attributeNames) {
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
		
		data.setmFirstName((String)data.getmFirstNameEdit().getSelectedItem());
		data.setmLastName((String)data.getmLastNameEdit().getSelectedItem());
		data.setmOfficePhone((String)data.getmOfficePhoneEdit().getSelectedItem());
		data.setmCellPhone((String)data.getmCellPhoneEdit().getSelectedItem());
		data.setmHomePhone((String)data.getmHomePhoneEdit().getSelectedItem());
		data.setmEmail((String)data.getmEmailEdit().getSelectedItem());
		data.setmStreet((String)data.getmStreetEdit().getSelectedItem());
		data.setmCity((String)data.getmCityEdit().getSelectedItem());
		data.setmZip((String)data.getmZipEdit().getSelectedItem());
		data.setmState((String)data.getmStateEdit().getSelectedItem());
		data.setmCountry((String)data.getmCountryEdit().getSelectedItem());
	}

}
