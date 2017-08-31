package cashdispense.fnb.org.validation;

import android.app.Activity;
import android.text.TextUtils;

import cashdispense.fnb.org.cashdispensemobileclient.R;

public class Validation {

    private Activity activity;

    public Validation(Activity activity) {
        this.activity = activity;
    }

    public String validateUsername(String username) {
        String error = null;
        if (TextUtils.isEmpty(username)) {
            error = activity.getString(R.string.error_field_required);
        }

        return error;
    }

    public String validatePassword(String password) {
        String error = null;
        if (TextUtils.isEmpty(password)) {
            error = activity.getString(R.string.error_field_required);
        }

        return error;
    }
}
