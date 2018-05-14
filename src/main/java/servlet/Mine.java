package servlet;

import core.BlockChain;
import core.Proof;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cfx
 * @Description: 该Servlet用于运行工作算法的证明来获得下一个证明，也就是所谓的挖矿
 * @Date: Created in 2018/5/9 17:21
 */
@WebServlet("/mine")
public class Mine extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlockChain blockChain = BlockChain.getInstance();

        //计算出工作量证明
        Map<String,Object> lastBlock = blockChain.lastBlock();
        Long last_proof = Long.parseLong(lastBlock.get("proof") + "");
        Long proof = new Proof().ProofOfWork(last_proof);

        //奖励计算出工作量证明的矿工1个币的奖励，发送者为"0"表明这是新挖出的矿。
        String uuid = (String) this.getServletContext().getAttribute("uuid");
        blockChain.newTransactions("0",uuid,1);

        //构建新的区块
        Map<String,Object> newBlock = blockChain.newBlock(proof,null);
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "New Block Forged");
        response.put("index", newBlock.get("index"));
        response.put("transactions", newBlock.get("transactions"));
        response.put("proof", newBlock.get("proof"));
        response.put("previous_hash", newBlock.get("previous_hash"));

        // 返回新区块的数据给客户端
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(new JSONObject(response));
        printWriter.close();
    }
}
