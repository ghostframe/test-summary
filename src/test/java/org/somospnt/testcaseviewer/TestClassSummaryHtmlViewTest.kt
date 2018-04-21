package org.somospnt.testcaseviewer

import java.util.Arrays.asList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.somospnt.testcaseviewer.Util.contentOfResource

class TestClassSummaryHtmlViewTest {

    @Test
    fun render_withTestClassText_returnsExpectedHtml() {
        assertThat(TestClassSummaryHtmlView.render(contentOfResource("testClass.txt")))
                .isEqualTo(contentOfResource("testClassView.html"))
    }
}
