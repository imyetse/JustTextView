package cn.tseeey.justtext;

import android.text.Spanned;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Des:
 * Create by: ye
 * On:        2019\07\18 15:18
 * Email:     yeshieh@163.com
 */
public class SpanSet<E> {
    private final Class<? extends E> classType;

    int numberOfSpans;
    E[] spans;
    int[] spanStarts;
    int[] spanEnds;
    int[] spanFlags;

    SpanSet(Class<? extends E> type) {
        classType = type;
        numberOfSpans = 0;
    }

    @SuppressWarnings("unchecked")
    public void init(Spanned spanned, int start, int limit) {
        final E[] allSpans = spanned.getSpans(start, limit, classType);
        final int length = allSpans.length;

        if (length > 0 && (spans == null || spans.length < length)) {
            // These arrays may end up being too large because of the discarded empty spans
            spans = (E[]) Array.newInstance(classType, length);
            spanStarts = new int[length];
            spanEnds = new int[length];
            spanFlags = new int[length];
        }

        int prevNumberOfSpans = numberOfSpans;
        numberOfSpans = 0;
        for (int i = 0; i < length; i++) {
            final E span = allSpans[i];

            final int spanStart = spanned.getSpanStart(span);
            final int spanEnd = spanned.getSpanEnd(span);
            if (spanStart == spanEnd) continue;

            final int spanFlag = spanned.getSpanFlags(span);

            spans[numberOfSpans] = span;
            spanStarts[numberOfSpans] = spanStart;
            spanEnds[numberOfSpans] = spanEnd;
            spanFlags[numberOfSpans] = spanFlag;

            numberOfSpans++;
        }

        // cleanup extra spans left over from previous init() call
        if (numberOfSpans < prevNumberOfSpans) {
            // prevNumberofSpans was > 0, therefore spans != null
            Arrays.fill(spans, numberOfSpans, prevNumberOfSpans, null);
        }
    }

    /**
     * Returns true if there are spans intersecting the given interval.
     * @param end must be strictly greater than start
     */
    public boolean hasSpansIntersecting(int start, int end) {
        for (int i = 0; i < numberOfSpans; i++) {
            // equal test is valid since both intervals are not empty by construction
            if (spanStarts[i] >= end || spanEnds[i] <= start) continue;
            return true;
        }
        return false;
    }

    /**
     * Similar to {@link Spanned#nextSpanTransition(int, int, Class)}
     */
    int getNextTransition(int start, int limit) {
        for (int i = 0; i < numberOfSpans; i++) {
            final int spanStart = spanStarts[i];
            final int spanEnd = spanEnds[i];
            if (spanStart > start && spanStart < limit) limit = spanStart;
            if (spanEnd > start && spanEnd < limit) limit = spanEnd;
        }
        return limit;
    }

    /**
     * Removes all internal references to the spans to avoid memory leaks.
     */
    public void recycle() {
        if (spans != null) {
            Arrays.fill(spans, 0, numberOfSpans, null);
        }
    }
}