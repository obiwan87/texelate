package com.lasagnerd.whack.lang;

import com.intellij.testFramework.ParsingTestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WhackParserDefinitionTest extends ParsingTestCase {

    public WhackParserDefinitionTest() {
        super("", "whack", new WhackParserDefinition());
        this.myLanguage = WhackLanguage.INSTANCE;
    }


    @Override
    protected String getTestDataPath() {
        return "src/test/testData";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

    @Test
    public void psiTreeTest() {
        doTest(true);
    }

    @Override
    public String getName() {
        return "simple";
    }
}