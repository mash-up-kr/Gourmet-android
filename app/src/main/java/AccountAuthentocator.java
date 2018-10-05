//import android.accounts.AbstractAccountAuthenticator;
//import android.accounts.Account;
//import android.accounts.AccountAuthenticatorResponse;
//import android.accounts.AccountManager;
//import android.accounts.NetworkErrorException;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//
//import up.mash.gourmet_mash_up.data.remote.api.GourmetRESTClient;
//import up.mash.gourmet_mash_up.data.remote.model.login.TokenModel;
//
//import static android.accounts.AccountManager.KEY_BOOLEAN_RESULT;
//
//public class AccountAuthentocator extends AbstractAccountAuthenticator{
//
//
//    private static final String TAG = AccountAuthentocator.class.getSimpleName();
//    private final Context context;
//    String clientId;
//    String clientSecret;
//    GourmetRESTClient apiService;
//
//    public AccountAuthentocator(Context context) {
//        super(context);
//        this.context = context;
//    }
//
//    /*
//     * The user has requested to add a new account to the system. We return an intent that will launch our login screen
//     * if the user has not logged in yet, otherwise our activity will just pass the user's credentials on to the account
//     * manager.
//     */
//    @Override
//    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType,
//                             String authTokenType, String[] requiredFeatures, Bundle options) {
//        Log.v("Account Add", "addAccount()");
//        final Intent intent = new Intent(context, AuthenticatorActivity.class);
//        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType);
//        intent.putExtra("Gourmet", authTokenType);
//        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//        final Bundle bundle = new Bundle();
//        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//        return bundle;
//    }
//
//
//    @Override
//    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) {
//        return null;
//    }
//
//    @Override
//    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
//        return null;
//    }
//
//    // See /Applications/android-sdk-macosx/samples/android-18/legacy/SampleSyncAdapter/src/com/example/android/samplesync/authenticator/Authenticator.java
//    // Also take a look here https://github.com/github/android/blob/d6ba3f9fe2d88967f56e9939d8df7547127416df/app/src/main/java/com/github/mobile/accounts/AccountAuthenticator.java
//    @Override
//    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType,
//                               Bundle options) throws NetworkErrorException {
//        Log.d(TAG, "getAuthToken() account="+account.name+ " type="+account.type);
//
//        final Bundle bundle = new Bundle();
//
//        // If the caller requested an authToken type we don't support, then
//        // return an error
//        if (!authTokenType.equals("Gourmet")) {
//            Log.d(TAG, "invalid authTokenType" + authTokenType);
//            bundle.putString(AccountManager.KEY_ERROR_MESSAGE, "invalid authTokenType");
//            return bundle;
//        }
//
//        // Extract the username and password from the Account Manager, and ask
//        // the server for an appropriate AuthToken
//        final AccountManager accountManager = AccountManager.get(context);
//        // Password is storing the refresh token
//        final String password = accountManager.getPassword(account);
//        if (password != null) {
//            Log.i(TAG,"Trying to refresh access token");
//            try {
//                TokenModel accessToken = apiService.refreshAccessToken(password, clientId, clientSecret);
//                if (accessToken!=null && !TextUtils.isEmpty(accessToken.accessToken)) {
//                    bundle.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
//                    bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, ACCOUNT_TYPE);
//                    bundle.putString(AccountManager.KEY_AUTHTOKEN, accessToken.accessToken);
//                    accountManager.setPassword(account, accessToken.refreshToken);
//                    return bundle;
//                }
//            } catch (Exception e) {
//                Timber.e(e, "Failed refreshing token.");
//            }
//        }
//
//        // Otherwise... start the login intent
//        Timber.i("Starting login activity");
//        final Intent intent = new Intent(context, AuthenticatorActivity.class);
//        intent.putExtra(LoginFragment.PARAM_USERNAME, account.name);
//        intent.putExtra(LoginFragment.PARAM_AUTHTOKEN_TYPE, authTokenType);
//        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//        return bundle;
//    }
//
//    @Override
//    public String getAuthTokenLabel(String authTokenType) {
//        return authTokenType.equals(AUTHTOKEN_TYPE) ? authTokenType : null;
//    }
//
//    @Override
//    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features)
//            throws NetworkErrorException {
//        final Bundle result = new Bundle();
//        result.putBoolean(KEY_BOOLEAN_RESULT, false);
//        return result;
//    }
//
//    @Override
//    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType,
//                                    Bundle options) {
//        return null;
//    }
//
//}
