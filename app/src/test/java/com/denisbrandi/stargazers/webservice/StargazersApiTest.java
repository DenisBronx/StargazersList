package com.denisbrandi.stargazers.webservice;

import com.denisbrandi.stargazers.model.Stargazer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by denis on 11/03/17.
 */
public class StargazersApiTest {

    private Gson gson;
    private StargazersApi stargazersApi;

    private String stargazerJson = "{\n" +
            "    \"login\": \"octocat\",\n" +
            "    \"id\": 1,\n" +
            "    \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
            "    \"gravatar_id\": \"\",\n" +
            "    \"url\": \"https://api.github.com/users/octocat\",\n" +
            "    \"html_url\": \"https://github.com/octocat\",\n" +
            "    \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
            "    \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
            "    \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
            "    \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
            "    \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
            "    \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
            "    \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
            "    \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
            "    \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
            "    \"type\": \"User\",\n" +
            "    \"site_admin\": false\n" +
            "  }";

    @Before
    public void setup() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .client(new OkHttpClient.Builder().build())
                .build();

        stargazersApi = retrofit.create(StargazersApi.class);
    }

    /*
    * Just to check that names of fields are correct
    * */
    @Test
    public void checkStargazerParsing() {

        Stargazer stargazer = gson.fromJson(stargazerJson, Stargazer.class);

        assertEquals(stargazer.getLogin(), "octocat");
        assertEquals(stargazer.getId(), 1);
        assertEquals(stargazer.getAvatarUrl(), "https://github.com/images/error/octocat_happy.gif");
        assertEquals(stargazer.getGravatarId(), "");
        assertEquals(stargazer.getUrl(), "https://api.github.com/users/octocat");
        assertEquals(stargazer.getHtmlUrl(), "https://github.com/octocat");
        assertEquals(stargazer.getFollowersUrl(), "https://api.github.com/users/octocat/followers");
        assertEquals(stargazer.getFollowingUrl(), "https://api.github.com/users/octocat/following{/other_user}");
        assertEquals(stargazer.getGistsUrl(), "https://api.github.com/users/octocat/gists{/gist_id}");
        assertEquals(stargazer.getStarredUrl(), "https://api.github.com/users/octocat/starred{/owner}{/repo}");
        assertEquals(stargazer.getSubscriptionsUrl(), "https://api.github.com/users/octocat/subscriptions");
        assertEquals(stargazer.getOrganizationsUrl(), "https://api.github.com/users/octocat/orgs");
        assertEquals(stargazer.getReposUrl(), "https://api.github.com/users/octocat/repos");
        assertEquals(stargazer.getEventsUrl(), "https://api.github.com/users/octocat/events{/privacy}");
        assertEquals(stargazer.getReceivedEventsUrl(), "https://api.github.com/users/octocat/received_events");
        assertEquals(stargazer.getType(), "User");
        assertEquals(stargazer.isSiteAdmin(), false);

    }

    @Test
    public void testStargazersCall() {

        try {
            Response<List<Stargazer>> response =stargazersApi.getStargazersNonRx("ReactiveX", "RxAndroid", 1).execute();

            assertTrue(response.isSuccessful());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}