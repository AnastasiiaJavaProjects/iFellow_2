package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Также;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static helpers.RestHelpers.getApi;
import static util.TestProperties.getProperty;

public class RickAndMortySteps {

    private String mortysId;
    private String mortysUrl;
    private String mortysLocation;
    private String mortysSpecies;
    private String mortysLastEpisodeUrl;
    private String lastCharactersUrl;
    private String lastCharactersLocation;
    private String lastCharactersSpecies;


    @Когда("отправляем GET запрос с именем {string} и получаем его id")
    public void getCharacterId(String name){
        Response response = getApi(getProperty("character.name.filter") + name, 200);
        mortysId = response.body().jsonPath().getString(getProperty("id.path"));
    }

    @Также("отправляем GET запрос с id Морти и получаем его URL")
    public void getCharacterUrl(){
        mortysUrl = getProperty("character.baseurl") + mortysId;
    }

    @Также("отправляем GET запрос с URL Морти и получаем данные о местонахождении Морти")
    public void getMortyLocation(){
        Response response = getApi(mortysUrl, 200);
        mortysLocation = response.body().jsonPath().getString(getProperty("location.path"));
    }

    @Также("отправляем GET запрос с URL Морти и получаем данные о расе Морти")
    public void getMortySpecies(){
        Response response = getApi(mortysUrl, 200);
        mortysSpecies = response.body().jsonPath().getString(getProperty("species.path"));
    }

    @Когда("отправляем GET запрос с id Морти и получаем URL его последнего эпизода")
    public void getMortysLastEpisodeUrl(){
        Response response = getApi(getProperty("character.baseurl") + mortysId, 200);
        List<String> episodes = response.body().jsonPath().getList(getProperty("episode.path"));
        mortysLastEpisodeUrl = episodes.get(episodes.size()-1);
    }

    @Также("отправляем GET запрос с URL последнего эпизода Морти и получаем URL последнего персонажа в этом эпизоде")
    public void getLastCharacterUrl(){
        Response response = getApi(mortysLastEpisodeUrl, 200);
        List<String> characters = response.body().jsonPath().getList(getProperty("characters.path"));
        lastCharactersUrl = characters.get(characters.size()-1);
    }

    @Также("отправляем GET запрос с URL последнего персонажа и получаем данные о его местонахождении")
    public void getLastCharacterLocation(){
        Response response = getApi(lastCharactersUrl, 200);
        lastCharactersLocation = response.body().jsonPath().getString(getProperty("location.path"));
    }

    @Также("отправляем GET запрос с URL последнего персонажа и получаем данные о его расе")
    public void getLastCharacterSpecies(){
        Response response = getApi(lastCharactersUrl, 200);
        lastCharactersSpecies = response.body().jsonPath().getString(getProperty("species.path"));
    }

    @Тогда("проверяем, что раса Морти и последнего персонажа совпадают")
    public void verifySpecies(){
        Assertions.assertEquals(mortysSpecies, lastCharactersSpecies);
    }

    @Тогда("проверяем, что местонахождение Морти и последнего персонажа не совпадают")
    public void verifyLocations(){
        Assertions.assertNotEquals(mortysLocation, lastCharactersLocation);
    }
}
