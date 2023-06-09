package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConcreteClientTest {
    @Test
    @DisplayName("noProxy")
    void noProxy() {
        //given
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        //then
        client.execute();
    }

}
