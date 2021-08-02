import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.Cat;

public class TestTest {
    @Test
    <T> void isBeanCreate() {
        DefaultListableBeanFactory def = new DefaultListableBeanFactory();
        final Cat bean = def.getBean(Cat.class);
        bean.say();
        System.out.println(def.containsBean("cat"));
        Assert.assertNotNull(bean);
    }
}