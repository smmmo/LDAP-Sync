package de.danielweisser.android.ldapsync.administration;

import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Spinner;
import de.danielweisser.android.ldapsync.Constants;
import de.danielweisser.android.ldapsync.R;
import de.danielweisser.android.ldapsync.authenticator.SettingsData;
import de.danielweisser.android.ldapsync.authenticator.SettingsUtil;
import de.danielweisser.android.ldapsync.client.LDAPServerInstance;
import de.danielweisser.android.ldapsync.client.LDAPUtilities;

public class EditBasics extends Activity {

	private SettingsUtil settingsUtil;
	private AdminUtil adminUtil;
	protected SettingsData data = new SettingsData(true, false, new Handler());

	
	public EditBasics()
	{
		settingsUtil = new SettingsUtil();
		adminUtil = new AdminUtil();
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		data.setmAccountManager(AccountManager.get(this));
		
		adminUtil.logPersistentValues(data.getmAccountManager(), Constants.LDAP_SERVER_PARAMS);
		
		data = adminUtil.loadPersistentConnectionData(data);
		data = settingsUtil.setDefaultLDAPMappings(data);

		setContentView(R.layout.settings_basic_ldap);

		Spinner spinner = (Spinner) findViewById(R.id.encryption_spinner);
		settingsUtil.initEncryptionSpinner(spinner, this, data);

		settingsUtil.initControls(data, this);

		// Set loaded persistent values
		settingsUtil.setConnectionValues(data, true);
    }
	
	public void saveAccount(View view) {
		mapGui2Data(data);

		adminUtil.storeLdapProperties(data.getmAccountManager(), data);
		
		testLdapSetting();
	}
	
	private void mapGui2Data(SettingsData data) {
		// Hostname
		data.setmHost(data.getmHostEdit().getText().toString());

		// Encryption
		data.setmEncryption(data.getmEncryption());

		Integer portAsInteger = Integer.parseInt(data.getmPortEdit().getText().toString());
		data.setmPort(portAsInteger);

		// Bind DN (might be empty)
		data.setmUsername(data.getmUsernameEdit().getText().toString());

		// Bind PW (might be empty)
		data.setmPassword(data.getmPasswordEdit().getText().toString());

		// Base DN
		data.setmBaseDN(data.getmBaseDNSpinner().getText().toString());

		// Search Filter
		data.setmSearchFilter(data.getmSearchFilterEdit().getText().toString());
	}
	
	private void testLdapSetting()
	{
		LDAPServerInstance ldapServer = new LDAPServerInstance(data.getmHost(), data.getmPort(), data.getmEncryption(), data.getmUsername(), data.getmPassword());
		LDAPUtilities.authenticate(ldapServer, handler, this);

	}
	
	static Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			;
		}
	};
}
