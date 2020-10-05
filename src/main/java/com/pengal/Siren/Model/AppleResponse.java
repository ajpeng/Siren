package com.pengal.Siren.Model;

import java.util.List;

public class AppleResponse {
    Integer resultCount;
    List<ApplePodcast> results;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<ApplePodcast> getResults() {
        return results;
    }

    public void setResults(List<ApplePodcast> results) {
        this.results = results;
    }
}
