package steps;

import io.restassured.response.Response;

import java.util.List;

import static helpers.RestHelpers.getApi;

public class RestSteps {

    public static String getCharacterId(String name){
        Response response = getApi("https://rickandmortyapi.com/api/character?name=" + name, 200);
        return response.body().jsonPath().getString("results[0].id");
    }

    public static String getCharacterUrl(String characterId){
        return "https://rickandmortyapi.com/api/character/" + characterId;
    }

    public static String getCharacterLastEpisodeUrl(String characterId){
        Response response = getApi("https://rickandmortyapi.com/api/character/" + characterId, 200);
        List<String> episodes = response.body().jsonPath().getList("episode");
        return episodes.get(episodes.size()-1);
    }

    public static String getLastCharacterUrl(String episodeUrl){
        Response response = getApi(episodeUrl, 200);
        List<String> characters = response.body().jsonPath().getList("characters");
        return characters.get(characters.size()-1);
    }

    public static String getCharactersLocation(String characterUrl){
        Response response = getApi(characterUrl, 200);
        return response.body().jsonPath().getString("location.name");
    }

    public static String getCharactersSpecies(String characterUrl){
        Response response = getApi(characterUrl, 200);
        return response.body().jsonPath().getString("species");
    }
}
