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
	
	public static final String TAG = "SettingsUtil";

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
	
	public void initLdapMappingGuiElements(SettingsData data, Activity activity) {
		data.setmFirstNameEdit((Spinner) activity.findViewById(R.id.firstname_spinner));
		data.setmLastNameEdit((Spinner) activity.findViewById(R.id.lastname_spinner));
		data.setmOfficePhoneEdit((Spinner) activity.findViewById(R.id.officephone_spinner));
		data.setmCellPhoneEdit((Spinner) activity.findViewById(R.id.cellphone_spinner));
		data.setmHomePhoneEdit((Spinner) activity.findViewById(R.id.homephone_spinner));
		data.setmEmailEdit((Spinner) activity.findViewById(R.id.mail_spinner));
		data.setmImageEdit((Spinner) activity.findViewById(R.id.image_spinner));
		data.setmStreetEdit((Spinner) activity.findViewById(R.id.street_spinner));
		data.setmCityEdit((Spinner) activity.findViewById(R.id.city_spinner));
		data.setmZipEdit((Spinner) activity.findViewById(R.id.zip_spinner));
		data.setmStateEdit((Spinner) activity.findViewById(R.id.state_spinner));
		data.setmCountryEdit((Spinner) activity.findViewById(R.id.country_spinner));
	}
	
	public void setLdapMappingValues(SettingsData data) {
        data.getmFirstNameEdit().setSelection(getSpinnerPos(data.getmFirstNameEdit(), data.getmFirstName()));
        data.getmLastNameEdit().setSelection(getSpinnerPos(data.getmLastNameEdit(), data.getmLastName()));
		data.getmOfficePhoneEdit().setSelection(getSpinnerPos(data.getmOfficePhoneEdit(), data.getmOfficePhone()));
		data.getmCellPhoneEdit().setSelection(getSpinnerPos(data.getmCellPhoneEdit(), data.getmCellPhone()));
		data.getmHomePhoneEdit().setSelection(getSpinnerPos(data.getmHomePhoneEdit(), data.getmHomePhone()));
		data.getmEmailEdit().setSelection(getSpinnerPos(data.getmEmailEdit(), data.getmEmail()));
		data.getmImageEdit().setSelection(getSpinnerPos(data.getmImageEdit(), data.getmImage()));
		data.getmStreetEdit().setSelection(getSpinnerPos(data.getmStreetEdit(), data.getmStreet()));
		data.getmCityEdit().setSelection(getSpinnerPos(data.getmCityEdit(), data.getmCity()));
		data.getmZipEdit().setSelection(getSpinnerPos(data.getmZipEdit(), data.getmZip()));
		data.getmStateEdit().setSelection(getSpinnerPos(data.getmStateEdit(), data.getmState()));
		data.getmCountryEdit().setSelection(getSpinnerPos(data.getmCountryEdit(), data.getmCountry()));
	}
	
	private int getSpinnerPos(Spinner spinner, String valuetoSearch)
	{
		int spinnerPosition = -1;
		ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
		if(adapter != null)
		{
			spinnerPosition = adapter.getPosition(valuetoSearch);
		}
		return spinnerPosition;
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

