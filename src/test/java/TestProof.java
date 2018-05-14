import util.Encrypt;

/**
 * @Author: cfx
 * @Description: 工作量证明理解demo
 * @Date: Created in 2018/5/9 16:42
 */
public class TestProof {
    public static void main(String[] args) {
        int x = 5;
        int y = 0;

        while (!(new Encrypt().Hash(x * y + "")).endsWith("0")) {
            y ++;
        }
        System.out.println("y的值为" + y);
    }
}
