package com.lasagnerd.texelate.microbool;

import com.intellij.testFramework.ParsingTestCase;
import org.junit.jupiter.api.Test;

class MicroboolParserDefinitionTest extends ParsingTestCase {

    public MicroboolParserDefinitionTest() {
        super("", "microbool", new MicroboolParserDefinition());
        this.myLanguage = MicroboolLanguage.INSTANCE;
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