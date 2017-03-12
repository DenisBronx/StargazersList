package com.denisbrandi.stargazers.stargazerslist.viewmodel;

import com.denisbrandi.stargazers.model.Stargazer;

/**
 * Created by denis on 12/03/17.
 */

public class StargazerDetailViewModel {

    private Stargazer stargazer;

    public StargazerDetailViewModel(Stargazer stargazer) {
        this.stargazer = stargazer;
    }

    public String getName() {
        return stargazer.getLogin();
    }

    public String getImageUrl() {
        return stargazer.getAvatarUrl();
    }

    public String getGravatarId() {
        return stargazer.getGravatarId();
    }

    public String getUrl() {
        return stargazer.getUrl();
    }

    public String getHtmlUrl() {
        return stargazer.getHtmlUrl();
    }

    public String getFollowersUrl() {
        return stargazer.getFollowersUrl();
    }

    public String getFollowingUrl() {
        return stargazer.getFollowingUrl();
    }

    public String getGistsUrl() {
        return stargazer.getGistsUrl();
    }

    public String getStarredUrl() {
        return stargazer.getStarredUrl();
    }

    public String getSubscriptionsUrl() {
        return stargazer.getSubscriptionsUrl();
    }

    public String getOrganizationsUrl() {
        return stargazer.getOrganizationsUrl();
    }


    public String getReposUrl() {
        return stargazer.getReposUrl();
    }


    public String getEventsUrl() {
        return stargazer.getEventsUrl();
    }


    public String getReceivedEventsUrl() {
        return stargazer.getReceivedEventsUrl();
    }


    public String getType() {
        return stargazer.getType();
    }


    public boolean isSiteAdmin() {
        return stargazer.isSiteAdmin();
    }


}
