package com.tech.dpn.bidapplication.utils;

public enum Category {
    Painting("Painting"), Sculptor("Sculptor"), Ornament("Ornament");

    private String category;

    private String getCategory() {
        return this.category;
    }

    private Category(String category) {
        this.category = category;
    }
}
