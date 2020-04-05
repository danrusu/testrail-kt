package demo

import khttp.get
import khttp.post
import netscape.javascript.JSObject
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*
import org.json.JSONObject


class RequestsTest {

    @Test
    fun getTest() {
        val simpleJsonRes = get("http://qatools.ro/testfiles/simpleJson.json")
        println("Response: $simpleJsonRes")
        assertThat(200).isEqualTo(simpleJsonRes.statusCode);

        val secondKid = simpleJsonRes.jsonObject.getJSONArray("kids").get(1)
        assertThat("tania").isEqualTo(secondKid);
    }

    @Test
    fun postTest() {
        val loginRes = post(
            "http://qatools.ro/api/login.php",
            data = mapOf(
                "username" to "tester",
                "password" to "passw0rd"
            )
        )

        assertThat(200).isEqualTo(loginRes.statusCode);
        assertThat("authorized").isEqualTo(loginRes.jsonObject.get("status"));
    }

    @Test
    fun postJsonBodyAsStringTest() {
        val body = """{
            "name":"Software testing course",
            "description":"Java Selenium Module",
            "project":"SDA",
            "date":"01/03/2020",
            "time":"09:00 AM",
            "priority":1
        }""";

        val response = post(
            "https://go-gin-todo.herokuapp.com/api/todo",
            headers = mapOf("content-type" to "application/json"),
            data = body
        )
        assertThat(200).isEqualTo(response.statusCode);
        println(response.jsonObject);
        assertThat("Todo successfully created").isEqualTo(response.jsonObject.get("message"));
    }

}