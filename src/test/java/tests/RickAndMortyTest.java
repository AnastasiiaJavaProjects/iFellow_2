package tests;

import hooks.WebHooks;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.RestSteps;

import java.util.List;

public class RickAndMortyTest extends WebHooks {

    @Test
    public void test1(){
        Response response1 = new RestSteps().getApi("https://rickandmortyapi.com/api/character?name=Morty", 200);
        String id = response1.body().jsonPath().getString("results[0].id");

        Response response2 = new RestSteps().getApi("https://rickandmortyapi.com/api/character/" + id, 200);
        List<String> episodes = response2.body().jsonPath().getList("episode");
        String mortysLastEpisodeUrl = episodes.get(episodes.size()-1);

        Response response3 = new RestSteps().getApi(mortysLastEpisodeUrl, 200);
        List<String> characters = response3.body().jsonPath().getList("characters");
        String lastCharacterUrl = characters.get(characters.size()-1);

        Response response4 = new RestSteps().getApi(lastCharacterUrl, 200);
        String personsLocation = response4.body().jsonPath().getString("location.name");
        String personsSpecies = response4.body().jsonPath().getString("species");

        String mortysLocation = response1.body().jsonPath().getString("location.name");
        String mortysSpecies = response1.body().jsonPath().getString("species");

        Assertions.assertEquals(mortysSpecies, personsSpecies);
        Assertions.assertNotEquals(mortysLocation, personsLocation);
    }
}
