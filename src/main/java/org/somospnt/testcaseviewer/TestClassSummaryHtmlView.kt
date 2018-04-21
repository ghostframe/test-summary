package org.somospnt.testcaseviewer

import com.github.mustachejava.DefaultMustacheFactory
import java.io.StringWriter
import org.apache.commons.lang.StringUtils.capitalize
import org.apache.commons.lang.StringUtils.splitByCharacterTypeCamelCase

object TestClassSummaryHtmlView {

    fun render(testClassCode: String): String {
        val testClass = TestClass.parse(testClassCode)
        val testClassSummary = TestClassSummary.from(testClass)
        testClassSummary.methodSummaries.forEach { it.scenarios.forEach(TestClassSummaryHtmlView::formatScenario) }
        return DefaultMustacheFactory()
                .compile("testClassSummaryViewTemplate.html.mustache")
                .execute(StringWriter(), testClassSummary)
                .toString()
    }

    private fun formatScenario(scenario: TestClassSummary.Scenario) {
        scenario.description = capitalize(camelCaseToNaturalLanguage(scenario.description))
        scenario.outcome = camelCaseToNaturalLanguage(scenario.outcome)
    }

    private fun camelCaseToNaturalLanguage(text: String?): String {
        return splitByCharacterTypeCamelCase(text).joinToString(separator = " ").toLowerCase()
    }
}
