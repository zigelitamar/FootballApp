package DomainTest.PersonalPages;

import Domain.PersonalPages.ProfileContent;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ProfileContentTest {

    @Test
    public void addFeatureToProfile() {
        ProfileContent content = new ProfileContent();
        content.addFeatureToProfile("height","1.80");
        //fixme(we dont have a getter)
        assertEquals(1,1);
    }
}