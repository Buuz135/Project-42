package com.buuz135.project42.api.manual;

import com.buuz135.project42.Project42;
import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class CategoryEntry {

    private List<Page> iPages;
    private List<IContent> contents;

    public CategoryEntry() {
        this.iPages = new ArrayList<>();
        this.contents = new ArrayList<>();
    }

    public List<IContent> getContent() {
        return contents;
    }

    public abstract ICategoryEntryDisplay getDisplay();

    public List<Page> getPages() {
        return iPages;
    }

    public CategoryEntry addContent(IContent content) {
        getContent().add(content);
        return this;
    }

    public void generatePages(int dimensionX, int dimensionY) {
        List<IContent> duplicateContent = new ArrayList<>(getContent());
        while (!duplicateContent.isEmpty()) {
            int currentY = 0;
            Page page = new Page();
            while (currentY < dimensionY) {
                if (duplicateContent.isEmpty()) break;
                int contentPointer = 0;
                int biggerY = 0;
                int currentX = 0;
                List<Page.FormattedContent> testContent = new ArrayList<>();
                while (currentX < dimensionX) {
                    if (contentPointer >= duplicateContent.size()) break;
                    IContent content = duplicateContent.get(contentPointer);
                    testContent.add(new Page.FormattedContent(currentX, currentY, content));
                    currentX += content.getSizeX();
                    if (content.getSizeY() > biggerY) biggerY = content.getSizeY();
                    ++contentPointer;
                }
                if (currentY + biggerY > dimensionY) {
                    int size = dimensionY - currentY;
                    int index = 0;
                    List<Page.FormattedContent> cleanedContent = new ArrayList<>();
                    for (Page.FormattedContent formattedContent : testContent) {
                        if (formattedContent.getContent().canBeSplitted()) {
                            duplicateContent.remove(formattedContent.getContent());
                            Pair<IContent, IContent> splitted = formattedContent.getContent().split(size);
                            cleanedContent.add(new Page.FormattedContent(formattedContent.getX(), formattedContent.getY(), splitted.getLeft()));
                            duplicateContent.add(index, splitted.getRight());
                        }
                        ++index;
                    }
                    testContent = cleanedContent;
                }
                currentY += biggerY;
                page.getFormattedContent().addAll(testContent);
                for (Page.FormattedContent formattedContent : testContent) {
                    duplicateContent.remove(formattedContent.getContent());
                }
            }
            if (!page.getFormattedContent().isEmpty()) getPages().add(page);
        }
        if (getPages().size() > 0) Project42.LOGGER.info("Generated " + getPages().size() + " pages");
    }
}
