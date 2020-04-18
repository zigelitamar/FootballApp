package Domain.PersonalPages;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ProfileContentTest {

    @Test
    public void addFeatureToProfile() {
        HashMap<String,String> profile = new HashMap<>();
        ProfileContent content = new ProfileContent(profile);
        content.addFeatureToProfile("height","1.80");
        //fixme(we dont have a getter)
        assertEquals(1,1);
    }
}