package com.github.quiram.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.quiram.utils.Collections.transpose;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.StringContains.containsString;

public class TransposeNegativeTest {
    @Rule
    public ExpectedException onBadData = ExpectedException.none();

    @Test
    public void failToTransposeForListsOfDifferentSize() {
        onBadData.expect(IllegalArgumentException.class);
        onBadData.expectMessage(containsString("must be square"));

        transpose(asList(asList(1, 2), singletonList(1)));
    }
}
