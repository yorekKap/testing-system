package com.testing.system.web.dto;

/**
 * Created by yuri on 27.09.17.
 */
public class CreateCategoryDto {
    private String categoryTitle;
    private Boolean openToAll;

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Boolean getOpenToAll() {
        return openToAll;
    }

    public void setOpenToAll(Boolean openToAll) {
        this.openToAll = openToAll;
    }
}
