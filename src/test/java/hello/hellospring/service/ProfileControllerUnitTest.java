package hello.hellospring.service;

import hello.hellospring.controller.ProfileController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.env.MockEnvironment;

public class ProfileControllerUnitTest {

    @Test
    public void real_profile이_조회된다(){
        //given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real_db");

        ProfileController controller = new ProfileController(env);
        //when
        String profile = controller.profile();
        //then
        Assertions.assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void real_profile이_없으면_첫_번째가_조회된다(){
        //given
        String expectatedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();

        env.addActiveProfile(expectatedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);
        //when
        String profile = controller.profile();
        //then
        Assertions.assertThat(profile).isEqualTo(expectatedProfile);
    }

    @Test
    public void active_profile이_없으면_default가_조회된다(){
        //given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment(); //스프링에서 제공하는 가짜 구현체
        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        Assertions.assertThat(profile).isEqualTo(expectedProfile);
    }
}
