package com.buuz135.project42.api.manual;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private final List<FormattedContent> formattedContent;

    public Page() {
        formattedContent = new ArrayList<>();
    }

    public List<FormattedContent> getFormattedContent() {
        return formattedContent;
    }

    public static class FormattedContent {

        private int x;
        private int y;
        private IContent content;

        public FormattedContent(int x, int y, IContent content) {
            this.x = x;
            this.y = y;
            this.content = content;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public IContent getContent() {
            return content;
        }

        public void setContent(IContent content) {
            this.content = content;
        }
    }
}
