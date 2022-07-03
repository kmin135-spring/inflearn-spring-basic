package hello.core.singleton;

public class StatefulService {
    // 상태를 가짐
    // 이러면 안 된다.
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int statelessOrder(String name, int price) {
        return price;
    }
}
