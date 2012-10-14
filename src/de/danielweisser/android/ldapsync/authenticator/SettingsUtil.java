package de.danielweisser.android.ldapsync.authenticator;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import de.danielweisser.android.ldapsync.R;

public class SettingsUtil {

	/**
	 * Sets the default LDAP mapping attributes
	 */
	public SettingsData setDefaultLDAPMappings(SettingsData data) {
		if (data.ismRequestNewAccount()) {
			// mSearchFilter = "(objectClass=inetOrgPerson)";
			data.setmSearchFilter("(objectClass=organizationalPerson)");
			data.setmFirstName("givenName");
			data.setmLastName("sn");
			data.setmOfficePhone("telephonenumber");
			data.setmCellPhone("mobile");
			data.setmHomePhone("homephone");
			data.setmEmail("mail");
			data.setmImage("jpegphoto");
			data.setmStreet("street");
			data.setmCity("l");
			data.setmZip("postalCode");
			data.setmState("st");
			data.setmCountry("co");
			// mImage = "thumbnailphoto";
		}
		return data;
	}
	
	public void initEncryptionSpinner(Spinner spinner, Activity activity, final SettingsData data) {
		data.setmEncryptionSpinner(spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.encryption_methods, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		data.getmEncryptionSpinner().setAdapter(adapter);
		data.getmEncryptionSpinner().setSelection(data.getmEncryption());
		data.getmEncryptionSpinner().setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				data.setmEncryption(position);
				if (data.getmPort() == 636 || data.getmPort() == 389)
				{
					if (position == 1) {
						data.getmPortEdit().setText("636");
					} else {
						data.getmPortEdit().setText("389");
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// Do nothing.
			}
		});
	}
	
	public void setLdapMappingValues(SettingsData data, Activity activity) {
		data.setmFirstNameEdit((EditText) activity.findViewById(R.id.firstname_edit));
		data.getmFirstNameEdit().setText(data.getmFirstName());
		
		data.setmLastNameEdit((Spinner) activity.findViewById(R.id.lastname_spinner));
		@SuppressWarnings("unchecked")
		ArrayAdapter<String> adapter = (ArrayAdapter<String>) data.getmLastNameEdit().getAdapter();
		int spinnerPosition = adapter.getPosition(data.getmLastName());
		data.getmLastNameEdit().setSelection(spinnerPosition);
		
		data.setmOfficePhoneEdit((EditText) activity.findViewById(R.id.officephone_edit));
		data.getmOfficePhoneEdit().setText(data.getmOfficePhone());
		data.setmCellPhoneEdit((EditText) activity.findViewById(R.id.cellphone_edit));
		data.getmCellPhoneEdit().setText(data.getmCellPhone());
		data.setmHomePhoneEdit((EditText) activity.findViewById(R.id.homephone_edit));
		data.getmHomePhoneEdit().setText(data.getmHomePhone());
		data.setmEmailEdit((EditText) activity.findViewById(R.id.mail_edit));
		data.getmEmailEdit().setText(data.getmEmail());
		data.setmImageEdit((EditText) activity.findViewById(R.id.image_edit));
		data.getmImageEdit().setText(data.getmImage());
		data.setmStreetEdit((EditText) activity.findViewById(R.id.street_edit));
		data.getmStreetEdit().setText(data.getmStreet());
		data.setmCityEdit((EditText) activity.findViewById(R.id.city_edit));
		data.getmCityEdit().setText(data.getmCity());
		data.setmZipEdit((EditText) activity.findViewById(R.id.zip_edit));
		data.getmZipEdit().setText(data.getmZip());
		data.setmStateEdit((EditText) activity.findViewById(R.id.state_edit));
		data.getmStateEdit().setText(data.getmState());
		data.setmCountryEdit((EditText) activity.findViewById(R.id.country_edit));
		data.getmCountryEdit().setText(data.getmCountry());
	}

	public void setConnectionValues(SettingsData data, boolean setBaseDN) {
		data.getmUsernameEdit().setText(data.getmUsername());
		data.getmPasswordEdit().setText(data.getmPassword());
		data.getmHostEdit().setText(data.getmHost());
		data.getmPortEdit().setText(Integer.toString(data.getmPort()));
		data.getmSearchFilterEdit().setText(data.getmSearchFilter());
		if(setBaseDN)
		{
			data.getmBaseDNSpinner().setText(data.getmBaseDN());
		}
	}

	public void initControls(SettingsData data, Activity activity) {
		// Find controls
		data.setmUsernameEdit((EditText) activity.findViewById(R.id.username_edit));
		data.setmPasswordEdit((EditText) activity.findViewById(R.id.password_edit));
		data.setmHostEdit((EditText) activity.findViewById(R.id.host_edit));
		data.setmPortEdit((EditText) activity.findViewById(R.id.port_edit));
		data.setmSearchFilterEdit((EditText) activity.findViewById(R.id.searchfilter_edit));
		data.setmBaseDNSpinner((AutoCompleteTextView) activity.findViewById(R.id.basedn_spinner));
	}
}

