package ru.stqa.jft.rest;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Anna on 2017-02-03.
 */
public class RestTests extends TestBase {

    @Test
    //
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(5);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
