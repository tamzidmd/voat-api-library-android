# voat-api-library-android
A wrapper for the Voat.co API using Retrofit

## Introduction
Use this library to access the Voat.co API in Android. The library comes with Retrofit and OkHttp dependencies.

Currently the API is in beta, and certain calls do not work. As of this writing:
+ A user's saved items cannot be accessed
+ Certain query strings do not work

## Basic Use
You will first need to get a public API Key from Voat.co to access its API.
It should be easy to obtain once the API comes out of beta.

You can set up a connection to the API like this:

```java
VoatApi api = new VoatApi().setApiKeyAndAccessToken(apiKey, accessToken);
VoatService voatService = api.getVoatService();
```

Then it might be used like this to do an asynchronous call:

```java
voatService.getSubmission(submissionId, new Callback<SubmissionResponse>() {
    @Override
    public void success(final SubmissionResponse submissionResponse, Response response) {
        Log.d(LOG_TAG, response.getReason());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, submissionResponse.data.title, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(LOG_TAG, error.toString());
    }
});
```

Or like this:

```java
SubmissionResponse submissionResponse = voatService.getSubmission(submissionId);
```

## Getting Access Tokens

```java
Executor httpExecutor = Executors.newSingleThreadExecutor();
MainThreadExecutor callbackExecutor = new MainThreadExecutor();

RestAdapter restAdapter = new RestAdapter.Builder()
        .setLogLevel(RestAdapter.LogLevel.BASIC)
        .setExecutors(httpExecutor, callbackExecutor)
        .setEndpoint(VoatApi.VOAT_WEB_API_ENDPOINT)
        .setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Content-Type", "application/x-www-form-urlencoded");
                request.addHeader("Voat-ApiKey", API_KEY);
            }
        })
        .build();


VoatService voatService = restAdapter.create(VoatService.class);

AccessToken accessToken = voatService.getAccessToken("password", username, password);
```

**Make sure you save the access token until it expires!**
If you request a new access token every time, you will get IP banned.

I highly recommend checking out the objects in the "models" and "responses" folders to get an idea of what 
data gets returned.
