package tests;

import hooks.WebHooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static steps.RickAndMortySteps.*;
import static util.TestProperties.getProperty;

public class RickAndMortyTest extends WebHooks {

    @Test
    public void test1(){
        String mortysId = getCharacterId(getProperty("test.character"));
        String mortysUrl = getCharacterUrl(mortysId);
        String mortysLocation = getCharactersLocation(mortysUrl);
        String mortysSpecies = getCharactersSpecies(mortysUrl);

        String lastCharacterEpisodeUrl = getCharacterLastEpisodeUrl(mortysId);
        String lastCharacterUrl = getLastCharacterUrl(lastCharacterEpisodeUrl);
        String lastCharactersLocation = getCharactersLocation(lastCharacterUrl);
        String lastCharactersSpecies = getCharactersSpecies(lastCharacterUrl);

        Assertions.assertEquals(mortysSpecies, lastCharactersSpecies);
        Assertions.assertNotEquals(mortysLocation, lastCharactersLocation);
    }
}
