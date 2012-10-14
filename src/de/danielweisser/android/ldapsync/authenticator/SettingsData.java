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
	
	// basic data
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
	
	// Mappings
	private String mFirstName;
	private Spinner mFirstNameEdit;
	private String mLastName;
	private Spinner mLastNameEdit;
	private String mCellPhone;
	private Spinner mCellPhoneEdit;
	private String mHomePhone;
	private Spinner mHomePhoneEdit;
	private String mOfficePhone;
	private Spinner mOfficePhoneEdit;
	private String mEmail;
	private Spinner mEmailEdit;
	private String mStreet;
	private Spinner mStreetEdit;
	private String mCity;
	private Spinner mCityEdit;
	private String mState;
	private Spinner mStateEdit;
	private String mZip;
	private Spinner mZipEdit;
	private String mCountry;
	private Spinner mCountryEdit;
	private String mImage;
	private Spinner mImageEdit;

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

	public Spinner getmFirstNameEdit() {
		return mFirstNameEdit;
	}

	public void setmFirstNameEdit(Spinner mFirstNameEdit) {
		this.mFirstNameEdit = mFirstNameEdit;
	}

	public String getmLastName() {
		return mLastName;
	}

	public void setmLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	public Spinner getmLastNameEdit() {
		return mLastNameEdit;
	}

	public void setmLastNameEdit(Spinner mLastNameEdit) {
		this.mLastNameEdit = mLastNameEdit;
	}

	public String getmCellPhone() {
		return mCellPhone;
	}

	public void setmCellPhone(String mCellPhone) {
		this.mCellPhone = mCellPhone;
	}

	public Spinner getmCellPhoneEdit() {
		return mCellPhoneEdit;
	}

	public void setmCellPhoneEdit(Spinner mCellPhoneEdit) {
		this.mCellPhoneEdit = mCellPhoneEdit;
	}

	public String getmHomePhone() {
		return mHomePhone;
	}

	public void setmHomePhone(String mHomePhone) {
		this.mHomePhone = mHomePhone;
	}

	public Spinner getmHomePhoneEdit() {
		return mHomePhoneEdit;
	}

	public void setmHomePhoneEdit(Spinner mHomePhoneEdit) {
		this.mHomePhoneEdit = mHomePhoneEdit;
	}

	public String getmOfficePhone() {
		return mOfficePhone;
	}

	public void setmOfficePhone(String mOfficePhone) {
		this.mOfficePhone = mOfficePhone;
	}

	public Spinner getmOfficePhoneEdit() {
		return mOfficePhoneEdit;
	}

	public void setmOfficePhoneEdit(Spinner mOfficePhoneEdit) {
		this.mOfficePhoneEdit = mOfficePhoneEdit;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public Spinner getmEmailEdit() {
		return mEmailEdit;
	}

	public void setmEmailEdit(Spinner mEmailEdit) {
		this.mEmailEdit = mEmailEdit;
	}

	public String getmStreet() {
		return mStreet;
	}

	public void setmStreet(String mStreet) {
		this.mStreet = mStreet;
	}

	public Spinner getmStreetEdit() {
		return mStreetEdit;
	}

	public void setmStreetEdit(Spinner mStreetEdit) {
		this.mStreetEdit = mStreetEdit;
	}

	public String getmCity() {
		return mCity;
	}

	public void setmCity(String mCity) {
		this.mCity = mCity;
	}

	public Spinner getmCityEdit() {
		return mCityEdit;
	}

	public void setmCityEdit(Spinner mCityEdit) {
		this.mCityEdit = mCityEdit;
	}

	public String getmState() {
		return mState;
	}

	public void setmState(String mState) {
		this.mState = mState;
	}

	public Spinner getmStateEdit() {
		return mStateEdit;
	}

	public void setmStateEdit(Spinner mStateEdit) {
		this.mStateEdit = mStateEdit;
	}

	public String getmZip() {
		return mZip;
	}

	public void setmZip(String mZip) {
		this.mZip = mZip;
	}

	public Spinner getmZipEdit() {
		return mZipEdit;
	}

	public void setmZipEdit(Spinner mZipEdit) {
		this.mZipEdit = mZipEdit;
	}

	public String getmCountry() {
		return mCountry;
	}

	public void setmCountry(String mCountry) {
		this.mCountry = mCountry;
	}

	public Spinner getmCountryEdit() {
		return mCountryEdit;
	}

	public void setmCountryEdit(Spinner mCountryEdit) {
		this.mCountryEdit = mCountryEdit;
	}

	public String getmImage() {
		return mImage;
	}

	public void setmImage(String mImage) {
		this.mImage = mImage;
	}

	public Spinner getmImageEdit() {
		return mImageEdit;
	}

	public void setmImageEdit(Spinner mImageEdit) {
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