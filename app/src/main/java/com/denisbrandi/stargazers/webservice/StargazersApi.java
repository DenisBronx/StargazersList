package com.denisbrandi.stargazers.webservice;

import com.denisbrandi.stargazers.model.Stargazer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by denis on 11/03/17.
 */

public interface StargazersApi {

    @GET("repos/{owner}/{repo}/stargazers")
    Observable<List<Stargazer>> getStargazers(@Path("owner") String owner,
                                              @Path("repo") String repo,
                                              @Query("page") int page);

    @GET("repos/{owner}/{repo}/stargazers")
    Call<List<Stargazer>> getStargazersNonRx(@Path("owner") String owner,
                                             @Path("repo") String repo,
                                             @Query("page") int page);

}
