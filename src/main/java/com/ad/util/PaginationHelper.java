package com.ad.util;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PaginationHelper<T> {

    public static final int MAX_PAGE_ITEM_DISPLAY = 10;
    private Page<T> page;
    private List<PageItem> items;
    private int currentNumber;
    private String url;

    public PaginationHelper(Page<T> page, String url) {
        this.page = page;
        this.url = url;
        items = new ArrayList();
        currentNumber = page.getNumber() + 1;

        int start, size;
        if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
            start = 1;
            size = page.getTotalPages();
        } else {
            if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
                start = 1;
                size = MAX_PAGE_ITEM_DISPLAY;
            } else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
                start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
                size = MAX_PAGE_ITEM_DISPLAY;
            } else {
                start = currentNumber - MAX_PAGE_ITEM_DISPLAY / 2;
                size = MAX_PAGE_ITEM_DISPLAY;
            }
        }

        for (int i = 0; i < size; i++) {
            items.add(new PageItem(start + i, (start + i) == currentNumber));
        }
    }

    public Page<T> getPage() {
        return page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PageItem> getItems() {
        return items;
    }

    public int getNumber() {
        return currentNumber;
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public int getSize() {
        return page.getSize();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean hasPrevious() {
        return page.hasPrevious();
    }

    public boolean hasNext() {
        return page.hasNext();
    }

    public class PageItem {
        private int number;
        private boolean current;

        public PageItem(int number, boolean current) {
            this.number = number;
            this.current = current;
        }

        public int getNumber() {
            return this.number;
        }

        public boolean isCurrent() {
            return this.current;
        }
    }
}