package de.danielweisser.android.ldapsync.authenticator;

import android.accounts.AccountManager;
import android.os.Handler;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsData {
	
	/** Was the original caller asking for an entirely new account? */
	private boolean mRequestNewAccount;
	
	/**
	 * If set we are just checking that the user knows their credentials, this doesn't cause the user's password to be changed on the device.
	 */
	private Boolean mConfirmCredentials;
	
	/** for posting authentication attempts back to UI thread */
	private Handler mHandler;
	
	private AccountManager mAccountManager;
	private Thread mAuthThread;
	private String mAuthtoken;
	private String mAuthtokenType;
	private String mPassword;
	private EditText mPasswordEdit;
	private String mUsername;
	private EditText mUsernameEdit;
	private String mHost;
	private EditText mHostEdit;
	private int mEncryption;
	private Spinner mEncryptionSpinner;
	private String mSearchFilter;
	private EditText mSearchFilterEdit;
	private String mBaseDN;
	private AutoCompleteTextView mBaseDNSpinner;
	private int mPort;
	private EditText mPortEdit;
	private String mFirstName;
	private EditText mFirstNameEdit;
	private String mLastName;
	private EditText mLastNameEdit;
	private String mCellPhone;
	private EditText mCellPhoneEdit;
	private String mHomePhone;
	private EditText mHomePhoneEdit;
	private String mOfficePhone;
	private EditText mOfficePhoneEdit;
	private String mEmail;
	private EditText mEmailEdit;
	private String mStreet;
	private EditText mStreetEdit;
	private String mCity;
	private EditText mCityEdit;
	private String mState;
	private EditText mStateEdit;
	private String mZip;
	private EditText mZipEdit;
	private String mCountry;
	private EditText mCountryEdit;
	private String mImage;
	private EditText mImageEdit;

	public SettingsData(boolean mRequestNewAccount, Boolean mConfirmCredentials, Handler mHandler) {
		this.mRequestNewAccount = mRequestNewAccount;
		this.mConfirmCredentials = mConfirmCredentials;
		this.mHandler = mHandler;
	}

	public boolean ismRequestNewAccount() {
		return mRequestNewAccount;
	}

	public void setmRequestNewAccount(boolean mRequestNewAccount) {
		this.mRequestNewAccount = mRequestNewAccount;
	}

	public Boolean ismConfirmCredentials() {
		return mConfirmCredentials;
	}

	public void setmConfirmCredentials(Boolean mConfirmCredentials) {
		this.mConfirmCredentials = mConfirmCredentials;
	}

	public Handler getmHandler() {
		return mHandler;
	}

	public void setmHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}

	public AccountManager getmAccountManager() {
		return mAccountManager;
	}

	public void setmAccountManager(AccountManager mAccountManager) {
		this.mAccountManager = mAccountManager;
	}

	public Thread getmAuthThread() {
		return mAuthThread;
	}

	public void setmAuthThread(Thread mAuthThread) {
		this.mAuthThread = mAuthThread;
	}

	public String getmAuthtoken() {
		return mAuthtoken;
	}

	public void setmAuthtoken(String mAuthtoken) {
		this.mAuthtoken = mAuthtoken;
	}

	public String getmAuthtokenType() {
		return mAuthtokenType;
	}

	public void setmAuthtokenType(String mAuthtokenType) {
		this.mAuthtokenType = mAuthtokenType;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public EditText getmPasswordEdit() {
		return mPasswordEdit;
	}

	public void setmPasswordEdit(EditText mPasswordEdit) {
		this.mPasswordEdit = mPasswordEdit;
	}

	public String getmUsername() {
		return mUsername;
	}

	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}

	public EditText getmUsernameEdit() {
		return mUsernameEdit;
	}

	public void setmUsernameEdit(EditText mUsernameEdit) {
		this.mUsernameEdit = mUsernameEdit;
	}

	public String getmHost() {
		return mHost;
	}

	public void setmHost(String mHost) {
		this.mHost = mHost;
	}

	public EditText getmHostEdit() {
		return mHostEdit;
	}

	public void setmHostEdit(EditText mHostEdit) {
		this.mHostEdit = mHostEdit;
	}

	public int getmEncryption() {
		return mEncryption;
	}

	public void setmEncryption(int mEncryption) {
		this.mEncryption = mEncryption;
	}

	public Spinner getmEncryptionSpinner() {
		return mEncryptionSpinner;
	}

	public void setmEncryptionSpinner(Spinner mEncryptionSpinner) {
		this.mEncryptionSpinner = mEncryptionSpinner;
	}

	public String getmSearchFilter() {
		return mSearchFilter;
	}

	public void setmSearchFilter(String mSearchFilter) {
		this.mSearchFilter = mSearchFilter;
	}

	public EditText getmSearchFilterEdit() {
		return mSearchFilterEdit;
	}

	public void setmSearchFilterEdit(EditText mSearchFilterEdit) {
		this.mSearchFilterEdit = mSearchFilterEdit;
	}

	public String getmBaseDN() {
		return mBaseDN;
	}

	public void setmBaseDN(String mBaseDN) {
		this.mBaseDN = mBaseDN;
	}

	public AutoCompleteTextView getmBaseDNSpinner() {
		return mBaseDNSpinner;
	}

	public void setmBaseDNSpinner(AutoCompleteTextView mBaseDNSpinner) {
		this.mBaseDNSpinner = mBaseDNSpinner;
	}

	public int getmPort() {
		return mPort;
	}

	public void setmPort(int mPort) {
		this.mPort = mPort;
	}

	public EditText getmPortEdit() {
		return mPortEdit;
	}

	public void setmPortEdit(EditText mPortEdit) {
		this.mPortEdit = mPortEdit;
	}

	public String getmFirstName() {
		return mFirstName;
	}

	public void setmFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}

	public EditText getmFirstNameEdit() {
		return mFirstNameEdit;
	}

	public void setmFirstNameEdit(EditText mFirstNameEdit) {
		this.mFirstNameEdit = mFirstNameEdit;
	}

	public String getmLastName() {
		return mLastName;
	}

	public void setmLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	public EditText getmLastNameEdit() {
		return mLastNameEdit;
	}

	public void setmLastNameEdit(EditText mLastNameEdit) {
		this.mLastNameEdit = mLastNameEdit;
	}

	public String getmCellPhone() {
		return mCellPhone;
	}

	public void setmCellPhone(String mCellPhone) {
		this.mCellPhone = mCellPhone;
	}

	public EditText getmCellPhoneEdit() {
		return mCellPhoneEdit;
	}

	public void setmCellPhoneEdit(EditText mCellPhoneEdit) {
		this.mCellPhoneEdit = mCellPhoneEdit;
	}

	public String getmHomePhone() {
		return mHomePhone;
	}

	public void setmHomePhone(String mHomePhone) {
		this.mHomePhone = mHomePhone;
	}

	public EditText getmHomePhoneEdit() {
		return mHomePhoneEdit;
	}

	public void setmHomePhoneEdit(EditText mHomePhoneEdit) {
		this.mHomePhoneEdit = mHomePhoneEdit;
	}

	public String getmOfficePhone() {
		return mOfficePhone;
	}

	public void setmOfficePhone(String mOfficePhone) {
		this.mOfficePhone = mOfficePhone;
	}

	public EditText getmOfficePhoneEdit() {
		return mOfficePhoneEdit;
	}

	public void setmOfficePhoneEdit(EditText mOfficePhoneEdit) {
		this.mOfficePhoneEdit = mOfficePhoneEdit;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public EditText getmEmailEdit() {
		return mEmailEdit;
	}

	public void setmEmailEdit(EditText mEmailEdit) {
		this.mEmailEdit = mEmailEdit;
	}

	public String getmStreet() {
		return mStreet;
	}

	public void setmStreet(String mStreet) {
		this.mStreet = mStreet;
	}

	public EditText getmStreetEdit() {
		return mStreetEdit;
	}

	public void setmStreetEdit(EditText mStreetEdit) {
		this.mStreetEdit = mStreetEdit;
	}

	public String getmCity() {
		return mCity;
	}

	public void setmCity(String mCity) {
		this.mCity = mCity;
	}

	public EditText getmCityEdit() {
		return mCityEdit;
	}

	public void setmCityEdit(EditText mCityEdit) {
		this.mCityEdit = mCityEdit;
	}

	public String getmState() {
		return mState;
	}

	public void setmState(String mState) {
		this.mState = mState;
	}

	public EditText getmStateEdit() {
		return mStateEdit;
	}

	public void setmStateEdit(EditText mStateEdit) {
		this.mStateEdit = mStateEdit;
	}

	public String getmZip() {
		return mZip;
	}

	public void setmZip(String mZip) {
		this.mZip = mZip;
	}

	public EditText getmZipEdit() {
		return mZipEdit;
	}

	public void setmZipEdit(EditText mZipEdit) {
		this.mZipEdit = mZipEdit;
	}

	public String getmCountry() {
		return mCountry;
	}

	public void setmCountry(String mCountry) {
		this.mCountry = mCountry;
	}

	public EditText getmCountryEdit() {
		return mCountryEdit;
	}

	public void setmCountryEdit(EditText mCountryEdit) {
		this.mCountryEdit = mCountryEdit;
	}

	public String getmImage() {
		return mImage;
	}

	public void setmImage(String mImage) {
		this.mImage = mImage;
	}

	public EditText getmImageEdit() {
		return mImageEdit;
	}

	public void setmImageEdit(EditText mImageEdit) {
		this.mImageEdit = mImageEdit;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getmHost()).append("\n");
		sb.append(getmAuthtoken()).append("\n");
		sb.append(getmPort()).append("\n");
		sb.append(getmPassword()).append("\n");
		return sb.toString();		
	}
}