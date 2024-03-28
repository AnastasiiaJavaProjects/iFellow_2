package steps;

import io.restassured.response.Response;

import java.util.List;

import static helpers.RestHelpers.getApi;
import static util.TestProperties.getProperty;

public class RickAndMortySteps {

    public static String getCharacterId(String name){
        Response response = getApi(getProperty("character.name.filter") + name, 200);
        return response.body().jsonPath().getString(getProperty("id.path"));
    }

    public static String getCharacterUrl(String characterId){
        return getProperty("character.baseurl") + characterId;
    }

    public static String getCharacterLastEpisodeUrl(String characterId){
        Response response = getApi(getProperty("character.baseurl") + characterId, 200);
        List<String> episodes = response.body().jsonPath().getList(getProperty("episode.path"));
        return episodes.get(episodes.size()-1);
    }

    public static String getLastCharacterUrl(String episodeUrl){
        Response response = getApi(episodeUrl, 200);
        List<String> characters = response.body().jsonPath().getList(getProperty("characters.path"));
        return characters.get(characters.size()-1);
    }

    public static String getCharactersLocation(String characterUrl){
        Response response = getApi(characterUrl, 200);
        return response.body().jsonPath().getString(getProperty("location.path"));
    }

    public static String getCharactersSpecies(String characterUrl){
        Response response = getApi(characterUrl, 200);
        return response.body().jsonPath().getString(getProperty("species.path"));
    }
}
