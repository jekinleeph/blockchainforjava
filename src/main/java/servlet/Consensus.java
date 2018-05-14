package servlet;

import core.BlockChain;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: cfx
 * @Description: 一致性共识算法，解决共识冲突，保证所有的节点都在同一条链上(最长链)
 * @Date: Created in 2018/5/10 11:38
 */
@WebServlet("/nodes/resolve")
public class Consensus extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlockChain blockChain = BlockChain.getInstance();
        boolean flag = blockChain.resolveConflicts();
        System.out.println("是否解决一致性共识冲突：" + flag);
    }
}
