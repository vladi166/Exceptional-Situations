import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.AlreadyExistsException;
import ru.netology.javaqa.NotFoundException;
import ru.netology.javaqa.Product;
import ru.netology.javaqa.ShopRepository;

public class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(957, "PS5", 60_000);
    Product product2 = new Product(948, "TV", 150_000);
    Product product3 = new Product(849, "Sofa", 50_000);


    @Test
    public void addingAnExistingProduct() {// добавление продукта
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product[] expected = { product1, product2, product3 };
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void successOfDeletingAnExistingElement() { // удаление существующего продукта
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.removeById(849);

        Product[] expected = { product1, product2 };
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void deletingANonExistentElement() { // удаление несуществующего продукта
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(925);
        });
    }

    @Test
    public void addAnItemWithADuplicateID() { // добавление продукта с дупликатом ID
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(product2);
        });
    }
}
