package amazin;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
    public void launch() {
        String[] contextPaths = new String[] {"amzon/app-context.xml"};
        new ClassPathXmlApplicationContext(contextPaths);
    }
}
