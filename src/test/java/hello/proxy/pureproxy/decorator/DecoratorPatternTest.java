package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DecoratorPatternTest {

    @Test
    @DisplayName("noDecorator")
    void noDecorator() {
        //given
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        //then
        client.execute();
        client.execute();
        client.execute();
    }
    
    @Test
    @DisplayName("decoData1")
    void decoData1() {
        //given
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        //then
        client.execute();
    }
    @Test
    @DisplayName("decoData2")
    void decoData2() {
        //given
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        //then
        client.execute();
    }
}
