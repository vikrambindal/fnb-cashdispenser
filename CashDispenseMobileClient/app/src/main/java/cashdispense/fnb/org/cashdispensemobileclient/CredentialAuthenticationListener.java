package cashdispense.fnb.org.cashdispensemobileclient;

/**
 * Created by Vikram.Bindal on 2017/08/29.
 */

public interface CredentialAuthenticationListener {

    void onInvalidCredentials();

    void onValidCredentials();
}
