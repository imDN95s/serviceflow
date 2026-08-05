package com.serviceflow.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(
        packages = "com.serviceflow",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class ModuleArchitectureTest {
    @ArchTest
    public static final ArchRule MODULES_SHOULD_BE_FREE_OF_CYCLES =
            slices()
                    .matching("com.serviceflow.(*)..")
                    .should()
                    .beFreeOfCycles();
}
