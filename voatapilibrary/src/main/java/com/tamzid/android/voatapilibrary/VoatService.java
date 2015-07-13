package com.tamzid.android.voatapilibrary;

import com.tamzid.android.voatapilibrary.enums.MessageState;
import com.tamzid.android.voatapilibrary.enums.MessageType;
import com.tamzid.android.voatapilibrary.models.AccessToken;
import com.tamzid.android.voatapilibrary.responses.CommentResponse;
import com.tamzid.android.voatapilibrary.responses.CommentsResponse;
import com.tamzid.android.voatapilibrary.responses.ErrorResponse;
import com.tamzid.android.voatapilibrary.responses.SubmissionResponse;
import com.tamzid.android.voatapilibrary.responses.SubmissionsResponse;
import com.tamzid.android.voatapilibrary.responses.SubscriptionResponse;
import com.tamzid.android.voatapilibrary.responses.SubverseInfoResponse;
import com.tamzid.android.voatapilibrary.responses.UserInfoResponse;
import com.tamzid.android.voatapilibrary.responses.UserMessageResponse;
import com.tamzid.android.voatapilibrary.responses.UserPreferencesResponse;
import com.tamzid.android.voatapilibrary.responses.VoteResponse;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/** Contains all calls to the Voat.co API using Retrofit annotations. */
public interface VoatService {

    //region SEARCH_OPTIONS =======================================================================

    /**
     * The span of time your search encompasses. Specify the text value in querystring.
     * Type: SortSpan
     */
    public static final String SPAN = "span";

    /**
     * The sort algorithm used to order search results. Specify the text value in querystring.
     * Type: SortAlgorithm
     */
    public static final String SORT = "sort";

    /**
     * The sort order requested. Specify the text value in querystring.
     * Type: SortDirection
     */
    public static final String DIRECTION = "direction";

    /**
     * The start date for limiting search results.
     * Type: date
     */
    public static final String STARTDATE = "startDate";

    /**
     * The end date for limiting search results. This value is overridden if span is provided.
     * Type: date
     */
    public static final String ENDDATE = "endDate";

    /**
     * The number of search records requested. Max Value is 50.
     * Type: integer
     */
    public static final String COUNT = "count";

    /**
     * The current index to start from for search results. This value is a paging index.
     * Type: integer
     */
    public static final String INDEX = "index";

    /**
     * The page in which to retrieve. This value simply overriddes 'Index' and calculates it for you.
     * How nice are we? Fairly nice I must say. Paging starts on page 1 not page 0.
     * Type: integer
     */
    public static final String PAGE = "page";

    /**
     * The search value to match for submissions or comments.
     * Type: string
     */
    public static final String SEARCH = "search";

    /**
     * Specifies the depth of comment tree to retrieve. Used only for comment queries.
     * Type: integer
     */
    public static final String DEPTH = "depth";

    //endregion

    //region SUBMISSIONS ==========================================================================

    /**
     * Gets submissions for a subverse. Supports Search Options querystring arguments.
     *
     * @param subverse The subverse in which to retrieve submissions. Special subverses include:
     *                 _front (currently logged in users front page according to subscriptions),
     *                 _default (voat's default subverses), _all (/v/all submissions)
     * @param callback List of submissions objects
     */
    @GET("/v1/v/{subverse}")
    public void getSubmissions(@Path("subverse") String subverse, Callback<SubmissionsResponse> callback);

    /**
     * Gets submissions for a subverse. Supports Search Options querystring arguments.
     *
     * @param subverse The subverse in which to retrieve submissions. Special subverses include:
     *                 _front (currently logged in users front page according to subscriptions),
     *                 _default (voat's default subverses), _all (/v/all submissions)
     * @return List of submissions objects
     */
    @GET("/v1/v/{subverse}")
    public SubmissionsResponse getSubmissions(@Path("subverse") String subverse);

    /**
     * Gets submissions for a subverse. Supports Search Options querystring arguments.
     *
     * @param subverse The subverse in which to retrieve submissions. Special subverses include:
     *                 _front (currently logged in users front page according to subscriptions),
     *                 _default (voat's default subverses), _all (/v/all submissions)
     * @param options Search Options querystring arguments.
     * @param callback List of submissions objects
     */
    @GET("/v1/v/{subverse}")
    public void getSubmissions(@Path("subverse") String subverse, @QueryMap Map<String, Object> options, Callback<SubmissionsResponse> callback);

    /**
     * Gets submissions for a subverse. Supports Search Options querystring arguments.
     *
     * @param subverse The subverse in which to retrieve submissions. Special subverses include:
     *                 _front (currently logged in users front page according to subscriptions),
     *                 _default (voat's default subverses), _all (/v/all submissions)
     * @param options Search Options querystring arguments.
     * @return List of submissions objects
     */
    @GET("/v1/v/{subverse}")
    public SubmissionsResponse getSubmissions(@Path("subverse") String subverse, @QueryMap Map<String, Object> options);

    /**
     * Posts a new submission to the specified subverse. Requires authentication.
     *
     * @param subverse The subverse in which to post
     * @param body The properties of the post, see below.
     * @param callback The submission response object
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserSubmission">UserSubmission</a> object
     */
    @POST("/v1/v/{subverse}")
    public void postSubmission(@Path("subverse") String subverse, @Body Map<String, Object> body, Callback<SubmissionResponse> callback);

    /**
     *  Posts a new submission to the specified subverse. Requires authentication.
     *
     * @param subverse The subverse in which to post
     * @param body The properties of the post, see below.
     * @return The submission response object
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserSubmission">UserSubmission</a> object
     */
    @POST("/v1/v/{subverse}")
    public SubmissionResponse postSubmission(@Path("subverse") String subverse, @Body Map<String, Object> body);

    //endregion

    //region SUBMISSION ===========================================================================

    /**
     * Gets a single submission by Id.
     *
     * @param subverse The subverse where the post is located
     * @param submissionId The ID of the submission
     * @param callback Callback method
     */
    @GET("/v1/v/{subverse}/{submissionID}")
    public void getSubmission(@Path("subverse") String subverse, @Path("submissionID") int submissionId, Callback<SubmissionResponse> callback);

    /**
     * Get a single submission by Id.
     *
     * @param subverse The subverse where the post is located
     * @param submissionId The ID of the submission
     * @return The {@link SubmissionResponse} object containing data for the submission
     */
    @GET("/v1/v/{subverse}/{submissionID}")
    public SubmissionResponse getSubmission(@Path("subverse") String subverse, @Path("submissionID") int submissionId);

    /**
     * Get a single submission by Id.
     *
     * @param submissionId The ID of the submission
     * @param callback The {@link SubmissionResponse} object containing data for the submission
     */
    @GET("/v1/submissions/{submissionID}")
    public void getSubmission(@Path("submissionID") int submissionId, Callback<SubmissionResponse> callback);

    /**
     * Get a single submission by Id.
     *
     * @param submissionId The ID of the submission
     * @return The {@link SubmissionResponse} object containing data for the submission
     */
    @GET("/v1/submissions/{submissionID}")
    public SubmissionResponse getSubmission(@Path("submissionID") int submissionId);

    /**
     * Edits a submission. Authentication required.
     *
     * @param subverse The subverse where the post is located
     * @param submissionId The ID of the submission to edit.
     * @param body Values to edit
     * @param callback The {@link SubmissionResponse} object containing data for the submission
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserSubmission">UserSubmission</a> object
     */
    @PUT("/v1/v/{subverse}/{submissionID}")
    public void editSubmission(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Body Map<String, Object> body, Callback<SubmissionResponse> callback);

    /**
     * Edits a submission. Authentication required.
     *
     * @param subverse The subverse where the post is located
     * @param submissionId The ID of the submission to edit.
     * @param body Values to edit
     * @return The {@link SubmissionResponse} object containing data for the submission
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserSubmission">UserSubmission</a> object
     */
    @PUT("/v1/v/{subverse}/{submissionID}")
    public SubmissionResponse editSubmission(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Body Map<String, Object> body);

    /**
     * Edits a submission. Authentication required.
     *
     * @param submissionId The ID of the submission to edit.
     * @param body Values to edit
     * @param callback The {@link SubmissionResponse} object containing data for the submission
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserSubmission">UserSubmission</a> object
     */
    @PUT("/v1/submissions/{submissionID}")
    public void editSubmission(@Path("submissionID") int submissionId, @Body Map<String, Object> body, Callback<SubmissionResponse> callback);

    /**
     * Edits a submission. Authentication required.
     *
     * @param submissionId The ID of the submission to edit.
     * @param body Values to edit
     * @return The {@link SubmissionResponse} object containing data for the submission
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserSubmission">UserSubmission</a> object
     */
    @PUT("/v1/submissions/{submissionID}")
    public SubmissionResponse editSubmission(@Path("submissionID") int submissionId, @Body Map<String, Object> body);

    /**
     * Deletes a submission. Authentication required.
     *
     * @param subverse The subverse where the post is located
     * @param submissionId The ID of the submission to edit.
     * @param callback The {@link ErrorResponse} object containing error data
     */
    @DELETE("/v1/v/{subverse}/{submissionID}")
    public void deleteSubmission(@Path("subverse") String subverse, @Path("submissionID") int submissionId, Callback<ErrorResponse> callback);

    /**
     * Deletes a submission. Authentication required.
     *
     * @param subverse The subverse where the post is located
     * @param submissionId The ID of the submission to edit.
     * @return The {@link ErrorResponse} object containing error data
     */
    @DELETE("/v1/v/{subverse}/{submissionID}")
    public ErrorResponse deleteSubmission(@Path("subverse") String subverse, @Path("submissionID") int submissionId);

    //endregion

    //region SUBVERSE ===========================================================================

    /**
     * The subverse name in which to retrieve information.
     *
     * @param subverse The subverse name from which to retrieve information.
     * @param callback Object with the subverse information
     */
    @GET("/v1/v/{subverse}/info")
    public void getSubverseInfo(@Path("subverse") String subverse, Callback<SubverseInfoResponse> callback);

    /**
     * The subverse name in which to retrieve information.
     *
     * @param subverse The subverse name from which to retrieve information.
     * @return Object with the subverse information
     */
    @GET("/v1/v/{subverse}/info")
    public SubverseInfoResponse getSubverseInfo(@Path("subverse") String subverse);

    /**
     * Blocks a subverse from a user's /v/all submissions. Authentication required.
     *
     * @param subverse The subverse to block
     * @param callback Object with the error response
     */
    @POST("/v1/v/{subverse}/block")
    public void blockSubverse(@Path("subverse") String subverse, Callback<ErrorResponse> callback);

    /**
     * Blocks a subverse from a user's /v/all submissions. Authentication required.
     *
     * @param subverse The subverse to block
     * @return Object with the error response
     */
    @POST("/v1/v/{subverse}/block")
    public ErrorResponse blockSubverse(@Path("subverse") String subverse);

    /**
     * Unblocks a previously blocked subverse from a users /v/all submissions. Authentication required.
     *
     * @param subverse The subverse to unblock
     * @param callback Object with the error response
     */
    @DELETE("/v1/v/{subverse}/block")
    public void unblockSubverse(@Path("subverse") String subverse, Callback<ErrorResponse> callback);

    /**
     * Unblocks a previously blocked subverse from a users /v/all submissions. Authentication required.
     *
     * @param subverse The subverse to unblock
     * @return Object with the error response
     */
    @DELETE("/v1/v/{subverse}/block")
    public ErrorResponse unblockSubverse(@Path("subverse") String subverse);

    //endregion

    //region COMMENTS ===========================================================================

    /**
     * Gets comments for a submission.
     *
     * @param subverse The subverse to get the comment from.
     * @param submissionId The ID of the submission to retrieve comments for.
     * @param callback Object with the comment information.
     */
    @GET("/v1/v/{subverse}/{submissionID}/comments")
    public void getComments(@Path("subverse") String subverse, @Path("submissionID") int submissionId, Callback<CommentsResponse> callback);

    /**
     * Gets comments for a submission.
     *
     * @param subverse The subverse to get the comment from.
     * @param submissionId The ID of the submission to retrieve comments for.
     * @return Object with the comment information.
     */
    @GET("/v1/v/{subverse}/{submissionID}/comments")
    public CommentsResponse getComments(@Path("subverse") String subverse, @Path("submissionID") int submissionId);

    /**
     * Gets comments for a submission. Supports Search Options querystring arguments.
     *
     * @param subverse The subverse to get the comment from.
     * @param submissionId The ID of the submission to retrieve comments for.
     * @param options Optional search query parameters
     * @param callback Object with the comment information.
     */
    @GET("/v1/v/{subverse}/{submissionID}/comments")
    public void getComments(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @QueryMap Map<String, Object> options, Callback<CommentsResponse> callback);

    /**
     * Gets comments for a submission. Supports Search Options querystring arguments.
     *
     * @param subverse The subverse to get the comment from.
     * @param submissionId The ID of the submission to retrieve comments for.
     * @param options Optional search query parameters
     * @return Object with the comment information.
     */
    @GET("/v1/v/{subverse}/{submissionID}/comments")
    public CommentsResponse getComments(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @QueryMap Map<String, Object> options);

    /**
     * Gets comments for a submission starting from a specified parent comment.
     *
     * @param subverse The subverse to get the comment from.
     * @param submissionId The ID of the submission to retrieve comments for.
     * @param parentId The comment id to start loading from.
     * @param callback Object with the comment information.
     */
    @GET("/v1/v/{subverse}/{submissionID}/comments/{parentID}")
    public void getComments(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Path("parentID") int parentId, Callback<CommentsResponse> callback);

    /**
     * Gets comments for a submission starting from a specified parent comment.
     *
     * @param subverse The subverse to get the comment from.
     * @param submissionId The ID of the submission to retrieve comments for.
     * @param parentId The comment id to start loading from.
     * @return Object with the comment information.
     */
    @GET("/v1/v/{subverse}/{submissionID}/comments/{parentID}")
    public CommentsResponse getComments(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Path("parentID") int parentId);

    /**
     * Gets comments for a submission starting from a specified parent comment. Supports Search Options querystring arguments.
     *
     * @param subverse The subverse to get the comment from.
     * @param submissionId The ID of the submission to retrieve comments for.
     * @param parentId The comment id to start loading from.
     * @param options Optional search query parameters
     * @param callback Object with the comment information.
     */
    @GET("/v1/v/{subverse}/{submissionID}/comments/{parentID}")
    public void getComments(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Path("parentID") int parentId, @QueryMap Map<String, Object> options, Callback<CommentsResponse> callback);

    /**
     * Gets comments for a submission starting from a specified parent comment. Supports Search Options querystring arguments.
     *
     * @param subverse The subverse to get the comment from.
     * @param submissionId The ID of the submission to retrieve comments for.
     * @param parentId The comment id to start loading from.
     * @param options Optional search query parameters
     * @return Object with the comment information.
     */
    @GET("/v1/v/{subverse}/{submissionID}/comments/{parentID}")
    public CommentsResponse getComments(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Path("parentID") int parentId, @QueryMap Map<String, Object> options);

    //endregion

    //region COMMENT ==============================================================================

    /**
     * Retrieves a single comment
     *
     * @param commentId The ID of the comment in which to retrieve.
     * @param callback Object containing comment details
     */
    @GET("/v1/comments/{commentID}")
    public void getComment(@Path("commentID") int commentId, Callback<CommentResponse> callback);

    /**
     * Retrieves a single comment
     *
     * @param commentId The ID of the comment in which to retrieve.
     * @return Object containing comment details
     */
    @GET("/v1/comments/{commentID}")
    public CommentResponse getComment(@Path("commentID") int commentId);

    /**
     *  Post a REPLY to an existing comment. Use this method for inbox comment replies. Authentication required.
     *
     * @param commentId The ID of the comment in which to respond.
     * @param body The value of the comment in which to post.
     * @param callback Object containing comment details
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @POST("/v1/comments/{commentID}")
    public void postReplyInbox(@Path("commentID") int commentId, @Body Map<String, Object> body, Callback<CommentResponse> callback);

    /**
     * Post a REPLY to an existing comment. Use this method for inbox comment replies. Authentication required.
     *
     * @param commentId The ID of the comment in which to respond.
     * @param body The value of the comment in which to post.
     * @return Object containing comment details
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @POST("/v1/comments/{commentID}")
    public CommentResponse postReplyInbox(@Path("commentID") int commentId, @Body Map<String, Object> body);

    /**
     * Post a REPLY to an existing comment in a submission. Authentication required.
     *
     * @param subverse The subverse the submission belongs to.
     * @param submissionId The submission id for which the comment is meant
     * @param commentId The commend id for which this comment will be a child
     * @param body The value of the user comment
     * @param callback Object containing comment details
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @POST("/v1/v/{subverse}/{submissionID}/comment/{commentID}")
    public void postReplyToComment(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Path("commentID") int commentId, @Body Map<String, Object> body, Callback<CommentResponse> callback);

    /**
     * Post a REPLY to an existing comment in a submission. Authentication required.
     *
     * @param subverse The subverse the submission belongs to.
     * @param submissionId The submission id for which the comment is meant
     * @param commentId The commend id for which this comment will be a child
     * @param body The value of the user comment
     * @return Object containing comment details
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @POST("/v1/v/{subverse}/{submissionID}/comment/{commentID}")
    public CommentResponse postReplyToComment(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Path("commentID") int commentId, @Body Map<String, Object> body);

    /**
     * Post a top level comment to a submission. Authentication Required
     *
     * @param subverse The subverse the submission belongs to.
     * @param submissionId The submission id for which the comment is meant
     * @param body Content of request
     * @param callback Object containing comment details
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @POST("/v1/v/{subverse}/{submissionID}/comment")
    public void postReplyToSubmission(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Body Map<String, Object> body, Callback<CommentResponse> callback);

    /**
     * Post a top level comment to a submission. Authentication Required
     *
     * @param subverse The subverse the submission belongs to.
     * @param submissionId The submission id for which the comment is meant
     * @param body Content of request
     * @return Object containing comment details
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @POST("/v1/v/{subverse}/{submissionID}/comment")
    public CommentResponse postReplyToSubmission(@Path("subverse") String subverse, @Path("submissionID") int submissionId, @Body Map<String, Object> body);

    /**
     * Edits an existing comment. Authentication required.
     *
     * @param commentId The ID of the comment in which to edit.
     * @param body The new value of the comment
     * @param callback Object containing comment details
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @PUT("/v1/comments/{commentID}")
    public void editComment(@Path("commentID") int commentId, @Body Map<String, Object> body, Callback<CommentResponse> callback);

    /**
     * Edits an existing comment. Authentication required.
     *
     * @param commentId The ID of the comment in which to edit.
     * @param body The new value of the comment
     * @return Object containing comment details
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @PUT("/v1/comments/{commentID}")
    public CommentResponse editComment(@Path("commentID") int commentId, @Body Map<String, Object> body);

    /**
     * Deletes an existing comment. Authentication required.
     *
     * @param commentId The ID of the comment in which to delete.
     * @param callback ErrorResponse object
     */
    @DELETE("/v1/comments/{commentID}")
    public void deleteComment(@Path("commentID") int commentId, Callback<ErrorResponse> callback);

    /**
     * Deletes an existing comment. Authentication required.
     *
     * @param commentId The ID of the comment in which to delete.
     * @return ErrorResponse object
     */
    @DELETE("/v1/comments/{commentID}")
    public ErrorResponse deleteComment(@Path("commentID") int commentId);

    //endregion

    //region USER_PREFERENCES ===========================================================================

    /**
     * Retrieves user preferences. Requires authentication.
     *
     * @param callback Returns User Preferences object
     */
    @GET("/v1/u/preferences")
    public void getUserPreferences(Callback<UserPreferencesResponse> callback);

    /**
     * Retrieves user preferences. Requires authentication.
     *
     * @return Returns User Preferences object
     */
    @GET("/v1/u/preferences")
    public UserPreferencesResponse getUserPreferences();

    /**
     * Updates a user's preferences. Requires authentication.
     *
     * @param body The preferences in which to edit.
     * @param callback Returns error response object
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=ApiUserPreferences">ApiUserPreferences</a> object
     */
    @PUT("/v1/u/preferences")
    public void setUserPreferences(@Body Map<String, Object> body, Callback<ErrorResponse> callback);

    /**
     * Updates a user's preferences. Requires authentication.
     *
     * @param body The preferences in which to edit.
     * @return Returns error response object
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=ApiUserPreferences">ApiUserPreferences</a> object
     */
    @PUT("/v1/u/preferences")
    public ErrorResponse setUserPreferences(@Body Map<String, Object> body);

    //endregion

    //region USER ===========================================================================

    /**
     * Retrieves user information
     *
     * @param user The username to retrieve info for.
     * @param callback User info object
     */
    @GET("/v1/u/{user}/info")
    public void getUserInfo(@Path("user") String user, Callback<UserInfoResponse> callback);

    /**
     * Retrieves user information
     *
     * @param user The username to retrieve info for.
     * @return User info object
     */
    @GET("/v1/u/{user}/info")
    public UserInfoResponse getUserInfo(@Path("user") String user);

    /**
     * Get comments for a user.
     *
     * @param user The username to retrieve comments for.
     * @param callback Comments response object
     */
    @GET("/v1/u/{user}/comments")
    public void getUserComments(@Path("user") String user, Callback<CommentsResponse> callback);

    /**
     * Get comments for a user.
     *
     * @param user The username to retrieve comments for.
     * @return Comments response object
     */
    @GET("/v1/u/{user}/comments")
    public CommentsResponse getUserComments(@Path("user") String user);

    /**
     * Get comments for a user. Supports Search Options querystring arguments.
     *
     * @param user The username to retrieve comments for.
     * @param options Search Options querystring arguments.
     * @param callback Comments response object
     */
    @GET("/v1/u/{user}/comments")
    public void getUserComments(@Path("user") String user, @QueryMap Map<String, Object> options, Callback<CommentsResponse> callback);

    /**
     * Get comments for a user. Supports Search Options querystring arguments.
     *
     * @param user The username to retrieve comments for.
     * @param options Search Options querystring arguments.
     * @return Comments response object
     */
    @GET("/v1/u/{user}/comments")
    public CommentsResponse getUserComments(@Path("user") String user, @QueryMap Map<String, Object> options);

    /**
     * Gets submissions for a user.
     *
     * @param user The username to retrieve submissions for.
     * @param callback Submissions response object
     */
    @GET("/v1/u/{user}/submissions")
    public void getUserSubmissions(@Path("user") String user, Callback<SubmissionsResponse> callback);

    /**
     * Gets submissions for a user.
     *
     * @param user The username to retrieve submissions for.
     * @return Submissions response object
     */
    @GET("/v1/u/{user}/submissions")
    public SubmissionsResponse getUserSubmissions(@Path("user") String user);

    /**
     * Gets submissions for a user. Supports Search Options querystring arguments.
     *
     * @param user The username to retrieve submissions for.
     * @param options Search Options querystring arguments.
     * @param callback Submissions response object
     */
    @GET("/v1/u/{user}/submissions")
    public void getUserSubmissions(@Path("user") String user,  @QueryMap Map<String, Object> options, Callback<SubmissionsResponse> callback);

    /**
     * Gets submissions for a user. Supports Search Options querystring arguments.
     *
     * @param user The username to retrieve submissions for.
     * @param options Search Options querystring arguments.
     * @return Submissions response object
     */
    @GET("/v1/u/{user}/submissions")
    public SubmissionsResponse getUserSubmissions(@Path("user") String user,  @QueryMap Map<String, Object> options);

    /**
     * Gets subscriptions for a user
     *
     * @param user The username to retrieve subscriptions for.
     * @param callback Subscription response object
     */
    @GET("/v1/u/{user}/subscriptions")
    public void getUserSubscriptions(@Path("user") String user, Callback<SubscriptionResponse> callback);

    /**
     * Gets subscriptions for a user
     *
     * @param user The username to retrieve subscriptions for.
     * @return Subscription response object
     */
    @GET("/v1/u/{user}/subscriptions")
    public SubscriptionResponse getUserSubscriptions(@Path("user") String user);

    /* TODO: Not currently implemented
    @GET("/v1/u/saved")
    public void getSavedItems();
    @GET("/v1/u/saved")
    */

    //endregion

    //region USER_MESSAGES ========================================================================

    /**
     * Replies to a user message. Authentication required.
     *
     * @param id The messageID that is being replied to
     * @param body The content of the message
     * @param callback Error response object
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @POST("/v1/u/messages/reply/{id}")
    public void replyToUserMessage(@Path("id") int id, @Body Map<String, Object> body, Callback<ErrorResponse> callback);

    /**
     * Replies to a user message. Authentication required.
     *
     * @param id The messageID that is being replied to
     * @param body The content of the message
     * @return Error response object
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=UserValue">UserValue</a> object
     */
    @POST("/v1/u/messages/reply/{id}")
    public ErrorResponse replyToUserMessage(@Path("id") int id, @Body Map<String, Object> body);

    /**
     * Gets messages for the logged in user. Authentication Required.
     *
     * @param type A value indicating what type of messages to retrieve. Use text values of enumeration.
     * @param state A value indicating what state of messages to retrieve. Use text values of enumeration
     * @param callback Collection of user messages
     */
    @GET("/v1/u/messages/{type}/{state}")
    public void getUserMessages(@Path("type") MessageType type, @Path("state") MessageState state, Callback<UserMessageResponse> callback);

    /**
     * Gets messages for the logged in user. Authentication Required.
     *
     * @param type A value indicating what type of messages to retrieve. Use text values of enumeration.
     * @param state A value indicating what state of messages to retrieve. Use text values of enumeration
     * @return Collection of user messages
     */
    @GET("/v1/u/messages/{type}/{state}")
    public UserMessageResponse getUserMessages(@Path("type") MessageType type, @Path("state") MessageState state);

    /**
     * Sends a new Private Message to a user. Authentication Required.
     *
     * @param body The message details
     * @param callback Error response object
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=ApiSendUserMessage">ApiSendUserMessage</a> object
     */
    @POST("/v1/u/messages")
    public void sendPrivateMessage(@Body Map<String, Object> body, Callback<ErrorResponse> callback);

    /**
     * Sends a new Private Message to a user. Authentication Required.
     *
     * @param body The message details
     * @return Error response object
     * @see <a href="https://fakevout.azurewebsites.net/api/help/resourcemodel?modelName=ApiSendUserMessage">ApiSendUserMessage</a> object
     */
    @POST("/v1/u/messages")
    public ErrorResponse sendPrivateMessage(@Body Map<String, Object> body);

    //endregion

    //region VOTE ===========================================================================

    /**
     * Submit votes of a user. Requires authentication.
     *
     * @param type Specifies what the ID value refers to. One of the following: comment, submission.
     * @param id ID of content to vote on. The CommentID or SubmissionID.
     * @param vote One of the following: -1 (down vote), 0 (revoke, unvote), 1 (up vote)
     * @param body POST requires a Body, pass in an empty HashMap to appease it.
     * @param callback Object with data.
     */
    @POST("/v1/vote/{type}/{id}/{vote}")
    public void vote(@Path("type") String type, @Path("id") int id, @Path("vote") int vote, @Body Map<String, Object> body, Callback<VoteResponse> callback);

    /**
     * Submit votes of a user. Requires authentication.
     *
     * @param type Specifies what the ID value refers to. One of the following: comment, submission.
     * @param id ID of content to vote on. The CommentID or SubmissionID.
     * @param vote One of the following: -1 (down vote), 0 (revoke, unvote), 1 (up vote)
     * @param body POST requires a Body, pass in an empty HashMap to appease it.
     * @return Object with data.
     */
    @POST("/v1/vote/{type}/{id}/{vote}")
    public VoteResponse vote(@Path("type") String type, @Path("id") int id, @Path("vote") int vote, @Body Map<String, Object> body);

    /**
     * Submit votes of a user. Requires authentication.
     *
     * @param type Specifies what the ID value refers to. One of the following: comment, submission.
     * @param id ID of content to vote on. The CommentID or SubmissionID.
     * @param vote One of the following: -1 (down vote), 0 (revoke, unvote), 1 (up vote)
     * @param revokeOnRevote Optional. If true then a duplicate vote will revoke (undo) the existing
     *                       vote, if false then a duplicate vote will be ignored. Choosing to use
     *                       this setting depends on your UI and how your users will interact with
     *                       it. If a user upvotes a submission but then wants to remove the upvote
     *                       they typically upvote the submission a second time, thus revoking the
     *                       original upvote and now the submission will be in an unvoted/revoked
     *                       state for the user. Default value is [true].
     * @param body POST requires a Body, pass in an empty HashMap to appease it.
     * @param callback with data.
     */
    @POST("/v1/vote/{type}/{id}/{vote}?revokeOnRevote={revokeOnRevote}")
    public void voteWithRevokeOnRevote(@Path("type") String type, @Path("id") int id, @Path("vote") int vote, @Path("revokeOnRevote") boolean revokeOnRevote, @Body Map<String, Object> body, Callback<VoteResponse> callback);

    /**
     * Submit votes of a user. Requires authentication.
     *
     * @param type Specifies what the ID value refers to. One of the following: comment, submission.
     * @param id ID of content to vote on. The CommentID or SubmissionID.
     * @param vote One of the following: -1 (down vote), 0 (revoke, unvote), 1 (up vote)
     * @param revokeOnRevote Optional. If true then a duplicate vote will revoke (undo) the existing
     *                       vote, if false then a duplicate vote will be ignored. Choosing to use
     *                       this setting depends on your UI and how your users will interact with
     *                       it. If a user upvotes a submission but then wants to remove the upvote
     *                       they typically upvote the submission a second time, thus revoking the
     *                       original upvote and now the submission will be in an unvoted/revoked
     *                       state for the user. Default value is [true].
     * @param body POST requires a Body, pass in an empty HashMap to appease it.
     * @return Object with data.
     */
    @POST("/v1/vote/{type}/{id}/{vote}?revokeOnRevote={revokeOnRevote}")
    public VoteResponse voteWithRevokeOnRevote(@Path("type") String type, @Path("id") int id, @Path("vote") int vote, @Path("revokeOnRevote") boolean revokeOnRevote, @Body Map<String, Object> body);

    //endregion

    //region SAVE ===========================================================================

    /**
     * Saves a submission to a users saved items collection. Requires authentication.
     *
     * @param submissionId The ID of the submission to save
     * @param body POST requires a Body, pass in an empty HashMap to appease it.
     * @param callback Object with error information.
     */
    @POST("/v1/submissions/{submissionID}/save")
    public void saveSubmission(@Path("submissionID") int submissionId, @Body Map<String, Object> body, Callback<ErrorResponse> callback);

    /**
     * Saves a submission to a users saved items collection. Requires authentication.
     *
     * @param submissionId The ID of the submission to save
     * @param body POST requires a Body, pass in an empty HashMap to appease it.
     * @return Object with error information.
     */
    @POST("/v1/submissions/{submissionID}/save")
    public ErrorResponse saveSubmission(@Path("submissionID") int submissionId, @Body Map<String, Object> body);

    /**
     * Deletes a saved submission from a users saved item collection. Requires authentication.
     *
     * @param submissionId The ID of the submission to unsave
     * @param callback Object with error information.
     */
    @DELETE("/v1/submissions/{submissionID}/save")
    public void unsaveSubmission(@Path("submissionID") int submissionId, Callback<ErrorResponse> callback);

    /**
     * Deletes a saved submission from a users saved item collection. Requires authentication.
     *
     * @param submissionId The ID of the submission to unsave
     * @return Object with error information.
     */
    @DELETE("/v1/submissions/{submissionID}/save")
    public ErrorResponse unsaveSubmission(@Path("submissionID") int submissionId);

    /**
     * Saves a comment to a users saved items collection. Requires authentication.
     *
     * @param commentId The ID of the comment to save
     * @param body POST requires a Body, pass in an empty HashMap to appease it.
     * @param callback Object with error information.
     */
    @POST("/v1/comments/{commentID}/save")
    public void saveComment(@Path("commentID") int commentId, @Body Map<String, Object> body, Callback<ErrorResponse> callback);

    /**
     * Saves a comment to a users saved items collection. Requires authentication.
     *
     * @param commentId The ID of the comment to save
     * @param body POST requires a Body, pass in an empty HashMap to appease it.
     * @return Object with error information.
     */
    @POST("/v1/comments/{commentID}/save")
    public ErrorResponse saveComment(@Path("commentID") int commentId, @Body Map<String, Object> body);

    /**
     * Deletes a saved comment from a users saved items collection. Requires authentication.
     *
     * @param commentId The ID of the comment to unsave
     * @param callback Object with error information.
     */
    @DELETE("/v1/comments/{commentID}/save")
    public void unsaveComment(@Path("commentID") int commentId, Callback<ErrorResponse> callback);

    /**
     * Deletes a saved comment from a users saved items collection. Requires authentication.
     *
     * @param commentId The ID of the comment to unsave
     * @return Object with error information.
     */
    @DELETE("/v1/comments/{commentID}/save")
    public ErrorResponse unsaveComment(@Path("commentID") int commentId);

    //endregion

    //region STREAM ===========================================================================

    /**
     * Returns a stream of submissions since the last call made to this endpoint. Used for live monitoring.
     * Authentication required.
     *
     * @param callback collection of submission objects
     */
    @GET("/v1/stream/submissions")
    public void getSubmissionsStream(Callback<SubmissionsResponse> callback);

    /**
     * Returns a stream of submissions since the last call made to this endpoint. Used for live monitoring.
     * Authentication required.
     *
     * @return collection of submission objects
     */
    @GET("/v1/stream/submissions")
    public SubmissionsResponse getSubmissionsStream();

    /**
     * Returns a stream of comments since the last call made to this endpoint. Used for live monitoring.
     * Authentication Required.
     *
     * @param callback collection of comment objects
     */
    @GET("/v1/stream/comments")
    public void getCommentsStream(Callback<CommentsResponse> callback);

    /**
     * Returns a stream of comments since the last call made to this endpoint. Used for live monitoring.
     * Authentication Required
     *
     * @return collection of comment objects
     */
    @GET("/v1/stream/comments")
    public CommentsResponse getCommentsStream();

    //endregion

    //region AUTHENTICATION =======================================================================

    /**
     * Used to POST your public API key and get back an Access Token.
     *
     * @param grantType Grant type should be "password"
     * @param username Username of the user trying to gain authentication
     * @param password Password of the user trying to gain authentication
     * @param callback {@link AccessToken} object containing the Access Token, expiration date of the token,
     *                 and other data.
     */
    @FormUrlEncoded
    @POST("/token")
    public void getAccessToken(@Field("grant_type") String grantType, @Field("username") String username, @Field("password") String password, Callback<AccessToken> callback);

    /**
     * Used to POST your public API key and get back an Access Token.
     *
     * @param grantType Grant type should be "password"
     * @param username Username of the user trying to gain authentication
     * @param password Password of the user trying to gain authentication
     * @return {@link AccessToken} object containing the Access Token, expiration date of the token,
     *         and other data.
     */
    @FormUrlEncoded
    @POST("/token")
    public AccessToken getAccessToken(@Field("grant_type") String grantType, @Field("username") String username, @Field("password") String password);

    //endregion

}
