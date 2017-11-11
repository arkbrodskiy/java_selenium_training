package first.pack.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;


public class RestAssuredTests {

    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> before = getIssues();
        Issue issue = new Issue().withSubject("Unicorn has three horns").withDescription("That's funny");
        int issueId = createIssue(issue);
        Set<Issue> after = getIssues();
        before.add(issue.withId(issueId));
        assertEquals(after, before);
    }

    private Set<Issue> getIssues() throws IOException {
        String jsonString = RestAssured.get("http://demo.bugify.com/api/issues.json?limit=500").asString();
                JsonElement element = new JsonParser().parse(jsonString);
        JsonElement issues = element.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private int createIssue(Issue issue) throws IOException {
        String jsonString = RestAssured.given().param("subject", issue.getSubject())
                .param("description", issue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();
                JsonElement element = new JsonParser().parse(jsonString);
        return element.getAsJsonObject().get("issue_id").getAsInt();

    }


}
