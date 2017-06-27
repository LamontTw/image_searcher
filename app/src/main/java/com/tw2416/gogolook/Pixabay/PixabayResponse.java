package com.tw2416.gogolook.Pixabay;


import java.util.List;

/**
 * Created by Lamont on 2017/6/25.
 */

public class PixabayResponse {

    private String total;
    private String totalHits;
    private List<PixabayHit> hits = null;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(String totalHits) {
        this.totalHits = totalHits;
    }

    public List<PixabayHit> getHits() {
        return hits;
    }

    public void setHits(List<PixabayHit> hits) {
        this.hits = hits;
    }
}
