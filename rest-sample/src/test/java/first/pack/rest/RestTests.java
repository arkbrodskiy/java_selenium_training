package first.pack.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;


public class RestTests {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> before = getIssues();
        Issue issue = new Issue().withSubject("mouse eats cats").withDescription("weird things happen");
        int issueId = createIssue(issue);
        Set<Issue> after = getIssues();
        before.add(issue.withId(issueId));
        assertEquals(after, before);
    }

    private Set<Issue> getIssues() throws IOException {
        String jsonString = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json?limit=500")).returnContent().asString();
        JsonElement element = new JsonParser().parse(jsonString);
        JsonElement issues = element.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
    }

    private int createIssue(Issue issue) throws IOException {
        String jsonString = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", issue.getSubject())
                        , new BasicNameValuePair("description", issue.getDescription())))
                .returnContent().asString();
        JsonElement element = new JsonParser().parse(jsonString);
        return element.getAsJsonObject().get("issue_id").getAsInt();

    }


}
