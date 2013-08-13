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

package de.danielweisser.android.ldapsync.authenticator;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ViewFlipper;
import de.danielweisser.android.ldapsync.Constants;
import de.danielweisser.android.ldapsync.R;
import de.danielweisser.android.ldapsync.administration.AdminUtil;
import de.danielweisser.android.ldapsync.client.LDAPServerInstance;
import de.danielweisser.android.ldapsync.client.LDAPUtilities;
import de.danielweisser.android.ldapsync.model.Contact;
import de.danielweisser.android.ldapsync.platform.ContactManager;

/**
 * Activity which displays login screen to the user.
 * 
 * @author <a href="mailto:daniel.weisser@gmx.de">Daniel Weisser</a>
 */
public class LDAPAuthenticatorActivity extends AccountAuthenticatorActivity {

	private static final int ERROR_DIALOG = 1;
	private static final int PROGRESS_DIALOG = 0;

	private static final String TAG = "LDAPAuthActivity";

	private String message;

	protected SettingsData data = new SettingsData(true, false, new Handler());
	private Dialog dialog;
	
	private SettingsUtil settingsUtil;

	
	public LDAPAuthenticatorActivity()
	{
		settingsUtil = new SettingsUtil();
	}
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		data.setmAccountManager(AccountManager.get(this));

		getDataFromIntent();
		data = settingsUtil.setDefaultLDAPMappings(data);

		setContentView(R.layout.login_activity);

		Spinner spinner = (Spinner) findViewById(R.id.encryption_spinner);
		settingsUtil.initEncryptionSpinner(spinner, this, data);

		settingsUtil.initControls(data, this);

		// Set values from the intent
		settingsUtil.setConnectionValues(data, false);

		// Set values for LDAP mapping
		if(data != null)
		{
			try
			{
				settingsUtil.initLdapMappingGuiElements(data, this);
				settingsUtil.setLdapMappingValues(data);
			}
			catch (IndexOutOfBoundsException ioobe)
			{
				Log.e(TAG, "Element not found, maybe not allowed. Skipping setLdapMappingValues()", ioobe);
			}
		}
	}


	/**
	 * Obtains data from an intent that was provided for the activity. If no intent was provided some default values are set.
	 */
	private void getDataFromIntent() {
		final Intent intent = getIntent();
		data.setmUsername(intent.getStringExtra(Constants.PARAM_USERNAME));
		data.setmPassword(intent.getStringExtra(Constants.PARAM_PASSWORD));
		data.setmHost(intent.getStringExtra(Constants.PARAM_HOST));
		data.setmPort(intent.getIntExtra(Constants.PARAM_PORT, 389));
		data.setmEncryption(intent.getIntExtra(Constants.PARAM_ENCRYPTION, 0));
		data.setmRequestNewAccount((data.getmUsername() == null));
		data.setmConfirmCredentials(intent.getBooleanExtra(Constants.PARAM_CONFIRMCREDENTIALS, false));
	}

	/**
	 * Called when response is received from the server for confirm credentials request. See onAuthenticationResult(). Sets the AccountAuthenticatorResult which
	 * is sent back to the caller.
	 * 
	 * @param the
	 *            confirmCredentials result.
	 */
	protected void finishConfirmCredentials(boolean result) {
		Log.i(TAG, "finishConfirmCredentials()");
		final Account account = new Account(data.getmHost(), Constants.ACCOUNT_TYPE);
		data.getmAccountManager().setPassword(account, data.getmPassword());
		final Intent intent = new Intent();
		intent.putExtra(AccountManager.KEY_BOOLEAN_RESULT, result);
		setAccountAuthenticatorResult(intent.getExtras());
		setResult(RESULT_OK, intent);
		finish();
	}

	/**
	 * Called when response is received from the server for authentication request. See onAuthenticationResult(). Sets the AccountAuthenticatorResult which is
	 * sent back to the caller. Also sets the authToken in AccountManager for this account.
	 */
	protected void finishLogin() {
		Log.i(TAG, "finishLogin()");
		final Account account = new Account(data.getmHost(), Constants.ACCOUNT_TYPE);

		if (data.ismRequestNewAccount()) {
			Bundle userData = new Bundle();
			userData.putString(Constants.PARAM_USERNAME, data.getmUsername());
			userData.putString(Constants.PARAM_PORT, data.getmPort() + "");
			userData.putString(Constants.PARAM_HOST, data.getmHost());
			userData.putString(Constants.PARAM_ENCRYPTION, data.getmEncryption() + "");
			userData.putString(Constants.PARAM_SEARCHFILTER, data.getmSearchFilter());
			userData.putString(Constants.PARAM_BASEDN, data.getmBaseDN());
			// Mappings for LDAP data
			userData.putString(Constants.PARAM_MAPPING + Contact.FIRSTNAME, data.getmFirstName());
			userData.putString(Constants.PARAM_MAPPING + Contact.LASTNAME, data.getmLastName());
			userData.putString(Constants.PARAM_MAPPING + Contact.TELEPHONE, data.getmOfficePhone());
			userData.putString(Constants.PARAM_MAPPING + Contact.MOBILE, data.getmCellPhone());
			userData.putString(Constants.PARAM_MAPPING + Contact.HOMEPHONE, data.getmHomePhone());
			userData.putString(Constants.PARAM_MAPPING + Contact.MAIL, data.getmEmail());
			userData.putString(Constants.PARAM_MAPPING + Contact.PHOTO, data.getmImage());
			userData.putString(Constants.PARAM_MAPPING + Contact.STREET, data.getmStreet());
			userData.putString(Constants.PARAM_MAPPING + Contact.CITY, data.getmCity());
			userData.putString(Constants.PARAM_MAPPING + Contact.ZIP, data.getmZip());
			userData.putString(Constants.PARAM_MAPPING + Contact.STATE, data.getmState());
			userData.putString(Constants.PARAM_MAPPING + Contact.COUNTRY, data.getmCountry());
			data.getmAccountManager().addAccountExplicitly(account, data.getmPassword(), userData);

			// Set contacts sync for this account.
			ContentResolver.setSyncAutomatically(account, ContactsContract.AUTHORITY, true);
			ContactManager.makeGroupVisible(account.name, getContentResolver());
		} else {
			data.getmAccountManager().setPassword(account, data.getmPassword());
		}
		final Intent intent = new Intent();
		data.setmAuthtoken(data.getmPassword());
		intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, account.name);
		intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, Constants.ACCOUNT_TYPE);
		if (data.getmAuthtokenType() != null && data.getmAuthtokenType().equals(Constants.AUTHTOKEN_TYPE)) {
			intent.putExtra(AccountManager.KEY_AUTHTOKEN, data.getmAuthtoken());
		}
		setAccountAuthenticatorResult(intent.getExtras());
		setResult(RESULT_OK, intent);
		finish();
	}

	/**
	 * Handles onClick event on the Next button. Sends username/password to the server for authentication.
	 * 
	 * @param view
	 *            The Next button for which this method is invoked
	 */
	public void getLDAPServerDetails(View view) {
		Log.i(TAG, "handleLogin");
		if (data.ismRequestNewAccount()) {
			data.setmUsername(data.getmUsernameEdit().getText().toString());
		}
		data.setmPassword(data.getmPasswordEdit().getText().toString());
		data.setmHost(data.getmHostEdit().getText().toString());
		try {
			data.setmPort(Integer.parseInt(data.getmPortEdit().getText().toString()));
		} catch (NumberFormatException nfe) {
			Log.i(TAG, "No port given. Set port to 389");
			data.setmPort(389);
		}
		LDAPServerInstance ldapServer = new LDAPServerInstance(data.getmHost(), data.getmPort(), data.getmEncryption(), data.getmUsername(), data.getmPassword());

		showDialog(PROGRESS_DIALOG);
		// Start authenticating...
		data.setmAuthThread(LDAPUtilities.attemptAuth(ldapServer, data.getmHandler(), LDAPAuthenticatorActivity.this));
	}

	/**
	 * Call back for the authentication process. When the authentication attempt is finished this method is called.
	 * 
	 * @param baseDNs
	 *            List of baseDNs from the LDAP server
	 * @param result
	 *            result of the authentication process
	 * @param message
	 *            Possible error message
	 */
	public void onAuthenticationResult(String[] baseDNs, boolean result, String message) {
		Log.i(TAG, "onAuthenticationResult(" + result + ")");
		if (dialog != null) {
			dialog.dismiss();
		}
		if (result) {
			if (baseDNs != null) {
				ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, baseDNs);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				data.getmBaseDNSpinner().setAdapter(adapter);
			}
			ViewFlipper vf = (ViewFlipper) findViewById(R.id.server);
			vf.showNext();
		} else {
			this.message = message;
			showDialog(ERROR_DIALOG);
			Log.e(TAG, "onAuthenticationResult: failed to authenticate");
		}
	}

	/**
	 * Handles onClick event on the Done button. Saves the account with the account manager.
	 * 
	 * @param view
	 *            The Done button for which this method is invoked
	 */
	public void saveAccount(View view) {
		data.setmSearchFilter(data.getmSearchFilterEdit().getText().toString());
		if(data.getmFirstNameEdit()!= null)
		{
			data.setmBaseDN(data.getmBaseDNSpinner().getText().toString());
			data.setmFirstName((String)data.getmFirstNameEdit().getSelectedItem());
			data.setmLastName((String)data.getmLastNameEdit().getSelectedItem());
			data.setmOfficePhone((String)data.getmOfficePhoneEdit().getSelectedItem());
			data.setmCellPhone((String)data.getmCellPhoneEdit().getSelectedItem());
			data.setmHomePhone((String)data.getmHomePhoneEdit().getSelectedItem());
			data.setmEmail((String)data.getmEmailEdit().getSelectedItem());
			data.setmImage((String)data.getmImageEdit().getSelectedItem());
			data.setmStreet((String)data.getmStreetEdit().getSelectedItem());
			data.setmCity((String)data.getmCityEdit().getSelectedItem());
			data.setmZip((String)data.getmZipEdit().getSelectedItem());
			data.setmState((String)data.getmStateEdit().getSelectedItem());
			data.setmCountry((String)data.getmCountryEdit().getSelectedItem());
		}
		
		if (!data.ismConfirmCredentials()) {
			finishLogin();
		} else {
			finishConfirmCredentials(true);
		}
	}
	
	/**
	 * Handles onClick event on the Done button. Saves the account with the account manager.
	 * 
	 * @param view
	 *            The Done button for which this method is invoked
	 */
	public void syncNow(View view) {
		AdminUtil adminUtil = new AdminUtil();
		Account account = adminUtil.getFirstAccount(data.getmAccountManager());
		String authority = "com.android.contacts";
		ContentResolver.requestSync(account, authority, new Bundle());
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == PROGRESS_DIALOG) {
			final ProgressDialog dialog = new ProgressDialog(this);
			dialog.setMessage(getText(R.string.ui_activity_authenticating));
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					Log.i(TAG, "dialog cancel has been invoked");
					if (data.getmAuthThread() != null) {
						data.getmAuthThread().interrupt();
						finish();
					}
				}
			});
			this.dialog = dialog;
			return dialog;
		} else if (id == ERROR_DIALOG) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Connection error").setMessage("Could not connect to the server:\n" + message).setCancelable(false);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create();
			return alert;
		}
		return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		if (id == ERROR_DIALOG) {
			((AlertDialog) dialog).setMessage("Could not connect to the server:\n" + message);
		}
	}
}
