/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class KwetterUserTest {

    /**
     * Test of timeline method, of class KwetterUser.
     */
    @Test
    public void testTimeline() {
        KwetterUser kwetterUser = new KwetterUser();
        KwetterUser following = new KwetterUser();
        Kwetter kwetter = new Kwetter("kwetterUser", "content", kwetterUser);
        Kwetter followingKwetter = new Kwetter("following", "following", following);
        kwetterUser.addKwetter(kwetter);
        following.addKwetter(followingKwetter);
        kwetterUser.follow(following);
        List<Kwetter> timeline = kwetterUser.timeline();
        Assert.assertEquals(timeline.get(0), kwetter);
        Assert.assertEquals(timeline.get(1), followingKwetter);
    }
    
}
