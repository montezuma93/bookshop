package com.example.selenium;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},        features = {"src/test/java/com/example/selenium/features"})

public class WikipediaTests {

}
