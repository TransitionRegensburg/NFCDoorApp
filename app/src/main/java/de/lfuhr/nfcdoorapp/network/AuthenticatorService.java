package de.lfuhr.nfcdoorapp.network;

import android.accounts.AbstractAccountAuthenticator;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import de.lfuhr.nfcdoorapp.network.MyAccountAuthenticator;

public class AuthenticatorService extends Service {
    // Instance field that stores the authenticator object
    private AbstractAccountAuthenticator mAuthenticator;
    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new MyAccountAuthenticator(this);
    }
    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
