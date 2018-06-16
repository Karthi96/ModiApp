package com.liteart.apps.modivsits;


import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PMVisit {


    public PMVisit(String placeOfVisitOrSummit, String year, String periodOfVisit, String visitDetails, String expenses, String moreUpdates) {
        this.placeOfVisitOrSummit = placeOfVisitOrSummit;
        this.year = year;
        this.periodOfVisit = periodOfVisit;
        this.visitDetails = visitDetails;
        this.expenses = expenses;
        this.moreUpdates = moreUpdates;

    }

    private String placeOfVisitOrSummit;
    private String year;
    private String type;
    private String dateCreated;
    private String periodOfVisit;
    private String visitDetails;
    private String expenses;
    private String moreUpdates;

    public String getPlaceOfVisitOrSummit() {
        return placeOfVisitOrSummit;
    }

    public void setPlaceOfVisitOrSummit(String placeOfVisitOrSummit) {
        this.placeOfVisitOrSummit = placeOfVisitOrSummit;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPeriodOfVisit() {
        return periodOfVisit;
    }

    public void setPeriodOfVisit(String periodOfVisit) {
        this.periodOfVisit = periodOfVisit;
    }

    public String getVisitDetails() {
        return visitDetails;
    }

    public void setVisitDetails(String visitDetails) {
        this.visitDetails = visitDetails;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getMoreUpdates() {
        return moreUpdates;
    }

    public void setMoreUpdates(String moreUpdates) {
        this.moreUpdates = moreUpdates;
    }

}
