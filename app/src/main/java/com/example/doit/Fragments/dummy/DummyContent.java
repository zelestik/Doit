package com.example.doit.Fragments.dummy;

import android.text.format.DateFormat;

import com.example.doit.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.doit.VeryMainActivity.alllabs;
import static com.example.doit.VeryMainActivity.date;
import static com.example.doit.VeryMainActivity.donelabs;
import static com.example.doit.VeryMainActivity.lessons;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    private static final int COUNT = lessons.size();

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), lessons.get(position - 1) + "\n" +  "Осталось работ: " + Integer.toString((alllabs.get(position - 1) - donelabs.get(position-1))) + "\n" + "Сделать до: " + convertDate((date.get(position -1 )), "dd/MM/yyyy"), makeDetails(position));
    }

    private static String convertDate(Long dateInMilliseconds,String dateFormat) {
        return DateFormat.format(dateFormat, dateInMilliseconds).toString();
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
