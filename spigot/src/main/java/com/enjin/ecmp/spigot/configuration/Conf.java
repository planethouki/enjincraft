package com.enjin.ecmp.spigot.configuration;

import com.enjin.ecmp.spigot.i18n.Locale;
import org.bukkit.configuration.Configuration;

public class Conf {

    private Configuration internalConf;

    public Conf(Configuration internalConf) {
        this.internalConf = internalConf;
    }

    public String getBaseUrl() {
        return internalConf.getString(ConfPath.BASE_URL.path());
    }

    public int getAppId() {
        return internalConf.getInt(ConfPath.APP_ID.path());
    }

    public String getAppSecret() {
        return internalConf.getString(ConfPath.APP_SECRET.path());
    }

    public int getDevIdentityId() {
        return internalConf.getInt(ConfPath.DEV_IDENTITY_ID.path());
    }

    public String getLocale() {
        return internalConf.getString(ConfPath.LOCALE.path());
    }

    public void setLocale(Locale locale) {
        internalConf.set(ConfPath.LOCALE.path(), locale.locale());
    }

    public boolean isSdkDebugEnabled() {
        return internalConf.getBoolean(ConfPath.DEBUG_SDK.path());
    }

    public boolean isPluginDebugEnabled() {
        return internalConf.getBoolean(ConfPath.DEBUG_PLUGIN.path());
    }

    public String getSentryUrl() {
        return internalConf.getString(ConfPath.SENTRY_URL.path());
    }

}