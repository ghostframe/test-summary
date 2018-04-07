package org.somospnt.testcaseviewer

import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import com.github.mustachejava.MustacheFactory
import java.io.StringWriter
import lombok.SneakyThrows
import org.apache.commons.lang.StringUtils.capitalize
import org.apache.commons.lang.StringUtils.join
import org.apache.commons.lang.StringUtils.splitByCharacterTypeCamelCase

object TestClassSummaryHtmlView {

    fun render(testSuite: TestClass): String {
        val mf = DefaultMustacheFactory()
        val mustache = mf.compile("testClassViewTemplate.html.mustache")
        val testSuiteGraph = TestClassSummary.from(testSuite)
        testSuiteGraph.methods.forEach { it.scenarios.forEach(TestClassSummaryHtmlView::formatScenario) }
        return mustache.execute(StringWriter(), testSuiteGraph).toString()
    }

    private fun formatScenario(scenario: TestClassSummary.Scenario) {
        scenario.description = capitalize(camelCaseToNaturalLanguage(scenario.description))
        scenario.outcome = camelCaseToNaturalLanguage(scenario.outcome)
    }

    private fun camelCaseToNaturalLanguage(text: String?): String {
        val words = splitByCharacterTypeCamelCase(text)
        return join(words, ' ').toLowerCase()
    }
}
