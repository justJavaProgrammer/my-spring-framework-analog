import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.finder.ImplementationFinder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.Cat;
import org.springframework.test.Cow;
import org.springframework.test.Pet;

class DefaultListableBeanFactoryTest {
    private ConfigurableListableBeanFactory factory =
            new DefaultListableBeanFactory(new ImplementationFinder("org/springframework/test"));

    @SneakyThrows
    @Test
    void createBean() {
        Pet bean = factory.createBean(Pet.class);
        Assert.assertEquals(Cow.class, bean.getClass());
    }

    @Test
    void getBean() {
        final Cat bean = factory.getBean(Cat.class);
        Assert.assertNotNull(bean);
        Assert.assertEquals(Cat.class, bean.getClass());

    }

    @Test
    void containsBean() {
        final boolean containsBean = factory.containsBean("fsafa");
        Assert.assertEquals(false, containsBean);
    }


}