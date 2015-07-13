package com.tamzid.android.voatapilibrary;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.MainThreadExecutor;

/** Connects to the Voat.co API by initializing the {@link VoatService} using {@link RestAdapter} */
public class VoatApi {

    /**
     * Main Voat.co Web API endpoint
     */
    public static final String VOAT_WEB_API_ENDPOINT = "https://fakevout.azurewebsites.net/api";

    private String mApiKey;
    private String mAccessToken;

    /**
     * Adds header with OAuth 2 token to every request requiring an authentication
     */
    private class WebApiAuthenticator implements RequestInterceptor {
        @Override
        public void intercept(RequestFacade request) {
            request.addHeader("Content-Type", "application/json");

            if (mApiKey != null) {
                request.addHeader("Voat-ApiKey", mApiKey);
            }

            if (mAccessToken != null) {
                request.addHeader("Authorization", "Bearer " + mAccessToken);
            }
        }
    }

    private final VoatService mVoatService;

    /**
     * Create an instance of VoatApi using the given executors.
     *
     * @param httpExecutor executor for http request. Cannot be null.
     * @param callbackExecutor executor for callbacks. If null is passed, then the same thread
     *                         that created the instance is used.
     */
    public VoatApi(@NonNull Executor httpExecutor, @Nullable Executor callbackExecutor) {
        mVoatService = init(httpExecutor, callbackExecutor);
    }

    private VoatService init(Executor httpExecutor, Executor callbackExecutor) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setExecutors(httpExecutor, callbackExecutor)
                .setEndpoint(VOAT_WEB_API_ENDPOINT)
                .setRequestInterceptor(new WebApiAuthenticator())
                .build();
        return restAdapter.create(VoatService.class);
    }

    /**
     * Creates a new instance of VoatApi with a single thread executor for both http and callbacks.
     */
    public VoatApi() {
        Executor httpExecutor = Executors.newSingleThreadExecutor();
        MainThreadExecutor callbackExecutor = new MainThreadExecutor();
        mVoatService = init(httpExecutor, callbackExecutor);
    }

    /**
     * Sets the public API key on the wrapper and/or the access token (obtained through authentication).
     * Use it to initialize or update with new values. If you want to remove the tokens, set them to null.
     *
     * @param apiKey Public API key to set on the wrapper. Required.
     * @param accessToken Private authentication token for the user. Some API calls do not require
     *                    authentication, so this can be set to null.
     * @return Instance of the wrapper.
     */
    public VoatApi setApiKeyAndAccessToken(String apiKey, String accessToken) {
        mAccessToken = accessToken;
        mApiKey = apiKey;
        return this;
    }

    /**
     * @return The VoatApi instance
     */
    public VoatService getVoatService() {
        return mVoatService;
    }

}
