package com.github.quiram.utils;

import org.junit.jupiter.api.Test;

import static com.github.quiram.utils.Collections.transpose;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransposeNegativeTest {
    @Test
    public void failToTransposeForListsOfDifferentSize() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> transpose(asList(asList(1, 2), singletonList(1))));
        assertThat(illegalArgumentException.getMessage(), containsString("must be square"));
    }
}
